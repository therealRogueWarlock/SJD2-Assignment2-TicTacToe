package server.model.gamemodel;

import transferobjects.TicTacToePiece;

import java.util.Arrays;

public class TicTacToe {
	private String[][] b;
	private String w, s;

	public TicTacToe() {
		b = new String[3][3];
		for (String[] chars : b) {
			Arrays.fill(chars, " ");
		}
	}

	public boolean placePiece(TicTacToePiece ticTacToePiece) {
		int x = ticTacToePiece.getX();
		int y = ticTacToePiece.getY();
		String piece = ticTacToePiece.getPiece();

		if (b[x][y].equals(" ")) {
			b[x][y] = piece;

			/*checkForWin(piece);
			if (w != null) {
				System.out.println(w + " wins!");
				return;
			}
			checkDraw();
			if (s != null) {
				System.out.println(s);
				return;
			}*/

			System.out.println(Arrays.deepToString(b));
			System.out.println("Piece " + b[x][y] + " placed at " + x + ", " + y);
			return true;
		}

		return false;
	}

	public boolean checkDraw() {
		for (int i = 0; i < b.length; i++) {
			for (String[] chars : b) {
				if (chars[i].equals(" ")) {
					return false;
				}
			}
		}
		s = "Draw";
		return true;
	}

	public boolean checkForWin(String piece) {
		// Diagonals
		if ((b[0][0].equals(piece) && b[1][1].equals(piece) && b[2][2].equals(piece)) || (b[2][0].equals(piece) && b[1][1].equals(piece) && b[0][2].equals(piece))) {
			w = "Player " + piece;
			return true;
		}
		// Straights
		for (int i = 0; i < b.length; i++) {
			if ((b[i][0].equals(piece) && b[i][1].equals(piece) && b[i][2].equals(piece)) || (b[0][i].equals(piece) && b[1][i].equals(piece) && b[2][i].equals(piece))) {
				w = "Player " + piece;
				return true;
			}
		}

		return false;
	}
}
