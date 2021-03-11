package client.model.loginmodel;

import client.networking.Client;
import util.LoginModel;

import java.beans.PropertyChangeListener;

public class ClientLoginModel implements LoginModel {

	private Client client;

	public ClientLoginModel(Client client) {
		this.client = client;
	}

	@Override
	public void login(String playerName) {
		client.setClientName(playerName);
		client.start();
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {

	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {

	}
}
