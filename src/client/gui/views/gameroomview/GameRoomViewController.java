package client.gui.views.gameroomview;

import client.core.ViewHandler;
import client.gui.viewmodel.GameRoomViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import transferobjects.Message;

import java.io.IOException;

public class GameRoomViewController implements ViewController {

	public GridPane gameBoard;
	@FXML private ListView chatGameRoom;
	@FXML private TextField textToSendGameRoom;
	private ViewHandler viewHandler;
	private GameRoomViewModel gameRoomViewModel;

	public void sendMessageButton() {
		if (!textToSendGameRoom.getText().isEmpty()) {
			gameRoomViewModel.sendMessage(new Message(textToSendGameRoom.getText())); // FIXME: Skal gøres som property??
			textToSendGameRoom.clear();
		}
	}

	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		gameRoomViewModel = (GameRoomViewModel) model;



		int index = 0;
		for (Node button:gameBoard.getChildren()){

			if (button instanceof Button){
				((Button) button).textProperty().bind(gameRoomViewModel.getSlots().get(index));
			}
			index++;
		}

	}

	@Override
	public void swapScene(String scene) throws IOException {
		// TODO: Hvis et spil slutter, så skal denne sende en tilbage til lobby view
		viewHandler.openView(scene);
		// FIXME: Ser gerne, vi kan få vores scene givet når et spil er slut, fremfor at hardcode en scene at gå til

	}

	public void placePiece(MouseEvent mouseEvent) {
		try {
			Node button = mouseEvent.getPickResult().getIntersectedNode();
			if (button instanceof Button) {

				int col = GridPane.getColumnIndex(button);
				int row = GridPane.getRowIndex(button);

				gameRoomViewModel.placePiece(col,row);

				// TODO: Fortæl serveren at der skal sættes en brik på x,y. Brikker er ikke bundet til en spiller endnu
			} else {
				System.out.println("Didn't click on the button");
			}
		} catch (RuntimeException runtimeException) {
			System.out.println(runtimeException.getMessage());
		}
	}

    public void sendTextGameButton(ActionEvent actionEvent) {
    }
}
