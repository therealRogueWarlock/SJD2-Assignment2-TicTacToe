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
		System.out.println("Send object to server");
		try {
			outToServer.writeUnshared(object);
		} catch (IOException e) {
			System.out.println("ERROR: SocketClientHandler - sendTransferObject(Object object)\n" + e.getMessage());
		}
	}

	public void receiveTransferObject() {
		// TODO: Unsure of this one

	}



	public void handleTransferObject(Object itemFromServer) {

		if (itemFromServer instanceof Request) {
			System.out.println("ask socket Client to handle request");
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

		System.out.println(loginName + " Logged in, listening to server");
		while (true) { // FIXME: Reevaluate use of infinite loop

			try {

				itemFromServer = inFromServer.readUnshared();
				System.out.println("Got object from server");
				System.out.println("Handel object");
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


	public void sendJoinRequest(int roomId) {
		System.out.println("Send join request to room"+ roomId);
		sendTransferObject(new Request("Join", roomId));
	}
}
