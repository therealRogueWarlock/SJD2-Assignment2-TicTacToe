package client.gui.viewmodel;

import client.model.gameroommodel.ClientGameRoomModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import transferobjects.Message;
import util.GameRoomModel;

import java.beans.PropertyChangeEvent;

public class GameRoomViewModel implements ViewModel {

	private StringProperty txtMessage;
	private ClientGameRoomModel clientGameRoomModel;

	public GameRoomViewModel(GameRoomModel gameRoomModel) {
		this.clientGameRoomModel = (ClientGameRoomModel) gameRoomModel;
		txtMessage = new SimpleStringProperty();
	}

	public void placePiece(int x, int y) {

	}

	public void sendMessage(Message message) {

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}
}
