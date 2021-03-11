package client.gui.viewmodel;

import client.model.lobbymodel.ClientLobbyModel;
import client.model.lobbymodel.tabelobjects.GameData;
import client.model.lobbymodel.tabelobjects.GameTableRow;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transferobjects.Message;
import util.GameRoomModel;
import util.LobbyModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LobbyViewModel implements ViewModel, PropertyChangeListener {


	private ClientLobbyModel clientLobbyModel;

	private ObservableList<GameTableRow> observableGameRooms;
	private ObjectProperty<GameTableRow> selectedGameRoom;


	private StringProperty txtMessage;
	private ObservableList<String> lobbyChatMessages;


	public LobbyViewModel(LobbyModel lobbyModel) {
		this.clientLobbyModel = (ClientLobbyModel) lobbyModel;
		txtMessage = new SimpleStringProperty();

		observableGameRooms = FXCollections.observableArrayList();
		selectedGameRoom = new SimpleObjectProperty<>();

		clientLobbyModel.addListener("gameRoomAdd",this);
		clientLobbyModel.addListener("messageAdded", this);

		txtMessage = new SimpleStringProperty();

		lobbyChatMessages = FXCollections.observableArrayList();

	}


	public ObservableList<GameTableRow> getObservableGameRooms() {
		return observableGameRooms;
	}

	public ObservableList<String> getLobbyChatMessages() {
		return lobbyChatMessages;
	}

	public void join() {
		System.out.println("Lobby view Call join on client");

		int gameRoomId = selectedGameRoom.getValue().getId();
		clientLobbyModel.join(null, gameRoomId);

	}

	public void host() {
		clientLobbyModel.host();

	}

	public void sendMessage(Message message) {
		clientLobbyModel.sendMessage(message);
	}



	public StringProperty txtMessageProperty() {
		return txtMessage;
	}



	public ObjectProperty<GameTableRow> selectedGameRoomProperty() {
		return selectedGameRoom;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Lobby view detect change");
		if (evt.getPropertyName().equals("gameRoomAdd")){
			int roomNumber = (int) evt.getNewValue();
			System.out.println("Set test room value " +  evt.getNewValue());

			Platform.runLater(()-> {

					observableGameRooms.add(new GameTableRow(
							new GameData(roomNumber, "someNames")));

					});

		}

		if (evt.getPropertyName().equals("messageAdded")){
			System.out.println("lobbyViewModel detected incoming message");
			Message message = (Message) evt.getNewValue();
			String senderName = message.getName();
			String txtMessage = message.getStringMessage();
			lobbyChatMessages.add(senderName+ ": " + txtMessage);
		}

	}
}
