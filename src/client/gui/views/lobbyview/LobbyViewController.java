package client.gui.views.lobbyview;

import client.core.ViewHandler;
import client.gui.viewmodel.LobbyViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import com.sun.javafx.scene.control.LabeledText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import transferobjects.Message;
import util.GameRoomModel;

import java.io.IOException;

public class LobbyViewController implements ViewController {


	public Label gameRoomTest;

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

		gameRoomTest.textProperty().bind(lobbyViewModel.testGameRoomProperty());


		/*listRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		listPlayerAmount.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
		listPlayerNames.setCellValueFactory(new PropertyValueFactory<>("playerNames"));
*/

	}


	public void hostGame() throws IOException {
		lobbyViewModel.host();

		swapScene("GameRoom");

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




	public void quitGame() {
		System.exit(1);
	}

	public void sendTextLobby() {
		lobbyViewModel.sendMessage(new Message(textToSendLobby.getText()));
	}

	public void joinTestGame(MouseEvent actionEvent) {

		int roomId = Integer.parseInt(((LabeledText) actionEvent.getTarget()).getText());

		System.out.println("lobby controller: join game, call viewModel");
		lobbyViewModel.join(roomId);

		try {
			swapScene("GameRoom");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void joinGame(ActionEvent actionEvent) {
	}
}
