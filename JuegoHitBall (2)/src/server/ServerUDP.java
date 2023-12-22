package server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import assets.Constantes;
import model.Movimiento;

public class ServerUDP extends Thread{
	
	private ArrayList<Player> players;
    private boolean conectado = true;
    
    private byte[] enviados;
    private byte[] recibidos;
    private DatagramSocket socketUDP = null;
	private Movimiento movimiento;
	private DatagramPacket dprecibo;
	private DatagramPacket dpenvio;
	
	public ServerUDP(ArrayList<Player> players) {
		this.players = players;
		// Creamos socket para comunicar
		try {
			socketUDP = new DatagramSocket(Constantes.PORTSERVER2);//al conectarse una segunda persona BindException porque ya hay un proceso en ese puerto
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {//recibimos mensajes de los clientes
		while(conectado) {
			recibidos = new byte[1024];
			dprecibo = new DatagramPacket(recibidos, recibidos.length);
			try {
				socketUDP.receive(dprecibo);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(recibidos);
				ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
				movimiento=(Movimiento) is.readObject();
				is.close();
			} catch (IOException e) {
				conectado=false;
				e.printStackTrace();
				continue;
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (isJugador(dprecibo.getAddress().getHostAddress())) {
				if (movimiento.getJugador()==-1) {break;}
				enviar(movimiento);	
			} else continue;
			
		}	
		socketUDP.close();
	}
	
	private boolean isJugador(String host) {
		for (Player player:players) {
			String hostAdddress = player.getClienteSocket().getInetAddress().getHostAddress();
			if ( hostAdddress.equals(host)) { 
				return true;
			}
		}
		return false;
	}
		
	private void enviar(Movimiento movimiento) {//enviamos en movimiento a todos los clientes
		for (Player player:players) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(movimiento);
				enviados = baos.toByteArray();
				dpenvio= new DatagramPacket(enviados, enviados.length,player.getClienteSocket().getInetAddress(),Constantes.PORTCLIENT);
				socketUDP.send(dpenvio);
				oos.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}    
}
