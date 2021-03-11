package util;

import transferobjects.Message;
import transferobjects.TicTacToePiece;

public interface GameRoomModel extends Subject {

	void placePiece(TicTacToePiece ticTacToePiece);

	int getRoomId();

	String getPlayerCount();

	String getPlayerNames();

	void sendMessage(Message message);

}
