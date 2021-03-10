package server.model.lobbymodel;

import java.util.HashMap;

public class PlayerList {
	private HashMap<Integer, String> players;

	public void addPlayer(int id, String name) {
		players.put(id, name);
	}

	public void removePlayer(int id) {
		players.remove(id);
	}

	public String getName(int id) {
		return players.get(id);
	}
}
