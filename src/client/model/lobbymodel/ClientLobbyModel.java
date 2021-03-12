package client.model.lobbymodel;

import client.networking.Client;
import transferobjects.Message;
import util.LobbyModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClientLobbyModel implements LobbyModel, PropertyChangeListener {
	private Client client;
	private PropertyChangeSupport support;

	public ClientLobbyModel(Client client) {
		support = new PropertyChangeSupport(this);
		this.client = client;
		this.client.addListener("gameRoomAdd", this);
		this.client.addListener("gameRoomDel", this);
		this.client.addListener("messageAddedLobby", this);
	}

	public void host() {
		client.hostGame();
	}

	@Override
	public void join(Object obj, int roomId) {
//		System.out.println("lobbyModelCAll joinGame on client");
		client.joinGame(roomId);
	}

	@Override
	public void sendMessage(Message message) {
		client.sendMessage(message);
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
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("ClientLobby Model detect change" + evt.getPropertyName());
		support.firePropertyChange(evt);
	}

}
