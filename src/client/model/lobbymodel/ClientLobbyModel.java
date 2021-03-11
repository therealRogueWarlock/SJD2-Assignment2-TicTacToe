package client.model.lobbymodel;

import client.networking.Client;
import transferobjects.Message;
import util.LobbyModel;

public class ClientLobbyModel implements LobbyModel {

	private Client client;

	public ClientLobbyModel(Client client) {
		this.client = client;
	}

	public void join() {

	}

	public void host() {

	}

	public void sendMessage(String msg) {
		client.sendMessage(new Message(msg));
	}

	@Override
	public void sendMessage(Message message) {
		client.sendMessage(message);
	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
