package server.model.lobbymodel;

import util.LobbyModel;
import server.model.gameroommodel.ServerGameRoomModel;
import server.model.chatmodel.ChatRoom;
import transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerLobbyModel implements LobbyModel {

	private ArrayList<ServerGameRoomModel> gameRooms;

	private PlayerList playerList;

	private ChatRoom chatRoom;

	public ServerLobbyModel() {
		this.gameRooms = new ArrayList<>();
		this.playerList = new PlayerList();
		this.chatRoom = new ChatRoom();
	}

	public void join() {

	}

	public void host() {

	}

	public void addMessage() {

	}

	public void addPlayer(String name) {

		playerList.addPlayer(name);

	}

	public void removePlayer() {

	}

	public HashMap<Integer, String> getPlayers(){
		return playerList.getPlayers();
	}

	public void sendMessage(Message message) {

	}



	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {

	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {

	}
}
