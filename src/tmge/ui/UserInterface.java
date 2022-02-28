package tmge.ui;

import tmge.game.base.Board;

/**
 * UI for display and control
 */
public abstract class UserInterface {

	protected Board board;
	
	public UserInterface(Board board) {
		this.board = board;
		initialize();
	}
	
	/**
	 * Initialize and open display window
	 */
	public abstract void initialize();
	
	/**
	 * Update display window with board state
	 */
	public abstract void display();
	
}
