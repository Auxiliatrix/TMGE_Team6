package tmge.game.base;

/**
 * Abstract class with functions relevant to game logic.
 */
public abstract class GameEngine {

	public Board state;
	public double score;
	
	public Player player;
	
	public GameEngine(Board initialState, Player player) {
		state = initialState;
		score = 0;
		this.player = player;
	}
	
	/**
	 * Ticks forward the game logic.
	 * @return Whether the program is finished
	 */
	public abstract boolean tick();
	
}
