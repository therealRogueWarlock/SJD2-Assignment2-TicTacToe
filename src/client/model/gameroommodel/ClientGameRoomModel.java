package client.model.gameroommodel;

import client.networking.Client;
import transferobjects.Message;
import transferobjects.Request;
import transferobjects.TicTacToePiece;
import util.GameRoomModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClientGameRoomModel implements GameRoomModel, PropertyChangeListener {
	private PropertyChangeSupport support;
	private Client client;

	public ClientGameRoomModel(Client client) {
		this.client = client;
		support = new PropertyChangeSupport(this);

		this.client.addListener("piecePlaced", this);
		this.client.addListener("win", this);
		this.client.addListener("draw", this);
		this.client.addListener("turnSwitch", this);
		this.client.addListener("messageAddedGameRoom", this);

	}

	@Override
	public void placePiece(TicTacToePiece ticTacToePiece) {
		System.out.println("game room model ask client to send request");
		ticTacToePiece.setPiece(client.getName());
		client.sendRequest(new Request("place", ticTacToePiece));

	}

	@Override
	public void sendMessage(Message msg) {
		client.sendMessage(msg);
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

	@Override
	public int getRoomId() {
		return 0;
	}

	@Override
	public String getPlayerCount() {
		return ";";
	}

	@Override
	public String getPlayerNames() {
		return "";
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("game room model recieved evet: " + evt.getPropertyName());
		support.firePropertyChange(evt);
	}
}
