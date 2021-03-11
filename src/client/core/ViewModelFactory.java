package client.core;

import client.gui.viewmodel.GameRoomViewModel;
import client.gui.viewmodel.LobbyViewModel;
import client.gui.viewmodel.LoginViewModel;

public class ViewModelFactory {

	private ModelFactory modelFactory;

	private LoginViewModel loginViewModel;
	private LobbyViewModel lobbyViewModel;
	private GameRoomViewModel gameRoomViewModel;

	public ViewModelFactory(ModelFactory modelFactory) {
		this.modelFactory = modelFactory;
	}

	public LoginViewModel getLoginViewModel() {
		if (loginViewModel == null) {
			loginViewModel = new LoginViewModel(modelFactory.getClientLoginModel());
		}
		return loginViewModel;
	}

	public LobbyViewModel getLobbyViewModel() {

		if (lobbyViewModel == null) {
			lobbyViewModel = new LobbyViewModel(modelFactory.getClientLobbyModel());
		}

		return lobbyViewModel;

	}

	public GameRoomViewModel getGameRoomViewModel() {

		if (gameRoomViewModel == null) {
			gameRoomViewModel = new GameRoomViewModel(modelFactory.getClientGameRoomModel());
		}

		return gameRoomViewModel;
	}

}
