package chess.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server for communication between clients
 * Singleton
 */
public class Server {

	private Boolean running;

	private final ServerSocket serverSocket;

	private Client client = null;

	private List<Thread> threads = new ArrayList<>();

	/**
	 * Default constructor for Server
	 *
	 * @param port Port to listen on
	 * @throws IOException throws exception when error has occurred
	 */
	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		System.out.println("Listening on port " + port);
		this.running = true;
	}


	/**
	 * Function to run server.
	 */
	public void run() {
		try {
			System.out.println("Waiting for client");
			Socket socket = serverSocket.accept();
			if (client != null) {
				throw new IOException("Unknown error");
			}
			this.client = new Client(socket);
			Thread thread = new Thread(this.client);
			threads.add(thread);
			thread.start();
			System.out.println("Client created");

		} catch (IOException e) {
			e.printStackTrace();
			try {
				serverSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}
