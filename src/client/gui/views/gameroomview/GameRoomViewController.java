package client.gui.views.gameroomview;

import client.core.ViewHandler;
import client.gui.viewmodel.GameRoomViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class GameRoomViewController implements ViewController, PropertyChangeListener {

	@FXML private GridPane gameBoard;
	@FXML private Label gameInfo;
	@FXML private ListView chatGameRoom;
	@FXML private TextField textToSendGameRoom;
	private ViewHandler viewHandler;
	private GameRoomViewModel gameRoomViewModel;
	private BooleanProperty myTurn;

	@Override
	public void init(ViewHandler viewHandler, ViewModel model) {
		//TODO: reset viewModel.

		this.viewHandler = viewHandler;
		gameRoomViewModel = (GameRoomViewModel) model;
		gameRoomViewModel.resetRoom();

		myTurn = new SimpleBooleanProperty();

		myTurn.bind(gameRoomViewModel.turnSwitcherProperty());

		int index = 0;
		for (Node button : gameBoard.getChildren()) {
			if (button instanceof Button) {
				((Button) button).textProperty().bind(gameRoomViewModel.getSlots().get(index));
				button.disableProperty().bind(gameRoomViewModel.turnSwitcherProperty());
			}
			index++;
		}

		gameInfo.textProperty().bind(gameRoomViewModel.winLabelProperty());

		gameRoomViewModel.txtMessageProperty().bind(textToSendGameRoom.textProperty());

		this.gameRoomViewModel.addListener("ViewChange", this);

		chatGameRoom.setItems(gameRoomViewModel.getGameRoomChatMessages());
	}

	public void placePiece(MouseEvent mouseEvent) {
		try {
			Node button = mouseEvent.getPickResult().getIntersectedNode();
			if (button instanceof Button) {

				int col = GridPane.getColumnIndex(button);
				int row = GridPane.getRowIndex(button);

				gameRoomViewModel.placePiece(col, row);

			} else {
				System.out.println("Didn't click on the button");
			}
		} catch (RuntimeException runtimeException) {
			System.out.println(runtimeException.getMessage());
		}
	}

	public void sendTextGameButton() {
		if (!textToSendGameRoom.getText().isEmpty()) {
			Message newMessage = new Message(textToSendGameRoom.getText());
			newMessage.setTarget("GameRoom");
			gameRoomViewModel.sendMessage(newMessage);
			textToSendGameRoom.clear();
		}
	}

	@Override
	public void swapScene(String scene) throws IOException {
		// TODO: Hvis et spil slutter, så skal denne sende en tilbage til lobby view
		viewHandler.openView(scene);
		// FIXME: Ser gerne, vi kan få vores scene givet når et spil er slut, fremfor at hardcode en scene at gå til

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("ViewChange")) {
			new Thread(() -> {
				try {
					Thread.sleep(5000);
					Platform.runLater(() -> {
						try {
							viewHandler.openView((String) evt.getNewValue());
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}).start();
		}
	}
}
