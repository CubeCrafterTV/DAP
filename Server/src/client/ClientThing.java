package client;

import java.io.OutputStream;
import java.net.Socket;

import server.ServerThing;

public class ClientThing {
	public static void main(String[] args) {
		new ClientThing();
	}
	public ClientThing() {
		Socket socket = null;
		try {
			System.out.println("CLIENT: Versuche Socket zu verbinden");
			//socket = new Socket("178.200.3.71",60001);
			socket = new Socket("localhost",60001);
			System.out.println("CLIENT: ClientSocket erstellt");
			OutputStream output = socket.getOutputStream();
			output.write(new String("hallo42").getBytes());
			output.flush();
			System.out.println("CLIENT: " + socket.getInputStream().read() + " ist zurückgekommen");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
