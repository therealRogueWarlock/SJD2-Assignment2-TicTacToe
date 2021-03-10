package client.model.loginmodel;

import util.LoginModel;
import client.networking.Client;

public class ClientLoginModel implements LoginModel {

	private Client client;


	public ClientLoginModel(Client client) {
		this.client = client;
	}

	@Override
	public void login(String playerName) {


	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
