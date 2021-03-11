package client.model.gameroommodel;

import client.networking.Client;
import transferobjects.Message;
import transferobjects.Request;
import transferobjects.TicTacToePiece;
import util.GameRoomModel;

import java.beans.PropertyChangeListener;


public class ClientGameRoomModel implements GameRoomModel {

	private Client client;

	public ClientGameRoomModel(Client client) {
		this.client = client;
	}

	@Override
	public void placePiece(int x, int y, char piece) {
		client.sendRequest(new Request("place", new TicTacToePiece(x, y, piece)));
	}

	@Override
	public void sendMessage(Message msg) {
		client.sendMessage(msg);
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {

	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {

	}

}
