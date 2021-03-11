package client.networking;

import transferobjects.Message;
import transferobjects.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientHandler implements Runnable {
	private Socket socket;
	private SocketClient socketClient;
	private ObjectOutputStream outToServer;
	private ObjectInputStream inFromServer;

	public SocketClientHandler(Socket socket, SocketClient socketClient) throws IOException {
		this.socket = socket;
		this.socketClient = socketClient;
		outToServer = new ObjectOutputStream(socket.getOutputStream());
		inFromServer = new ObjectInputStream(socket.getInputStream());
	}

	public void sendTransferObject(Object object) {
		try {
			outToServer.writeUnshared(object);
		} catch (IOException e) {
			System.out.println("SocketClientHandler - sendTransferObject(Object object)\n" + e.getMessage());
		}
	}

	public void receiveTransferObject() {
		// TODO: Unsure of this one

	}



	public void handleTransferObject(Object itemFromServer) {

		if (itemFromServer instanceof Request) {
			socketClient.handleReceivedRequest((Request) itemFromServer); // FIXME: Unsure
		} else if (itemFromServer instanceof Message) {
			socketClient.handleReceivedMessage((Message) itemFromServer); // FIXME: Unsure
		}

	}

	@Override
	public void run() {
		Object itemFromServer;

		String loginName = socketClient.getClientName();
		sendTransferObject(new Request("Login", loginName));


		while (true) { // FIXME: Reevaluate use of infinite loop

			try {

				itemFromServer = inFromServer.readUnshared();
				handleTransferObject(itemFromServer);

			} catch (IOException | ClassNotFoundException e) {
				System.out.println("SocketClientHandler - run()\n" + e.getMessage());
				break;
			}

		}
	}

	public void sendHostRequest(){
		sendTransferObject(new Request("Host", socketClient.getClientName()));
	}


}
