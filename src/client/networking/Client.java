package client.networking;

import transferobjects.Message;
import transferobjects.Request;
import util.Subject;

public interface Client extends Subject {
	void start();
	void sendMessage(Message message);
	void joinGame(int roomId);
	void hostGame();
	void setClientName(String name);
	void sendRequest(Request request);
    String getName();
}
