package tmge.ui;

import java.awt.Color;

import tmge.game.base.TiledBoard;

/**
 * UI for display and control
 */
public class UserInterface {

	protected TiledBoard<Color> board;
	protected Grid grid;
	public UserInterface(TiledBoard<Color> board, Grid grid) {
		this.board = board;
	}
	
	/**
	 * Update display window with board state
	 */
	public void display()
	{
		//System.out.println("updating UI");
		grid.update(board);
	}
	
}
