package server.networking;

import server.model.gameroommodel.ServerGameRoomModel;
import transferobjects.Message;
import transferobjects.Request;
import transferobjects.TicTacToePiece;
import util.GameRoomModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketServerHandler implements Runnable, PropertyChangeListener {

	private Socket socket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private ServerGameRoomModel serverGameRoomModel;
	private SocketServer socketServer;

	public SocketServerHandler(Socket socket, SocketServer socketServer) throws IOException {
		this.socket = socket;
		outToClient = new ObjectOutputStream(this.socket.getOutputStream());
		inFromClient = new ObjectInputStream(this.socket.getInputStream());
		this.socketServer = socketServer;

	}

	@Override
	public void run() {

		while (true) {

			try {

				Object obj = receiveTransferObject();
				handleReceivedObject(obj);

			} catch (SocketException e) {
				System.out.println("User Disconnected");
				break;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}

		}

	}

	public void sendTransferObject(Object object) throws IOException {
		outToClient.writeObject(object);
	}

	public void setServerGameRoomModel(GameRoomModel gameRoomModel) {
		this.serverGameRoomModel = (ServerGameRoomModel) gameRoomModel;
	}

	public void requestBroadcast(Object object) {
		try {
			socketServer.broadcast(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleMessage(Message message) {
		if (message.getTarget().equals("Lobby")) {
			socketServer.addMessage(message);
		} else {
			serverGameRoomModel.addMessage(message);
		}

	}

	private void handleRequest(Request request) {

		switch (request.getType()) {
			case "Login" -> socketServer.loginPlayer((String) request.getArg());
			case "Host" -> {
//				System.out.println("SocketServerHandler received hos request");
				socketServer.createGameRoom(this, (String) request.getArg());
			}
			case "Join" -> {
//				System.out.println("SocketServerHandler received join request");
				socketServer.joinGameRoom(this, (Integer) request.getArg(), (String) request.getArg2());
			}
			case "place" -> {
//				System.out.println("Server received a place piece request");
				serverGameRoomModel.placePiece((TicTacToePiece) request.getArg());
			}
			case "update" -> socketServer.getServerData(this);
		}

	}

	private void handleReceivedObject(Object obj) {
		if (obj instanceof Request) {
			handleRequest((Request) obj);

		} else if (obj instanceof Message) {
			handleMessage((Message) obj);
		}

	}

	public Object receiveTransferObject() throws IOException, ClassNotFoundException {
		return inFromClient.readObject();

	}

	public ServerGameRoomModel getServerGameRoomModel() {
		return serverGameRoomModel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			String eventType = evt.getPropertyName();

			switch (eventType) {
				case "piecePlaced", "win", "draw", "turnSwitch" -> sendTransferObject(new Request(eventType, evt.getNewValue()));
				case "gameRoomDel" -> requestBroadcast(new Request(eventType, evt.getNewValue()));
			}
			if (eventType.equals("messageAdded")) {
				Message message = (Message) evt.getNewValue();
				if (message.getTarget().equals("Lobby")) {
					requestBroadcast(evt.getNewValue());
				} else {
					sendTransferObject(evt.getNewValue());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
