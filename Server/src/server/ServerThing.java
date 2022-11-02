package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class ServerThing {
	private class ClientManager extends Thread{
		private ArrayList<ClientConnection> connections;
		public ClientManager(ArrayList<ClientConnection> pConnections) {
			connections = pConnections;
		}
		public void run() {
			while(true) {
				for(int i = 0; i < connections.size(); i++) {
					//TODO Socket Inhalte auslesen
					connections.get(i);
				}
			}
		}
	}
	private class ClientConnection extends Thread{
		private Socket socket;
		public ClientConnection(Socket pSocket) {
			socket = pSocket;
		}
		public void run() {
			while(true) {
				try {
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	private ServerSocket socket;
	private ArrayList<ClientConnection> connections;
	private ClientManager clientmanager;
	private ServerGUI gui;
	public ServerThing(ServerGUI pGui) {
		try {
			socket = new ServerSocket(60001);
			gui = pGui;
			gui.updateText(getTime() + " III running...");
			connections = new ArrayList<ClientConnection>();
			clientmanager = new ClientManager(connections);
			clientmanager.run();
			run();
		} catch(IOException e) {
			System.out.println("Problem bei ServerSocketErstellung");
			return;
		}
	}
	public void run() {
		while(true) {
			try {
				Socket connection = socket.accept();
				ClientConnection client = new ClientConnection(connection);
				client.start();
				connections.add(client);
				processSocket(connection);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void processSocket(Socket connection) throws IOException {
		gui.updateText(getTime() + " III " + connection.getInetAddress().toString() + ": " + "Socket Connected");
		int message = connection.getInputStream().read();
		gui.updateText(getTime() + " III " + connection.getInetAddress() + ": " + message + " wurde empfangen");
		OutputStream output = connection.getOutputStream();
		output.write(69);
		output.flush();
		gui.updateText(getTime() + " III " + connection.getInetAddress() + ": " + 69 + " wurde gesendet");
	}
	protected String[] readSocket(Socket socket) {
		return null;
	}
	private String getTime() {
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
	}
}
