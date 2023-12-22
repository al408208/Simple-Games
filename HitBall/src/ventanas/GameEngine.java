package ventanas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import assets.Constantes;
import assets.Recursos;
import objetos.Barra;
import objetos.Bola;
import objetos.InputHandler;
import objetos.Jelly;
import objetos.Pingubueno;
import objetos.Pingumalo;

public class GameEngine extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private int contadorBola=0;
	private int contadorRitmoJ=0;
	private int totalJellys=0;
	private int contadorNextJ=0;
	private int contadorRitmop=0;
	private int totalp2=0;
	private int contadorNextp2=0;
	private int contadorNextp1=0;
	private int totalp1=0;
	private int indice=0;
	private int indice2=0;
	private int indice3=0;
	private int puntuacion=0;
	private int vidas=3;
	private int variacion=0;
	
	boolean isRunning = true;
	private BufferedImage bufferedImage;
	private InputHandler input;
	private Barra barra;
	private Bola bola;
	private boolean bandera=true;
	private ArrayList<Jelly> mons;
	private ArrayList<Jelly> monsActivos;
	private ArrayList<Jelly> toremove;
	private ArrayList<Jelly> arrayaux;
	private ArrayList<Pingumalo> p2;
	private ArrayList<Pingumalo> p2Activos;
	private ArrayList<Pingumalo> toremove2;
	private ArrayList<Pingubueno> p1;
	private ArrayList<Pingubueno> p1Activos;
	private ArrayList<Pingubueno> toremove3;
	private VPrincipal ventana;

	public GameEngine(VPrincipal ventanaA) {
		setVisible(true);
		setBounds(0, 0, 800, 600);
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		
		ventana= ventanaA;
		barra = new Barra();
		bola = new Bola(barra);
		mons= new ArrayList<Jelly>();
		monsActivos=new ArrayList<Jelly>();
		toremove=new ArrayList<Jelly>();
		arrayaux=new ArrayList<Jelly>();
		
		p2= new ArrayList<Pingumalo>();
		p2Activos=new ArrayList<Pingumalo>();
		toremove2=new ArrayList<Pingumalo>();
		p1= new ArrayList<Pingubueno>();
		p1Activos=new ArrayList<Pingubueno>();
		toremove3=new ArrayList<Pingubueno>();
		crear();	
	}
	public void crear() {
		for(int i=0;i<20;i++) {
			Jelly jelly=new Jelly();
			mons.add(jelly);	
		}
		for(int i=0;i<5;i++) {
			Pingumalo pingum=new Pingumalo();
			p2.add(pingum);	
		}
		for(int i=0;i<5;i++) {
			Pingubueno pingub=new Pingubueno();
			p1.add(pingub);	
		}
	}

	void update() {
		// handle inputs
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			barra.right();
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			barra.left();
		}
		// update sprites
		barra.siguiente();
		bola.update();
		cotroltiempo();
		variaciones();
	}

	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		// Fondo
		bbg.drawImage(Recursos.fondojuego, 0, 0, 800, 600, null);
	
		// draws sprites
		barra.draw(bbg);
		bola.draw(bbg);
		for (Jelly j : monsActivos) {
			j.draw(bbg);
		}
		for (Pingubueno p1 : p1Activos) {
			p1.draw(bbg);
		}
		for (Pingumalo p2 : p2Activos) {
			p2.draw(bbg);
		}	
		for(Jelly jr : toremove) {
			if(!jr.getControlfin()) {
				jr.draw(bbg);
			}	
		}
        g.drawImage(bufferedImage, 0, 0, this);
	}
	
	public void fin() {
		if(bola.getyBola()>600) {
			if(vidas==2) {
				ventana.vida.setVisible(false);
			}else if(vidas==1) {
				ventana.vida2.setVisible(false);
			}else {
				ventana.vida3.setVisible(false);
			}
			Recursos.vidamenos.play();
			
			if(vidas==1||vidas==0) {
				vidas=0;
				Recursos.musica.stop();
				Recursos.lose.play();
				JOptionPane.showMessageDialog(this, "More luck next time", "You have lost :(", JOptionPane.WARNING_MESSAGE,Recursos.iirip);
				cambio();
			}else {
				vidas--;
				bola.setxBola(400);
				bola.setyBola(0);	
			}
		}
		if(arrayaux.size()==20) {
			Recursos.musica.stop();
			Recursos.win.play();
			JOptionPane.showMessageDialog(this, "Congratulations boss", "You win", JOptionPane.PLAIN_MESSAGE,Recursos.iivictory);
			cambio();
		}
	}
	public void cambio(){
		bandera=!bandera;
	}
	public boolean isBandera() {
		return bandera;
	}
	
	public void colision() {
		Rectangle recBola=new Rectangle(bola.getxBola(),bola.getyBola(),Constantes.anchoBola,Constantes.altoBola);
		for (Jelly j : monsActivos) {
			Rectangle recJelly=new Rectangle(j.getx(),j.gety(),j.getancho()/2,j.getalto()/2);//entre dos porque el ancho no es el original
			if(recBola.intersects(recJelly)){
				j.setBandera(true);
				toremove.add(j);
				totalJellys--;
				puntuacion=puntuacion+10;
				Recursos.muerte.play();
			}
		}
		monsActivos.removeAll(toremove);//para evitar ConcurrentModificationException
		ventana.setP(puntuacion);	
		for (Pingubueno p1 : p1Activos) {
			Rectangle recp=new Rectangle(p1.getx(),p1.gety(),p1.getancho()/2,p1.getalto()/2);
			if(recBola.intersects(recp)){
				p1.setBandera(true);
				toremove3.add(p1);
				totalp1--;
				variacion++;
				Recursos.pingu1.play();
			}
		}
		for (Pingumalo p2 : p2Activos) {
			Rectangle recp=new Rectangle(p2.getx(),p2.gety(),p2.getancho()/2,p2.getalto()/2);
			if(recBola.intersects(recp)){
				p2.setBandera(true);
				toremove2.add(p2);
				totalp2--;
				variacion--;
				Recursos.pingu2.play();
			}
		}
	}
	
	public void cotroltiempo() {
		contadorBola=((contadorBola+1) %Constantes.vecesbola); 
		if(contadorBola==0) {
			bola.update();
		}
		contadorNextJ=((contadorNextJ+1) %Constantes.tiemponextjelly);
		if(contadorNextJ==0) {
			if(Constantes.maxjelly!=totalJellys && indice<mons.size()) {
				monsActivos.add(mons.get(indice));
				totalJellys++;
				indice++;
			}
		}	
		contadorRitmoJ=((contadorRitmoJ+1) %Constantes.vecesjelly); 
		if(contadorRitmoJ==0) {
			for (Jelly j : monsActivos) {
				j.update();
			}	
			for(Jelly jr : toremove) {
				if(!jr.getControlfin()) {
					jr.update();
				}else {
					arrayaux.add(jr);
				}
			}
		}
		toremove.removeAll(arrayaux);//para evitar ConcurrentModificationException
		
		contadorNextp1=((contadorNextp1+1) %Constantes.tiemponextp1);
		if(contadorNextp1==0) {
			if(totalp1!=1 && indice3<p2.size() && variacion<2) {
				p1Activos.add(p1.get(indice3));
				totalp1++;
				indice3++;
			}
		}
		contadorNextp2=((contadorNextp2+1) %Constantes.tiemponextp2);
		if(contadorNextp2==0) {
			if(totalp2!=1 && indice2<p2.size() && variacion>-2) {
				p2Activos.add(p2.get(indice2));
				totalp2++;
				indice2++;
			}
		}
		contadorRitmop=((contadorRitmop+1) %Constantes.vecesp); 
		if(contadorRitmop==0) {
			for (Pingubueno p1 : p1Activos) {
				if(!p1.isBandera()) {
					p1.update();
				}else {
					toremove3.add(p1);
				}
			}	
			for (Pingumalo p2 : p2Activos) {
				if(!p2.isBandera()) {
					p2.update();
				}else {
					toremove2.add(p2);
				}
			}	
		}
		p1Activos.removeAll(toremove3);
		p2Activos.removeAll(toremove2);
	}
	
	public void variaciones() {
		if(variacion==0) {
			if(bola.getspeedX()<0) {
				bola.setspeedX(-10);
			}else {
				bola.setspeedX(10);
			}
			if(bola.getspeedY()<0) {
				bola.setspeedY(-10);
			}else {
				bola.setspeedY(10);
			}
			
		}else if(variacion<0) {
			if(bola.getspeedX()<0) {
				bola.setspeedX(-10+(variacion*3));
			}else {
				bola.setspeedX(10+(-variacion*3));
			}
			if(bola.getspeedY()<0) {
				bola.setspeedY(-10+(variacion*3));
			}else {
				bola.setspeedY(10+(-variacion*3));
			}
		}else if(variacion>0) {
			if(bola.getspeedX()<0) {
				bola.setspeedX(-10-(-variacion*2));
			}else {
				bola.setspeedX(10-(variacion*2));
			}
			if(bola.getspeedY()<0) {
				bola.setspeedY(-10-(-variacion*2));
			}else {
				bola.setspeedY(10-(variacion*2));
			}
		}
	}
}