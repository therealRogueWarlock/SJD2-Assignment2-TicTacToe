package server.model.lobbymodel;

import java.util.HashMap;

public class PlayerList {
	private HashMap<Integer, String> players;
	private int playerIds = 0;

	public PlayerList() {
		this.players = new HashMap<>();
	}

	public void addPlayer(String name) {
		players.put(playerIds, name);
		playerIds++;
	}

	public void removePlayer(int id) {
		players.remove(id);
	}

	public String getName(int id) {
		return players.get(id);
	}

	public HashMap<Integer, String> getPlayers() {
		return players;
	}

}
