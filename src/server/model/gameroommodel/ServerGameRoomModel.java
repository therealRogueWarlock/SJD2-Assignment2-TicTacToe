package server.model.gameroommodel;

import server.model.chatmodel.ChatRoom;
import server.model.gamemodel.TicTacToe;
import transferobjects.Message;
import util.GameRoomModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class ServerGameRoomModel implements GameRoomModel, Serializable {

	private TicTacToe ticTacToe;
	private ChatRoom chatRoom;
	private int gameRoomId;
	private String[] players = new String[2];

	private PropertyChangeSupport support;

	@Override
	public void placePiece(int x, int y, char piece) {
		ticTacToe.placePiece(x, y, piece);
	}

	@Override
	public int getRoomId() {
		return gameRoomId;
	}

	@Override
	public String getPlayerCount() {
		return "Randomshit";
	}

	@Override
	public String getPlayerNames() {
		return "Morerandshit";
	}

	public void sendMessage(Message message) {
		chatRoom.addMessage(message);
	}

	@Override
	public void addListener(String propertyName, PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removeListener(String propertyName, PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}


	public void addPlayerInfo(String playerName){
		if (players[0] == null){

			players[0] = playerName;

		}else{

			players[1] = playerName;

		}

	}


	public void addId(int gameRoomId) {
		this.gameRoomId = gameRoomId;
	}
}
