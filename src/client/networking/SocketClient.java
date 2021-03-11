package client.networking;

import transferobjects.Message;
import transferobjects.Request;

import java.io.IOException;
import java.net.Socket;

public class SocketClient implements Client {
	private String clientName;
	private Client client;

	private SocketClientHandler socketClientHandler;

	public void start() {
		try (Socket socket = new Socket("localhost", 69420)) {
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

	@Override
	public void sendRequest(Request request) {
		socketClientHandler.sendTransferObject(request);
	}

	@Override
	public void addListener(String propertyName, int listener) { // FIXME: int listener?

	}

	@Override
	public void removeListener(String propertyName, int listener) { // FIXME: int listener?

	}
}
