package client.gui.views.lobbyview;

import client.core.ViewHandler;
import client.gui.viewmodel.LobbyViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import transferobjects.Message;

import java.io.IOException;

public class LobbyViewController implements ViewController {

	@FXML private TextField textToSendLobby;
	@FXML private ListView lobbyChat;
	@FXML private TableColumn listRoomNumber;
	@FXML private TableColumn listPlayerAmount;
	@FXML private TableColumn listPlayerNames;
	private ViewHandler viewHandler;
	private LobbyViewModel lobbyViewModel;

	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		lobbyViewModel = (LobbyViewModel) model;
	}

	public void joinButton() throws IOException {
		lobbyViewModel.join(/* FIXME: Hvordan skal den vide hvad den skal join? */);
		swapScene("GameRoom");
	}

	public void hostButton() throws IOException {
		lobbyViewModel.host(/* FIXME: Den skal have noget information når man hoster et game? */);
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

	public void joinGame() {
	}

	public void hostGame() {
	}

	public void quitGame() {
		System.exit(1);
	}

	public void sendTextLobby() {
		lobbyViewModel.sendMessage(new Message(textToSendLobby.getText()));
	}

}
