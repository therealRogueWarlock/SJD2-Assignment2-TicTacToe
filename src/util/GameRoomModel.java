package util;

import transferobjects.Message;

public interface GameRoomModel extends Subject {

	void placePiece(int x, int y, char piece);

	int getRoomId();

	String getPlayerCount();

	String getPlayerNames();

	void sendMessage(Message message);

}
