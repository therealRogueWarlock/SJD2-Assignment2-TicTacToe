package util;

import transferobjects.Message;

public interface LobbyModel extends Subject {


	public abstract void join();

	public abstract void sendMessage(Message message);

}
