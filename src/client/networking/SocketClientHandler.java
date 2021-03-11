package client.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientHandler implements Runnable {
	private Socket socket;
	private SocketClient socketClient;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public SocketClientHandler(Socket socket, SocketClient socketClient) throws IOException {
		this.socket = socket;
		this.socketClient = socketClient;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	public void sendTransferObject(Object object) {
		try {
			out.writeUnshared(object);
		} catch (IOException e) {
			System.out.println("SocketClientHandler - sendTransferObject(Object object)\n" + e.getMessage());
		}
	}

	public void receiveTransferObject() {
		// TODO: Unsure of this one
	}

	public void handleTransferObject(Object itemFromServer) {
//		if (itemFromServer instanceof Request) {
//			socketClient.handleReceivedRequest((Request) itemFromServer); // FIXME: Unsure
//		} else if (itemFromServer instanceof Message) {
//			socketClient.handleReceivedMessage((Message) itemFromServer); // FIXME: Unsure
//		}
	}

	@Override
	public void run() {
		Object itemFromServer;
		while (true) { // FIXME: Reevaluate use of infinite loop
			try {
				itemFromServer = in.readUnshared();
				handleTransferObject(itemFromServer);
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("SocketClientHandler - run()\n" + e.getMessage());
			}
		}
	}
}
