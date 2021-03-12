package server.networking;

import server.model.lobbymodel.ServerLobbyModel;
import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer implements PropertyChangeListener {
	private ServerLobbyModel serverLobbyModel;
	private ArrayList<SocketServerHandler> socketServerHandlers;

	public SocketServer() {
		socketServerHandlers = new ArrayList<>();
		serverLobbyModel = new ServerLobbyModel();
	}

	public void start() throws IOException {

		try (ServerSocket serverSocket = new ServerSocket(1235)) {
			System.out.println("Server running");
			serverLobbyModel.addListener("gameRoomAdd", this);
			serverLobbyModel.addListener("messageAdded", this);

			while (true) {

				Socket socket = serverSocket.accept();
				SocketServerHandler socketServerHandler = new SocketServerHandler(socket, this);

				socketServerHandlers.add(socketServerHandler);

				Thread socketThread = new Thread(socketServerHandler);
				socketThread.start();

			}

		}
	}

	public void loginPlayer(String playerName) {
		serverLobbyModel.addPlayer(playerName);
	}

	public void createGameRoom(SocketServerHandler socketServerHandler, String playerName) {
		serverLobbyModel.host(socketServerHandler, playerName);
	}

	public void run() {
		try {
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void joinGameRoom(SocketServerHandler socketServerHandler, int roomId, String playerName) {
//        System.out.println("SocketServer call join on server lobby model");
		serverLobbyModel.join(socketServerHandler, roomId, playerName);

	}

	public void addMessage(Message message) {
		serverLobbyModel.addMessage(message);
	}

	public void broadcast(Object obj) throws IOException {
		for (SocketServerHandler socketServerHandler : socketServerHandlers) {
			socketServerHandler.sendTransferObject(obj);
		}
	}

	public ServerLobbyModel getServerLobbyModel() {
		return serverLobbyModel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		System.out.println("SocketServer " + evt.getSource());

		try {
			String eventType = evt.getPropertyName();
			// gameRoomDel bliver stadig detected af socketServerHandler
			switch (eventType) {
				case "messageAdded" -> broadcast(evt.getNewValue());
				case "gameRoomAdd", "gameRoomDel" -> broadcast(new Request(eventType, evt.getNewValue()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
