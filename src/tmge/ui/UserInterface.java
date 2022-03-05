package tmge.ui;

import tmge.game.tiles.TiledBoard;
import tmge.game.base.Board;
import tmge.main.Constants;

import java.awt.*;
import javax.swing.*;

import static tmge.main.Constants.UI_HEIGHT;
import static tmge.main.Constants.UI_WIDTH;

/**
 * UI for display and control
 */
public class UserInterface {

	protected Board board;
	protected Grid grid;
	public UserInterface(Board board) {
		this.board = board;
		initialize();
	}
	
	/**
	 * Initialize and open display window
	 */
	public void initialize()
	{
		grid = new Grid();

	}
	
	/**
	 * Update display window with board state
	 */
	public void display()
	{
		//System.out.println("updating UI");
		grid.update((TiledBoard)board);
	}

	/**
	 * API made for game engine, to (un)select a tile.
	 */
	public void select(int row, int col)
	{
		grid.select(row, col);
	}

	
}
