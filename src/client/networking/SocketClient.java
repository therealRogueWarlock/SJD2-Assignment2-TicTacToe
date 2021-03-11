package client.networking;

import transferobjects.Message;
import transferobjects.Request;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;

public class SocketClient implements Client {

	private String clientName;

	private SocketClientHandler socketClientHandler;

	public void start() {

		try {

			Socket socket = new Socket("localhost", 1235);
			socketClientHandler = new SocketClientHandler(socket, this);

			Thread socketClientHandlerThread = new Thread(socketClientHandler);

			socketClientHandlerThread.setDaemon(true); // FIXME: Needed?
			socketClientHandlerThread.start();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void sendMessage(Message message) {
		socketClientHandler.sendTransferObject(message);
	}

	public void joinGame(String playerName) {

	}

	public void hostGame() {

	}


	public void setClientName(String name) {
		clientName = name;
	}


	public String getClientName(){
		return clientName;
	}


	@Override
	public void sendRequest(Request request) {
		socketClientHandler.sendTransferObject(request);
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {

	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {

	}
}
