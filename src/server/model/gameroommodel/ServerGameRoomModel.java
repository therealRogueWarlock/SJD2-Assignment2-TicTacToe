package server.model.gameroommodel;

import server.model.chatmodel.ChatRoom;
import server.model.gamemodel.TicTacToe;
import transferobjects.Message;
import util.GameRoomModel;

public class ServerGameRoomModel implements GameRoomModel {

	private TicTacToe ticTacToe;
	private ChatRoom chatRoom;

	@Override
	public void placePiece(int x, int y, char piece) {
		ticTacToe.placePiece(x, y, piece);
	}

	public void sendMessage(Message message) {
		chatRoom.addMessage(message);
	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
