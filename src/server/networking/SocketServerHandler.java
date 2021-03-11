package server.networking;

import server.model.gameroommodel.ServerGameRoomModel;
import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketServerHandler implements Runnable, PropertyChangeListener {

	private Socket socket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private ServerGameRoomModel serverGameRoomModel;
	private SocketServer socketServer;

	public SocketServerHandler(Socket socket, SocketServer socketServer) throws IOException {
		this.socket = socket;
		outToClient = new ObjectOutputStream(socket.getOutputStream());
		inFromClient = new ObjectInputStream(socket.getInputStream());
		this.socketServer = socketServer;

	}

	@Override
	public void run() {

		while (true) {

			try {

				Object obj = receiveTransferObject();
				handleReceivedObject(obj);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}

		}

	}

	public void sendTransferObject(Request request) throws IOException {
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

		if (request.getType().equals("Login")) {
			socketServer.loginPlayer((String) request.getArg());
		}

		if (request.getType().equals("Host")) {
			socketServer.createGameRoom((String) request.getArg());
		}

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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			if (evt.getNewValue() instanceof ServerGameRoomModel) {
				sendTransferObject(new Request(evt.getPropertyName(), ((ServerGameRoomModel) evt.getNewValue()).getRoomId()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
