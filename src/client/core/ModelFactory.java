package client.core;

import client.model.gameroommodel.ClientGameRoomModel;
import client.model.lobbymodel.ClientLobbyModel;
import client.model.loginmodel.ClientLoginModel;

public class ModelFactory {

	private ClientLoginModel clientLoginModel;
	private ClientLobbyModel clientLobbyModel;
	private ClientGameRoomModel clientGameRoomModel;
	private ClientFactory clientFactory;

	public ModelFactory(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public ClientLoginModel getClientLoginModel() {

		if (clientLoginModel == null) {
			clientLoginModel = new ClientLoginModel(clientFactory.getClient());
		}

		return clientLoginModel;
	}

	public ClientLobbyModel getClientLobbyModel() {

		if (clientLobbyModel == null) {
			clientLobbyModel = new ClientLobbyModel(clientFactory.getClient());
		}

		return clientLobbyModel;
	}

	public ClientGameRoomModel getClientGameRoomModel() {

		if (clientGameRoomModel == null) {
			clientGameRoomModel = new ClientGameRoomModel(clientFactory.getClient());
		}
		return clientGameRoomModel;
	}

}
