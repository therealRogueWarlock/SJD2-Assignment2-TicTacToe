package server.model.chatmodel;

import transferobjects.Message;

import java.util.ArrayList;

public class ChatRoom {
	private ArrayList<Message> allMessages;

	public ChatRoom() {
		this.allMessages = new ArrayList<>();
	}

	public void addMessage(Message msg) {
		allMessages.add(msg);
	}

	public ArrayList<Message> getAllMessages() {
		return allMessages;
	}

}
