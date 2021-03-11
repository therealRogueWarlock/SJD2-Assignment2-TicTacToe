package transferobjects;

import java.io.Serializable;

public class TicTacToePiece implements Serializable {
	int x, y;
	char piece;

	public TicTacToePiece(int x, int y, char piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}
}
