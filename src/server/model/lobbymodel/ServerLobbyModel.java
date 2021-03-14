package server.model.lobbymodel;

import server.model.chatmodel.ChatRoom;
import server.model.gameroommodel.ServerGameRoomModel;
import server.networking.SocketServerHandler;
import transferobjects.GameData;
import transferobjects.Message;
import util.GameRoomModel;
import util.LobbyModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerLobbyModel implements LobbyModel, PropertyChangeListener {

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



	public void addMessage(Message message) {
		System.out.println("Add message to lobby " + message.getName() + " " + message.getStringMessage());
		chatRoom.addMessage(message);
		System.out.println(getAllMessages().toString());
		iChanged("messageAdded", message);
	}

	public void addPlayer(String name) {

		playerList.addPlayer(name);

	}

	public void removePlayer() {

	}

	public ArrayList<Message> getAllMessages() {
		return chatRoom.getAllMessages();
	}

	public HashMap<Integer, String> getPlayers() {
		return playerList.getPlayers();
	}

	public ArrayList<ServerGameRoomModel> getGameRooms() {
		return gameRooms;
	}

	public ArrayList<GameData> getAllGameRoomData(){
		ArrayList<GameData> allDameRoomData = new ArrayList<>();
		for (ServerGameRoomModel gameRoom: getGameRooms()){
			allDameRoomData.add(new GameData(gameRoom.getRoomId(),
					gameRoom.getPlayerNames()));
		}
		return allDameRoomData;
	}


	private GameRoomModel getGameRoomById(int id) {
		for (GameRoomModel gameRoom : gameRooms) {
			if (gameRoom.getRoomId() == id) {
				return gameRoom;
			}
		}
		return null;
	}

	private void removeGameRoomById(int id) {
		gameRooms.removeIf(gameRoom -> gameRoom.getRoomId() == id);
	}

	public void host(SocketServerHandler socketServerHandler, String playerName) {
		ServerGameRoomModel gameRoom = new ServerGameRoomModel();
		gameRoom.addId(gameRoomsId);
		gameRooms.add(gameRoom);

		gameRoom.addListener("resultMessage", this);
		gameRoom.addListener("gameRoomDel",this);

		// joining the game room just added
		join(socketServerHandler, gameRoomsId, playerName);

		iChanged("gameRoomAdd", new GameData(gameRoomsId, playerName));

		gameRoomsId++;
	}


	@Override
	public void join(Object object, int roomId, String playerName) {

		ServerGameRoomModel gameRoom = (ServerGameRoomModel) getGameRoomById(roomId);

		gameRoom.join((SocketServerHandler) object,playerName);

	}

	@Override
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("resultMessage")){
			addMessage((Message) evt.getNewValue());}
		else if (evt.getPropertyName().equals("gameRoomDel")){
			removeGameRoomById((Integer) evt.getNewValue());}

	}

	private void iChanged(String eventType, Object newValue) {
		System.out.println("ServerLobby model detect change, fire change");
		support.firePropertyChange(eventType, null, newValue);
	}

}
