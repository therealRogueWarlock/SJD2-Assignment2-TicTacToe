package server.model.lobbymodel;

import server.networking.SocketServer;
import server.networking.SocketServerHandler;
import util.GameRoomModel;
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



	public void host(SocketServerHandler socketServerHandler, String playerName) {
		ServerGameRoomModel gameRoom = new ServerGameRoomModel();
		gameRoom.addPlayerInfo(playerName);
		gameRoom.addId(gameRoomsId);
		gameRooms.add(gameRoom);

		// joining the game room just added
		join(socketServerHandler,gameRoomsId);
		gameRoomsId++;

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

	@Override
	public void join(Object object, int roomId) {

		GameRoomModel gameRoom = getGameRoomById(roomId);
		SocketServerHandler socketServerHandler = (SocketServerHandler) object;
		socketServerHandler.setServerGameRoomModel(gameRoom);
		System.out.println("Adding " + socketServerHandler +" to "+ roomId + " " + gameRoom );

		gameRoom.addListener("piecePlaced", socketServerHandler);
		gameRoom.addListener("win", socketServerHandler);
		gameRoom.addListener("draw", socketServerHandler);
		gameRoom.addListener("turnSwitch", socketServerHandler);

		System.out.println("all listeners added");

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
		System.out.println("ServerLobby model detect change, fire change");
		support.firePropertyChange("gameRoomAdd", null,gameRooms.get(gameRooms.size()-1));
	}



	private GameRoomModel getGameRoomById(int id){
		for (GameRoomModel gameRoom: gameRooms){
			if(gameRoom.getRoomId() == id){
				return gameRoom;
			}
		}

		return null;
	}


}
