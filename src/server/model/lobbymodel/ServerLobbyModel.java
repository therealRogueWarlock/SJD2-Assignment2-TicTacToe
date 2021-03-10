package server.model.lobbymodel;

import util.LobbyModel;
import server.model.gameroommodel.ServerGameRoomModel;
import server.model.chatmodel.ChatRoom;
import transferobjects.Message;

import java.util.ArrayList;

public class ServerLobbyModel implements LobbyModel {

	private ArrayList<ServerGameRoomModel> gameRooms;

	private PlayerList playerList;

	private ChatRoom chatRoom;

	public void join() {

	}

	public void host() {

	}

	public void addMessage() {

	}

	public void addPlayer() {

	}

	public void removePlayer() {

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
