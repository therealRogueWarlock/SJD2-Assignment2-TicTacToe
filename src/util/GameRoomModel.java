package util;

import transferobjects.Message;
import transferobjects.TicTacToePiece;

public interface GameRoomModel extends Subject {
	void placePiece(TicTacToePiece ticTacToePiece);

	void sendMessage(Message message);

	int getRoomId();

	String getPlayerCount();

	String getPlayerNames();

}
