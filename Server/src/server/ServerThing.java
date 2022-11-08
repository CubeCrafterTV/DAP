package server;

import utilities.Utills;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
public class ServerThing extends Thread{
	private class ClientManager extends Thread{
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
			public Socket getSocket() {
				return socket;
			}
		}
		private ArrayList<ClientConnection> connections;
		private ServerGUI gui;
		public ClientManager(ServerGUI pGui) {
			connections = new ArrayList<ClientConnection>();
			gui = pGui;
			gui.updateText(Utills.getTime() + "    running...");
			start();
		}
		public void run() {
			while(true) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] incomingMessages = getMessages();
				for(int i = 0; i < incomingMessages.length; i++) {
					if(!incomingMessages[i].contentEquals("")) {
						gui.updateText(Utills.getTime() + "    " + incomingMessages[i] + " von " +
										connections.get(i).getSocket().getInetAddress() + " gesendet");
						clientmanager.send(incomingMessages[i], i);
						
						gui.updateText(Utills.getTime() + "    " + incomingMessages[i] + " an " +
								connections.get(i).getSocket().getInetAddress() + " gesendet");
					}
				}
			}
		}
		public void add(Socket socket) {
			ClientConnection client = new ClientConnection(socket);
			connections.add(client);
			client.start();
		}
		public String[] getMessages() {
			String[] messages = new String[connections.size()];
			for(int i = 0; i < connections.size(); i++) {
				messages[i] = "";
				InputStream inputReader;
				try {
					//Alternative Idee
					//message byte array fixte große größe machen und die größe der Nachricht dann von result ablesen
					inputReader = connections.get(i).getSocket().getInputStream();
					byte[] message = new byte[inputReader.available()];
					int result = inputReader.read(message);
					if(result==-1) message=new String("\"\"").getBytes();
					messages[i] = new String(message);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			return messages;
		}
		public void send(String message, int target) {
			try {
				OutputStream output = connections.get(target).getSocket().getOutputStream();
				output.write(message.getBytes());
				output.flush();
			} catch (IOException e) {
				System.out.println("Senden hat nich funktioniert");
				e.printStackTrace();
			}
		}
	}
	private ServerSocket socket;
	private ClientManager clientmanager;
	public ServerThing(ServerGUI pGui) {
		try {
			socket = new ServerSocket(60001);
			clientmanager = new ClientManager(pGui);
			clientmanager.start();
			run();
		} catch(IOException e) {
			System.out.println("Problem bei ServerSocketErstellung");
			pGui.end();
			return;
		}
	}
	public void run() {
		while(true) {
			try {
				System.out.println("runnung waiting for connection");
				clientmanager.add(socket.accept());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}	
}

