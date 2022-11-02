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
			socket = new Socket("192.168.0.210",60001);
			System.out.println("CLIENT: ClientSocket erstellt");
			OutputStream output = socket.getOutputStream();
			output.write(42);
			output.flush();
			System.out.println("CLIENT: " + socket.getInputStream().read() + " ist zurückgekommen");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
