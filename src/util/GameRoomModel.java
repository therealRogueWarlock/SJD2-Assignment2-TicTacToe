package util;

import transferobjects.Message;

public interface GameRoomModel extends Subject {
	void placePiece(int x, int y, char piece);

	void sendMessage(Message message);
}
