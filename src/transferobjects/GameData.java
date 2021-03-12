package transferobjects;

import java.io.Serializable;

public class GameData implements Serializable {
    private int id;
    private String playerCount;
    private String players;

    public GameData(int id, String players) {
        this.id = id;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(String playerCount) {
        this.playerCount = playerCount;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }
}
