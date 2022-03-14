package tmge.game.base;

import tmge.ui.WindowCloseReactable;

/**
 * Abstract class with functions relevant to game logic.
 */
public abstract class GameEngine implements WindowCloseReactable {

	public double score;
	protected boolean kill;
		
	public GameEngine() {
		score = 0;
		kill = false;
	}
	
	/**
	 * Run logic to determine whether game should stop. If it should, run closing operations.
	 * @return Whether the game should continue.
	 */
	public final boolean tick() {
		if( kill || !update() ) {
			close();
			return false;
		}
		return true;
	}
	
	/**
	 * Causes tick to return false no matter what.
	 */
	public final void signalClosed() {
		kill = true;
	}
	
	/**
	 * Return whether the game is incompleted.
	 * @return False when game is complete
	 */
	protected abstract boolean update();
	
	/**
	 * Run final operations before closing the game.
	 */
	protected abstract void close();
	
}
