package client.gui.viewmodel;

import client.model.lobbymodel.ClientLobbyModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import transferobjects.Message;
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
		// TODO: Serveren skal give noget, som clienten skal kobles op til
		// - Tror umiddlebart vi stadig bare skal sende en join request ned
	}

	public void host() {
		// TODO: Server skal lave et game, som clienten skal kobles op til
		// - Tror umiddlebart vi stadig bare skal sende en host request ned
	}

	public void sendMessage(Message message) {
		clientLobbyModel.sendMessage(message);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}
}
