package server;

import java.net.Socket;

public class Player {
	
	private Socket clienteSocket;
	private int nick;
	
	public Player(Socket clienteSocket, int nick) {
		super();
		this.clienteSocket = clienteSocket;
		this.nick = nick;
	}


	public Socket getClienteSocket() {
		return clienteSocket;
	}


	public void setClienteSocket(Socket clienteSocket) {
		this.clienteSocket = clienteSocket;
	}


	public int getNick() {
		return nick;
	}


	public void setNick(int nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "Cliente [clienteSocket=" + clienteSocket + ", nick=" + nick + "]";
	}
		
}
