package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import assets.Constantes;

public class ConexionTCP  extends Thread{

	private ServerSocket serverSocket;
	private Socket clienteSocket;
	private Player cliente;
	public 	ArrayList<Player> totalclientes;
	private ServerUDP server;
	private int contplayer=0,numplayer;
	private ArrayList<Integer> list= new ArrayList<Integer>(); 
	private PrintWriter pw;
	
	public static void main(String[] args) {
		ConexionTCP conex = new ConexionTCP();
	}
	
	
	public ConexionTCP() {//Nos encargamos de conectar los clientes al server
		for (int i = 1; i <= Constantes.maxJugadores; i++) {
			list.add(i);
		}
		try {
			serverSocket = new ServerSocket(Constantes.PORTSERVER);
			System.out.println("Servidor Iniciado.");
			totalclientes = new ArrayList<Player>();
		} catch (IOException e) {
			System.out.println("Imposible iniciar Server....");
		}
		setName("Server");
		start();
	}


	@Override
	public void run() {
		Collections.shuffle(list);//Cada jugador tendra un personaje aleatorio
		while (contplayer < Constantes.maxJugadores) {
			numplayer=list.get(contplayer);
			System.out.println("A la espera");
			try {
				clienteSocket = serverSocket.accept();	
				contplayer++;
				cliente=new Player(clienteSocket,numplayer);//con una lista de socket creo que habria bastado
				totalclientes.add(cliente);
				enviar(("Eres:"+numplayer),cliente.getClienteSocket());
								
				System.out.println("Cliente "+numplayer+" con la IP " + clienteSocket.getInetAddress().getHostName() + " conectado.");
								
				enviarall(("Esperando jugadores... "+ contplayer+"/"+Constantes.maxJugadores));
				
			} catch (IOException e) {
				e.printStackTrace();
				break; // Problemas => a la calle
			}
		}
		enviarall(("Listos los " + Constantes.maxJugadores + " jugagores. Comenzamos en 10 segundos..."));			
		try {
			Thread.sleep(10000);
			server = new ServerUDP(totalclientes);
			server.start();
			enviarall("Capturados:");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void enviar(String msg, Socket socket) {
		try {
			pw = new PrintWriter(socket.getOutputStream());
			pw.write(msg + "\n");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void enviarall(String msg) {
		for (Player p:totalclientes) {
			try {
				pw = new PrintWriter(p.getClienteSocket().getOutputStream());
				pw.write(msg + "\n");
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

}