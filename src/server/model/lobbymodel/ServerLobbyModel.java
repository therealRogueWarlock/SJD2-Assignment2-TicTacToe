package server.model.lobbymodel;

import util.LobbyModel;
import server.model.gameroommodel.ServerGameRoomModel;
import server.model.chatmodel.ChatRoom;
import transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerLobbyModel implements LobbyModel {

	private ArrayList<ServerGameRoomModel> gameRooms;
	private PlayerList playerList;
	private ChatRoom chatRoom;
	private int gameRoomsId;

	private PropertyChangeSupport support;

	public ServerLobbyModel() {
		this.gameRooms = new ArrayList<>();
		this.playerList = new PlayerList();
		this.chatRoom = new ChatRoom();
		this.support = new PropertyChangeSupport(this);

	}

	public void join() {

	}


	public void host(String playerName) {
		ServerGameRoomModel gameRoom = new ServerGameRoomModel();
		gameRoom.addPlayerInfo(playerName);
		gameRoom.addId(gameRoomsId);
		gameRooms.add(gameRoom);
		iChanged();
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
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

	public ArrayList<ServerGameRoomModel> getGameRooms() {
		return gameRooms;
	}

	private void iChanged(){
		support.firePropertyChange("gameRoomAdd", null,gameRooms);
	}





}
