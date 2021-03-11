package client.gui.viewmodel;

import client.model.gameroommodel.ClientGameRoomModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import transferobjects.Message;
import transferobjects.Request;
import transferobjects.TicTacToePiece;
import util.GameRoomModel;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class GameRoomViewModel implements ViewModel {

	private StringProperty txtMessage;
	private ClientGameRoomModel clientGameRoomModel;

	private ArrayList<StringProperty> slots;

	public GameRoomViewModel(GameRoomModel gameRoomModel) {
		this.clientGameRoomModel = (ClientGameRoomModel) gameRoomModel;

		this.clientGameRoomModel.addListener("piecePlaced", this);
		this.clientGameRoomModel.addListener("win", this);
		this.clientGameRoomModel.addListener("draw", this);
		this.clientGameRoomModel.addListener("turnSwitch", this);

		txtMessage = new SimpleStringProperty();

		slots = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			slots.add(new SimpleStringProperty());
		}



	}

	public void placePiece(int x, int y) {
		System.out.println("ViewModel: send placePiece to GameRoomModel client");
		clientGameRoomModel.placePiece(new TicTacToePiece(x,y));
	}

	public void updateGameBoard(int x, int y, String symbol){
		System.out.println("Updating game board");

		Platform.runLater(()-> slots.get(convert2dTo1d(x,y)).setValue(String.valueOf(symbol)));
	}


	private int convert2dTo1d(int x, int y){
		return (y*3)+x;
	}

	public ArrayList<StringProperty> getSlots() {
		return slots;
	}

	public void sendMessage(Message message) {

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String eventType = evt.getPropertyName();
		if (eventType.equals("piecePlaced")){
			System.out.println("GameViewModel detected piece placed event ");
			TicTacToePiece newPiece = (TicTacToePiece) evt.getNewValue();
			updateGameBoard(newPiece.getX(),newPiece.getY(), newPiece.getPiece());
		} if (eventType.equals("win")){
			//sendTransferObject(new Request(eventType, null));
		} if (eventType.equals("draw")){
			//sendTransferObject(new Request(eventType, null));
		} if (eventType.equals("trunSwitch")){
			//sendTransferObject(new Request(eventType, null));
		}
	}





}
