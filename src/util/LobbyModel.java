package util;

import transferobjects.Message;

public interface LobbyModel extends Subject {
	void join(Object object, int roomId, String playerName);

	void sendMessage(Message message);

}
