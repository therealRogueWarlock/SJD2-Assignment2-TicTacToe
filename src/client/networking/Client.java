package client.networking;

import transferobjects.Message;
import transferobjects.Request;
import util.Subject;

public interface Client extends Subject {

	void start();

	void sendMessage(Message message);

	void joinGame(String playerName);

	void hostGame();

	void setClientName(String name);

	void sendRequest(Request request);
}
