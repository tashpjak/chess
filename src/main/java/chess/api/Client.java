package chess.api;

import java.io.*;
import java.net.Socket;

/**
 * Class to represent connected client
 */
public class Client implements Runnable {

	private PrintWriter output;
	private BufferedReader input;
	private Socket socket;

	public Client(final Socket socket) throws IOException {

		output = new PrintWriter(socket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.socket = socket;
	}

	@Override
	public void run() {
		while(this.socket.isConnected()) {
			try {
				String line = input.readLine();
				System.out.println(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
