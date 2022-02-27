package tmge.game.base;

import java.util.HashMap;
import java.util.Map;

public class Player {

	private static Map<String, Player> players = new HashMap<String, Player>();
	private static long ID = 0L;
	
	protected String username;
	protected Map<String, Double> highScores;
	protected Map<String, Integer> playCounts;
	
	protected Player(String username) {
		this.username = username;
		highScores = new HashMap<String, Double>();
		playCounts = new HashMap<String, Integer>();
	}
	
	public static Player createNew() {
		while( players.containsKey(ID+"") ) {
			ID++;
		}
		Player newPlayer = new Player(ID+"");
		players.put(ID+"", newPlayer);
		ID++;
		return newPlayer;
	}
	
	public static Player createNew(String username) {
		if( players.containsKey(username) ) {
			return null;
		} else {
			Player newPlayer = new Player(username);
			players.put(username, newPlayer);
			return newPlayer;
		}
	}
	
	public static Player getPlayer(String username) {
		if( players.containsKey(username) ) {
			return players.get(username);
		} else {
			return null;
		}
	}
	
	public double getHighScore(String game) {
		return highScores.get(game);
	}
	
	public double getPlayCount(String game) {
		return playCounts.get(game);
	}
	
	public boolean trackPlay(String game, double score) {
		if( !playCounts.containsKey(game) ) {
			playCounts.put(game, 1);
		} else {
			playCounts.put(game, playCounts.get(game)+1);
		}
		if( !highScores.containsKey(game) ) {
			highScores.put(game, score);
			return true;
		} else if( highScores.get(game) < score ) {
			highScores.put(game, score);
			return true;
		} else {
			return false;
		}
	}
	
}
