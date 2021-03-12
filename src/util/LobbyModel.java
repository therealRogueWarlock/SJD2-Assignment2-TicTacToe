package util;

import server.networking.SocketServerHandler;
import transferobjects.Message;

public interface LobbyModel extends Subject {


	public abstract void join(Object object, int roomId, String playerName);

	public abstract void sendMessage(Message message);

}
