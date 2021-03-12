package client.model.lobbymodel.tableobjects;

public class GameTableRow {
	private GameData game;

	public GameTableRow(GameData game) {
		this.game = game;
	}

	public int getId() {
		return game.getId();

	}

	public String getPlayers() {
		return game.getPlayers();
	}

}
