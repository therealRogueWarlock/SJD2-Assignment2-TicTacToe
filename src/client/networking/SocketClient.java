package client.networking;


import transferobjects.Message;

public class SocketClient implements Client {

	private String clientName;

	private Client client;

	private SocketClientHandler socketClientHandler;



	public void start() {

	}


	public void sendMessage(Message message) {

	}



	public void joinGame(String playerName) {

	}



	public void hostGame() {

	}


	public void setClientName() {

	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
