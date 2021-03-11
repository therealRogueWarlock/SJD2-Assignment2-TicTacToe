package client.gui.viewmodel;

import client.model.lobbymodel.ClientLobbyModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transferobjects.Message;
import util.GameRoomModel;
import util.LobbyModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LobbyViewModel implements ViewModel, PropertyChangeListener {

	private StringProperty txtMessage;

	private ClientLobbyModel clientLobbyModel;

	private ObservableList<GameRoomModel> observableGameRooms;



	public LobbyViewModel(LobbyModel lobbyModel) {
		this.clientLobbyModel = (ClientLobbyModel) lobbyModel;
		txtMessage = new SimpleStringProperty();

		observableGameRooms = FXCollections.observableArrayList();

		clientLobbyModel.addListener("gameRoomAdd",this);


	}


	public ObservableList<GameRoomModel> getObservableGameRooms() {
		return observableGameRooms;
	}




	public void join() {
		// TODO: Serveren skal give noget, som clienten skal kobles op til
		// - Tror umiddlebart vi stadig bare skal sende en join request ned
	}

	public void host() {
		clientLobbyModel.host();


		// TODO: Server skal lave et game, som clienten skal kobles op til
		// - Tror umiddlebart vi stadig bare skal sende en host request ned
	}

	public void sendMessage(Message message) {
		clientLobbyModel.sendMessage(message);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("gameRoomAdd")){
			observableGameRooms.addAll((GameRoomModel) evt.getNewValue());
		}
	}
}
