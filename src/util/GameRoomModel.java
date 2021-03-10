package util;

import transferobjects.Message;

public interface GameRoomModel extends Subject {

	public abstract void placePiece();

	public abstract void sendMessage(Message message);

}
