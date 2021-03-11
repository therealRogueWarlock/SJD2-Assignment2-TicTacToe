package server.networking;

import server.model.gameroommodel.ServerGameRoomModel;
import server.model.lobbymodel.ServerLobbyModel;
import transferobjects.Message;
import transferobjects.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketServerHandler implements Runnable {

	private Socket socket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private ServerGameRoomModel serverGameRoomModel;
	private ServerLobbyModel serverLobbyModel;

	public SocketServerHandler(Socket socket, ServerLobbyModel serverLobbyModel) throws IOException {
		this.socket = socket;
		outToClient = new ObjectOutputStream(socket.getOutputStream());
		inFromClient = new ObjectInputStream(socket.getInputStream());
		this.serverLobbyModel = serverLobbyModel;
	}

	@Override
	public void run() {

		while (true) {

			try {

				Object obj = receiveTransferObject();
				handleReceivedObject(obj);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	public void sendRequest(Request request) throws IOException {
		outToClient.writeObject(request);
	}

	public Object receiveTransferObject() throws IOException, ClassNotFoundException {
		return inFromClient.readObject();

	}

	private void handleReceivedObject(Object obj) {
		if (obj instanceof Request) {
			handleRequest((Request) obj);

		} else if (obj instanceof Message) {
			handleMessage((Message) obj);
		}

	}

	private void handleRequest(Request request) {
		// TODO: check request types
	}

	private void handleMessage(Message message) {

		// TODO: check where the message should go

	}

	public void setServerGameRoomModel(ServerGameRoomModel serverGameRoomModel) {
		this.serverGameRoomModel = serverGameRoomModel;
	}

	public ServerGameRoomModel getServerGameRoomModel() {
		return serverGameRoomModel;
	}

}
