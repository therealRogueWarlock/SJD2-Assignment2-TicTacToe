package client.model.loginmodel;

import client.networking.Client;
import util.LoginModel;

public class ClientLoginModel implements LoginModel {

	private Client client;

	public ClientLoginModel(Client client) {
		this.client = client;
	}

	@Override
	public void login(String playerName) {
		client.setClientName(playerName);
	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
