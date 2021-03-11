package client.gui.views.lobbyview;

import client.core.ViewHandler;
import client.gui.viewmodel.LobbyViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import transferobjects.Message;
import util.GameRoomModel;

import java.io.IOException;

public class LobbyViewController implements ViewController {


	@FXML private TextField textToSendLobby;
	@FXML private ListView lobbyChat;

	@FXML private TableView<String> gameRooms;
	@FXML private TableColumn<String, String> listRoomNumber;


	@FXML private TableColumn<GameRoomModel, String> listPlayerAmount;
	@FXML private TableColumn<GameRoomModel, String> listPlayerNames;


	private ViewHandler viewHandler;
	private LobbyViewModel lobbyViewModel;

	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		lobbyViewModel = (LobbyViewModel) model;

		listRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		listPlayerAmount.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
		listPlayerNames.setCellValueFactory(new PropertyValueFactory<>("playerNames"));

		gameRooms.setItems(lobbyViewModel.getObservableGameRooms());


	}

	public void joinButton() throws IOException {
		lobbyViewModel.join(/* FIXME: Hvordan skal den vide hvad den skal join? */);
		swapScene("GameRoom");
	}

	public void hostGame() throws IOException {
		lobbyViewModel.host();

		//swapScene("GameRoom");

	}

	public void sendMessageButton() {
		if (!textToSendLobby.getText().isEmpty()) {
			lobbyViewModel.sendMessage(new Message(textToSendLobby.getText()));
			textToSendLobby.clear();
		}
	}

	@Override
	public void swapScene(String scene) throws IOException {
		viewHandler.openView(scene);
	}

	public void joinGame() {
	}


	public void quitGame() {
		System.exit(1);
	}

	public void sendTextLobby() {
		lobbyViewModel.sendMessage(new Message(textToSendLobby.getText()));
	}

}
