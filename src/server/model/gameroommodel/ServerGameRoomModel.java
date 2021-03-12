package server.model.gameroommodel;

import server.model.chatmodel.ChatRoom;
import server.model.gamemodel.TicTacToe;
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
	private PropertyChangeSupport support;

	public ServerGameRoomModel() {
		this.support = new PropertyChangeSupport(this);
		ticTacToe = new TicTacToe();
		chatRoom = new ChatRoom();
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

	public void iChanged(String type, Object newValue) {
		support.firePropertyChange(type, null, newValue);
	}

	@Override
	public void placePiece(TicTacToePiece ticTacToePiece) {

		if (ticTacToe.placePiece(ticTacToePiece)) {
			iChanged("piecePlaced", ticTacToePiece);
		}

		if (ticTacToe.checkForWin(ticTacToePiece.getPiece())) {
			String winnerName = ticTacToePiece.getPiece();
			iChanged("win", ticTacToePiece.getPiece());

			iChanged("gameRoomDel", gameRoomId);

			Message newMessage = new Message(getPlayerNames() + " " +
					"game has ended, "+ winnerName + "Won!" );

			newMessage.setName("Lobby");
			iChanged("messageAdded", newMessage);

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
		return players[0] + "/" + players[1];
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

}
