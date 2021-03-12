package transferobjects;

import java.io.Serializable;

public class TicTacToePiece implements Serializable {
	int x, y;
	String piece;

	public TicTacToePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPiece(String name) {

		piece = name;
	}

	public String getPiece() {
		return piece;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
