package client.gui.viewmodel;

import server.model.gamemodel.TicTacToe;
import server.model.gameroommodel.ServerGameRoomModel;
import Util.GameRoomModel;
import Client.client.model.client.model.gameroommodel.ClientGameRoomModel;

public class GameRoomViewModel implements ViewModel {

	private SimpleStringProperty txtMessage;

	private TicTacToe ticTacToe;

	private ServerGameRoomModel serverGameRoomModel;

	private GameRoomModel gameRoomModel;

	private ClientGameRoomModel clientGameRoomModel;

	public void placePiece(int x, int y) {

	}

	public void sendMessage() {

	}

}
