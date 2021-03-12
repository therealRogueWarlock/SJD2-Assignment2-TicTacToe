package client.gui.viewmodel;

import client.model.lobbymodel.ClientLobbyModel;
import client.model.lobbymodel.tableobjects.GameTableRow;
import transferobjects.GameData;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transferobjects.Message;
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

		clientLobbyModel.addListener("gameRoomAdd", this);
		clientLobbyModel.addListener("gameRoomDel", this);
		clientLobbyModel.addListener("messageAddedLobby", this);

		txtMessage = new SimpleStringProperty();

		lobbyChatMessages = FXCollections.observableArrayList();

	}

	public void host() {
		clientLobbyModel.host();

	}

	public void sendMessage(Message message) {
		clientLobbyModel.sendMessage(message);
	}

	public boolean join() {


		try {
			GameTableRow selectedItem = selectedGameRoom.getValue();
			if (selectedItem != null){
				clientLobbyModel.join(null, selectedItem.getId(),null);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ObservableList<GameTableRow> getObservableGameRooms() {
		return observableGameRooms;
	}

	public ObservableList<String> getLobbyChatMessages() {
		return lobbyChatMessages;
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
		switch (evt.getPropertyName()) {
			case "gameRoomAdd" -> Platform.runLater(() -> observableGameRooms.add(new GameTableRow((GameData) evt.getNewValue())));
			case "gameRoomDel" -> Platform.runLater(() -> {
				System.out.println("LobbyViewModel, get delete room event");
				observableGameRooms.removeIf(row -> row.getId() == (int) evt.getNewValue());
			});

			case "messageAddedLobby" -> {
				System.out.println("lobbyViewModel detected incoming message");
				Message message = (Message) evt.getNewValue();
				String senderName = message.getName();
				String txtMessage = message.getStringMessage();
				Platform.runLater(() -> lobbyChatMessages.add(senderName + ": " + txtMessage));
			}
		}
	}
}
