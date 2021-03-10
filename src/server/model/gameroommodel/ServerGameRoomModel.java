package server.model.gameroommodel;

import util.GameRoomModel;
import server.model.gamemodel.TicTacToe;
import client.networking.Client;
import server.model.chatmodel.ChatRoom;
import transferobjects.Message;

public class ServerGameRoomModel implements GameRoomModel {

	private TicTacToe ticTacToe;

	private ChatRoom chatRoom;

	public void placePiece() {

	}

	public void addMessage() {

	}

	public void sendMessage(Message message) {

	}

	@Override
	public void addListener(String propertyName, int listener) {

	}

	@Override
	public void removeListener(String propertyName, int listener) {

	}
}
