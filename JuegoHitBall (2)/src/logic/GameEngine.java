package logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JPanel;

import assets.Constantes;
import assets.Recursos;
import model.Movimiento;
import model.Personaje;
import model.Power;
import view.Finjuego;
import view.VPrincipal;

public class GameEngine extends JPanel  implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Power> powers;
	private ArrayList<Power> powersActivos;
	private ArrayList<Power> toremove;
	private ArrayList<Personaje> personajes;
	private ArrayList<Personaje> capturados;
	public int njugador;
	private int refresh=0,totalpowers=0,indice=0;
	private int x,y;
	public boolean bandera=true;
	//private VPrincipal ventana; para no hacer cosas publicas en la ventana
	
	private byte[] enviados = new byte[1024];
	private byte[] recibidos = new byte[1024];
	private DatagramSocket socketUDP=null;
	private InetAddress direccion=null;
	private DatagramPacket dpenvio;
	private DatagramPacket dprecibo;
	private Thread hilo;
	
	boolean isRunning = true;
	private BufferedImage bufferedImage;
	private InputHandler input;

	public GameEngine(VPrincipal ventanaA) {
		setVisible(true);
		setBounds(0, 0, 1340, 650);
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		personajes= new ArrayList<Personaje>();
		capturados= new ArrayList<Personaje>();
		powers= new ArrayList<Power>();
		powersActivos= new ArrayList<Power>();
		toremove=new ArrayList<Power>();
		
		// CREAR SOCKET Y ESTABLECER DIRECCION
		try {
			socketUDP = new DatagramSocket(Constantes.PORTCLIENT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		// Datos del Server
		try {
			direccion = InetAddress.getByName("192.168.1.131");//si quiero poner VPrincipal.host tengo que iniciar la clase gameengine despues del dialogo	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		// Lanzamos el hilo que se va a encargar de escuchar todo lo que el server nos envie
		hilo = new Thread(this);
		hilo.setName("hilo");
		hilo.start();
		crear();	
	}
	
	public void crear() {//creamos loa arrays de poderes y personajes
		x=185;
		y=0;
		for(int i=0;i<21;i++) {
			Power power=new Power();
			if(y==600) {
				x+=185;
				y=200;
			}else {
				y+=200;
			}
			power.setX(x);
			power.setY(y);
			powers.add(power);	
		}
		x=0;
		y=100;
		for (int i = 1; i <= Constantes.maxJugadores; i++) {//puedes poner <=6 para ver a todos los jugadores
			Personaje personaje = new Personaje(i);
			if(i==1) {
				y+=400;
				x=600;
				personaje.setX(x);
				personaje.setY(y);
				x=0;
				y=100;
			}else {
				x+=200;
				personaje.setX(x);
				personaje.setY(y);
			}
			personajes.add(personaje);
		}
	}
	
	public void update(Movimiento movimiento) {//recivimos el movimiento del server 
		for (Personaje personaje : personajes) {
			if (personaje.getPersonaje()==movimiento.getJugador()) {
				
				if(movimiento.getMove()==0){
					personaje.up();
				}
				if (movimiento.getMove()==1) {
					personaje.right();
				}
				if(movimiento.getMove()==2){
					personaje.down();
				}
				if (movimiento.getMove()==3) {
					personaje.left();
				}
				if (movimiento.getMove()==4) {
					personaje.espacio();
				}
			}
		}		
		
		// update sprites
		for(Personaje personaje : personajes) {
			personaje.siguiente();
		}	
		
	}

	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		// Fondo
		bbg.drawImage(Recursos.fondo, 0, 0, 1340, 650, null);

		colision();//antes de pintar las cosas vemos si ha habido una colision
		// draws sprites
		for(Power p : powersActivos) {
			p.draw(bbg);
		}
		for(Personaje personaje : personajes) {
			personaje.draw(bbg);
		}
		
        g.drawImage(bufferedImage, 0, 0, this);
	}
	
	protected void mover() {//creamos los rayos y estamos atentos a que tecla se pulsa, para enviar que movimiento hacer y que personaje lo hace
		refresh=++refresh %Constantes.tiemponextpower;
		if(refresh==0) {
			if(totalpowers<1 && indice<powers.size()) {
				powersActivos.add(powers.get(indice));
				totalpowers++;
				indice++;
			}
		}	
		if(input.isKeyDown(KeyEvent.VK_UP)){
			enviar(new Movimiento(njugador,0));
		}
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			enviar(new Movimiento(njugador,1));
		}
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			enviar(new Movimiento(njugador,2));
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			enviar(new Movimiento(njugador,3));
		}
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			enviar(new Movimiento(njugador,4));
		}
	}

	
	// Continuamente estamos atentos a lo que nos envie el server
	@Override
	public void run() {
		while(true) {
			recibidos = new byte[1024];
			dprecibo = new DatagramPacket(recibidos, recibidos.length);
			try {
				socketUDP.receive(dprecibo);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(recibidos);
				ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
				Movimiento movimiento=(Movimiento) is.readObject();
				is.close();
				update(movimiento);//movemos al perosnaje
			} catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	//para evitar desajustes podemos simplemente recibir el primer movimiento, guardarlo en una varibale y hacerlo infinitamente hasta
		//que entre un movimiento distinto en el cual repetimos el proceso, esto haria que una sola pulsada mueva al personaje
		//seria algo asi, a groso modo y hacer continue tambien si no se manda ninguna tecla
//		Movimiento movimiento2=new Movimiento(kk,2);
//		update(movimiento2);                         Esto al principio del bucle y tras recibir el movimiento
//		if(movimiento.getMove()==kk) {
//			continue;
//		}else {
//			kk=movimiento.getMove();
//		}
	}
	
	public void colision() {//control de choques
		colisionPersonaje();

		String s="Capturdos: ";
		for (Personaje p : capturados) {
			s=s+p.getPersonaje()+" ";
		}
		VPrincipal.lblInfo.setText(s);
		
		colisionRayo();
	}
	
	public void fin() {//controlamos que se termine el juego
		if(capturados.size()==Constantes.maxJugadores-1) {//puedes poner ==5 para ver a todos los personajes aunque no se muevan, y quitar los if del findialog para que los pinte a 3 en el podio
			Recursos.mjuego.stop();
			Recursos.fin.play();
			Finjuego findialog= new Finjuego(capturados);
			findialog.setLocationRelativeTo(this);
			findialog.setVisible(true);
			bandera=false;
		}
	}
	
	public void enviar(Movimiento movimiento) {//enviamos el movimiento al server
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(movimiento);
			enviados = baos.toByteArray();
			dpenvio= new DatagramPacket(enviados, enviados.length,direccion,Constantes.PORTSERVER2);
			socketUDP.send(dpenvio);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void colisionPersonaje() {//controlamos que si un personaje choca con el enemigo lo elimina, salvo alguna excepcion
		for (Personaje personaje : personajes) {
			Rectangle recPer=new Rectangle(personaje.getX(),personaje.getY(),personaje.getANCHO(),personaje.getALTO());
			for (Personaje p : personajes) {
				if(personaje.getPersonaje()!=p.getPersonaje()) {
					Rectangle recPersonajes=new Rectangle(p.getX(),p.getY(),p.getANCHO(),p.getALTO());
					if(p.getPersonaje()==1) {
						if(recPer.intersects(recPersonajes)){
							if(personaje.getPersonaje()==5 && personaje.isHabilidad()==true) {
								personaje.setX(0);
								personaje.setY(0);
								personaje.reinicio();
								personaje.setHabilidad(false);
								if ( personaje.getPersonaje()==njugador) {
									VPrincipal.lblpowerup.setVisible(false);
								}	
							}else if(personaje.getPersonaje()==2 && personaje.isHabilidad()==true){
								p.rickgolpeado();
								personaje.reinicio();
								personaje.setHabilidad(false);
								if ( personaje.getPersonaje()==njugador) {
									VPrincipal.lblpowerup.setVisible(false);
								}	
							}else {
								Recursos.rickpilla.play();
								capturados.add(personaje);								
							}
						}
					}else{//si choca con otro personaje que no sea el enemigo desactiva su habilidad
						if(recPer.intersects(recPersonajes) ) {								
							personaje.setHabilidad(false);
							personaje.reinicio();
							if ( p.getPersonaje()==njugador) {
								VPrincipal.lblpowerup.setVisible(false);
							}						
						}						
					}
				}
			}
		}
		personajes.removeAll(capturados);
	}

	private void colisionRayo() {//controlamos que si un personaje que no sea elenemigo choca con un poder se activa su habilidad
		for (Power power : powersActivos) {
			Rectangle recPower=new Rectangle(power.getX(),power.getY(),power.getANCHO(),power.getALTO());
			for (Personaje personaje : personajes) {
				if(personaje.getPersonaje()!=1) {
					Rectangle recP=new Rectangle(personaje.getX(),personaje.getY(),personaje.getANCHO(),personaje.getALTO());
					if(recPower.intersects(recP)){						
						toremove.add(power);
						totalpowers--;
						personaje.setHabilidad(true);
						if(personaje.getPersonaje()==njugador) {							
							VPrincipal.lblpowerup.setVisible(true);
						}						
						Recursos.sonidopower.play();
					}
				}
			}			
		}		
		powersActivos.removeAll(toremove);//para evitar ConcurrentModificationException
	}
}
/**
El juego tiene problemas como que se desacompasa a veces, o el hecho de pintar los rayitos, otra opción que me plantee era tener solo 3 rayitos
ya pintados y quien los coja antes pues ya tenia los poderes, pero nada de que se vayan pintando, ni que al colisionar con otro compañero se desactive.
Al final lo he hecho de la manera mas complicada porque me parecía que podía tener mas estrategia aunque la programación esta un poco peor y seguro 
que se puede hacer mejor.

*/
