package tmge.game.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Data object storing gameplay data for Players.
 * Only one Player with a given username can exist. This is enforced by a private Constructor and a static player map.
 */
public class Player {

	/**
	 * Static map containing all usernames and associated Player instances.
	 */
	private static Map<String, Player> players = new HashMap<String, Player>();
	
	/**
	 * Lowest unused ID number.
	 */
	private static long ID = 0L;
	
	protected String username;
	protected Map<String, Double> highScores;
	protected Map<String, Integer> playCounts;
	
	protected Player(String username) {
		this.username = username;
		highScores = new HashMap<String, Double>();
		playCounts = new HashMap<String, Integer>();
	}
	
	public String getUsername() {
		return username;
	}
	
	/**
	 * Create and return a new Player object with a unique ID.
	 * @return
	 */
	public static Player createNew() {
		while( players.containsKey(ID+"") ) {
			ID++;
		}
		Player newPlayer = new Player(ID+"");
		players.put(ID+"", newPlayer);
		ID++;
		return newPlayer;
	}
	
	/**
	 * If the given username is not yet taken, create a new Player with the given username.
	 * @param username String username to associate with Player
	 * @return Player Created Player object if username not taken; null otherwise
	 */
	public static Player createNew(String username) {
		if( players.containsKey(username) ) {
			return null;
		} else {
			Player newPlayer = new Player(username);
			players.put(username, newPlayer);
			return newPlayer;
		}
	}
	
	/**
	 * Fetch a Player instance by username.
	 * @param username String username associated with Player
	 * @return Player Associated Player instance if found; null otherwise
	 */
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
