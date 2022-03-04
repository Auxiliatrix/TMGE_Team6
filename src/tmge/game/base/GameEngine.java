package tmge.game.base;

/**
 * Abstract class with functions relevant to game logic.
 */
public abstract class GameEngine {

	public double score;
		
	public GameEngine() {
		score = 0;
	}
	
	/**
	 * Ticks forward the game logic.
	 * @return Whether the program is finished
	 */
	public abstract boolean tick();
	
}
