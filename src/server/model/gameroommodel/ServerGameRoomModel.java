package server.model.gameroommodel;

import server.model.chatmodel.ChatRoom;
import server.model.gamemodel.TicTacToe;
import server.networking.SocketServerHandler;
import transferobjects.Message;
import transferobjects.TicTacToePiece;
import util.GameRoomModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class ServerGameRoomModel implements GameRoomModel, Serializable {
	private TicTacToe ticTacToe;
	private ChatRoom chatRoom;
	private int gameRoomId;
	private String[] players = new String[2];
	private PropertyChangeSupport support;;

	public ServerGameRoomModel() {
		this.support = new PropertyChangeSupport(this);
		ticTacToe = new TicTacToe();
		chatRoom = new ChatRoom();
	}


	public void join(SocketServerHandler socketServerHandler, String playerName){

		this.addPlayerInfo(playerName);
		socketServerHandler.setServerGameRoomModel(this);

		System.out.println("Adding " + socketServerHandler + " to " + gameRoomId + " " + this);

		this.addListener("piecePlaced", socketServerHandler);
		this.addListener("win", socketServerHandler);
		this.addListener("draw", socketServerHandler);
		this.addListener("turnSwitch", socketServerHandler);
		this.addListener("messageAdded", socketServerHandler);
		this.addListener("gameRoomDel", socketServerHandler);
		this.iChanged("turnSwitch", null);

	}


	public void addPlayerInfo(String playerName) {
		if (players[0] == null) {
			players[0] = playerName;
		} else if (players[1] == null) {
			players[1] = playerName;
		}
	}

	public void addId(int gameRoomId) {
		this.gameRoomId = gameRoomId;
	}

	public void addMessage(Message message) {
		chatRoom.addMessage(message);
		iChanged("messageAdded", message);
	}


	@Override
	public void placePiece(TicTacToePiece ticTacToePiece) {
		System.out.println(ticTacToePiece.getPiece());
		if (ticTacToe.placePiece(ticTacToePiece)) {
			iChanged("piecePlaced", ticTacToePiece);
		}

		if (ticTacToe.checkForWin(ticTacToePiece.getPiece())) {
			String winnerName = ticTacToePiece.getPiece();

			iChanged("win", ticTacToePiece.getPiece());

			iChanged("gameRoomDel", gameRoomId);

			Message newMessage = new Message(getPlayerNames() + " : "+ winnerName + " Won!" );
			newMessage.setName("Lobby");
			iChanged("resultMessage", newMessage);


		} else if (ticTacToe.checkDraw()) {
			iChanged("draw", null);

			iChanged("gameRoomDel", gameRoomId);


		}
		iChanged("turnSwitch", null);
	}

	@Override
	public int getRoomId() {
		return gameRoomId;
	}

	@Override
	public String getPlayerCount() {
		return null;
	}

	@Override
	public String getPlayerNames() {
		return players[0] + " vs " + players[1];
	}

	@Override
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

	public void iChanged(String type, Object newValue) {
		support.firePropertyChange(type, null, newValue);
	}

}
