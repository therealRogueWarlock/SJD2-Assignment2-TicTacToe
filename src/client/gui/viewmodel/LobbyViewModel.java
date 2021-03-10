package client.gui.viewmodel;

import client.model.lobbymodel.ClientLobbyModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.LobbyModel;

import java.beans.PropertyChangeEvent;


public class LobbyViewModel implements ViewModel {

	private StringProperty txtMessage;

	private ClientLobbyModel clientLobbyModel;

	public LobbyViewModel(LobbyModel lobbyModel) {
		this.clientLobbyModel = (ClientLobbyModel) lobbyModel;
		txtMessage = new SimpleStringProperty();
	}

	public void join() {

	}

	public void host() {

	}

	public void sendMessage() {

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}
}
