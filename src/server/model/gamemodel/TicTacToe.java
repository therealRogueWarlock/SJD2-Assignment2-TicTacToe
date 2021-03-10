package server.model.gamemodel;

import java.util.Arrays;

public class TicTacToe {
	private char[][] b;
	private String w, s;

	public TicTacToe() {
		b = new char[3][3];
		for (char[] chars : b) {
			Arrays.fill(chars, ' ');
		}
	}

	public void placePiece(int x, int y, char piece) {
		if (b[x][y] == ' ') {
			b[x][y] = piece;
			checkForWin(piece);
			if (w != null) {
				System.out.println(w + " wins!");
				return;
			}
			checkDraw();
			if (s != null) {
				System.out.println(s);
				return;
			}
			System.out.println("Piece " + b[x][y] + " placed at " + x + ", " + y);
		}
	}

	private void checkDraw() {
		for (int i = 0; i < b.length; i++) {
			for (char[] chars : b) {
				if (chars[i] == ' ') {
					return;
				}
			}
		}
		s = "Draw";
	}

	private void checkForWin(char piece) {
		// Diagonals
		if ((b[0][0] == piece && b[1][1] == piece && b[2][2] == piece) || (b[2][0] == piece && b[1][1] == piece && b[0][2] == piece)) {
			w = "Player " + piece;
		}
		// Straights
		for (int i = 0; i < b.length; i++) {
			if ((b[i][0] == piece && b[i][1] == piece && b[i][2] == piece) || (b[0][i] == piece && b[1][i] == piece && b[2][i] == piece)) {
				w = "Player " + piece;
				break;
			}
		}
	}
}
