package client.gui.views.lobbyview;

import client.core.ViewHandler;
import client.gui.viewmodel.LobbyViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import client.model.lobbymodel.tabelobjects.GameTableRow;
import com.sun.javafx.scene.control.LabeledText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import transferobjects.Message;
import util.GameRoomModel;

import java.io.IOException;

public class LobbyViewController implements ViewController {


	@FXML private TextField textToSendLobby;
	@FXML private ListView lobbyChat;


	@FXML private TableView<GameTableRow> gameRooms;
	@FXML private TableColumn<GameTableRow, Integer> listRoomId;
	@FXML private TableColumn<GameTableRow, String> listPlayerNames;


	private ViewHandler viewHandler;
	private LobbyViewModel lobbyViewModel;

	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		lobbyViewModel = (LobbyViewModel) model;


		gameRooms.setItems(lobbyViewModel.getObservableGameRooms());

		listRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
		listPlayerNames.setCellValueFactory(new PropertyValueFactory<>("players"));


		lobbyViewModel.selectedGameRoomProperty().bind(gameRooms.getSelectionModel().selectedItemProperty());

		lobbyViewModel.txtMessageProperty().bind(textToSendLobby.textProperty());

		lobbyChat.setItems(lobbyViewModel.getLobbyChatMessages());



	}


	public void hostGame() throws IOException {
		lobbyViewModel.host();

		swapScene("GameRoom");
	}

	public void sendTextLobby() {
		if (!textToSendLobby.getText().isEmpty()) {
			lobbyViewModel.sendMessage(new Message(textToSendLobby.getText()));
			textToSendLobby.clear();
		}
	}

	@Override
	public void swapScene(String scene) throws IOException {
		viewHandler.openView(scene);
	}




	public void quitGame() {
		System.exit(1);
	}



	public void joinGame() {
		lobbyViewModel.join();
		try {
			swapScene("GameRoom");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
