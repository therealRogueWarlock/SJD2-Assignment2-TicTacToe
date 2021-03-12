package client.model.lobbymodel.tableobjects;

public class GameData {
	private int id;
	private String playerCount;
	private String players;

	public GameData(int id, String players) {
		this.id = id;
		this.players = players;
	}

	public void setId(int id) {
		this.id = id;
	}

    public void setPlayerCount(String playerCount) {
        this.playerCount = playerCount;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public String getPlayerCount() {
        return playerCount;
    }

	public String getPlayers() {
		return players;
	}
}
