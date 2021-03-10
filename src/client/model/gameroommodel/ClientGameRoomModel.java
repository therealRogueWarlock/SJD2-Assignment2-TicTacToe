package client.model.gameroommodel;

import util.GameRoomModel;
import client.networking.Client;
import transferobjects.Message;

public class ClientGameRoomModel implements GameRoomModel {

	private Client client;


	public ClientGameRoomModel(Client client) {
		this.client = client;
	}

	public void placePiece() {

	}

	@Override
	public void sendMessage(Message message) {

	}

	public void sendMessage(String msg) {

	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
