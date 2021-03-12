package server.model.lobbymodel;

import transferobjects.GameData;
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
		gameRoom.addId(gameRoomsId);
		gameRooms.add(gameRoom);

		// joining the game room just added
		join(socketServerHandler,gameRoomsId, playerName);

		iChanged("gameRoomAdd",new GameData(gameRoomsId,playerName));

		gameRoomsId++;
	}

	public void addMessage(Message message) {
		System.out.println("Add message to lobby " + message.getName() + " " + message.getStringMessage());
		chatRoom.addMessage(message);
		iChanged("messageAdded", message);
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
	public void join(Object object, int roomId, String playerName) {

		ServerGameRoomModel gameRoom = (ServerGameRoomModel) getGameRoomById(roomId);
		gameRoom.addPlayerInfo(playerName);
		SocketServerHandler socketServerHandler = (SocketServerHandler) object;
		socketServerHandler.setServerGameRoomModel(gameRoom);

		System.out.println("Adding " + socketServerHandler +" to "+ roomId + " " + gameRoom );

		gameRoom.addListener("piecePlaced", socketServerHandler);
		gameRoom.addListener("win", socketServerHandler);
		gameRoom.addListener("draw", socketServerHandler);
		gameRoom.addListener("turnSwitch", socketServerHandler);
		gameRoom.addListener("messageAdded", socketServerHandler);
		gameRoom.addListener("gameRoomDel", socketServerHandler);

		gameRoom.iChanged("turnSwitch",null);

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



	private GameRoomModel getGameRoomById(int id){
		for (GameRoomModel gameRoom: gameRooms){
			if(gameRoom.getRoomId() == id){
				return gameRoom;
			}
		}

		return null;
	}

	private void iChanged(String eventType, Object newValue){
		System.out.println("ServerLobby model detect change, fire change");
		support.firePropertyChange(eventType, null,newValue);

	}


}
