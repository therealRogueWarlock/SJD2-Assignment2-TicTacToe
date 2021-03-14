package client.networking;

import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class SocketClient implements Client {

	private PropertyChangeSupport support;

	private String clientName;

	private SocketClientHandler socketClientHandler;

	@Override
	public void start() {
		support = new PropertyChangeSupport(this);

		try {

			Socket socket = new Socket("localhost", 1235);
			socketClientHandler = new SocketClientHandler(socket, this);

			Thread socketClientHandlerThread = new Thread(socketClientHandler);

			socketClientHandlerThread.setDaemon(true);
			socketClientHandlerThread.start();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void handleReceivedRequest(Request requestFromServer) {
		System.out.println("SocketClient Recived Request " + requestFromServer.getType() + " , fire property change");
		support.firePropertyChange(requestFromServer.getType(), null, requestFromServer);
	}

	public void handleReceivedMessage(Message message) {
		if (message.getTarget().equals("Lobby")) {
			support.firePropertyChange("messageAddedLobby", null, message);
		} else {
			support.firePropertyChange("messageAddedGameRoom", null, message);
		}

	}

	public String getClientName() {
		return clientName;
	}

	@Override
	public void sendMessage(Message message) {
		message.setName(clientName);
		socketClientHandler.sendTransferObject(message);
	}

	@Override
	public void joinGame(int roomId) {
//		System.out.println("SocketClient ask socketClient handler to send join request");
		socketClientHandler.sendJoinRequest(roomId);
	}

	@Override
	public void hostGame() {
//		System.out.println("Socket client ask socketClient handler to send hos request.");
		socketClientHandler.sendHostRequest();
	}

	public void update(){
		socketClientHandler.sendUpdateRequest();
	}

	@Override
	public void setClientName(String name) {
		clientName = name;
	}

	@Override
	public void sendRequest(Request request) {
//		System.out.println("Client ask clientHanlder to send object to server");
		socketClientHandler.sendTransferObject(request);
	}

	@Override
	public String getName() {
		return getClientName();
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

}
