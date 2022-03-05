package tmge.ui;

import tmge.game.base.Board;
import tmge.game.base.TiledBoard;
import tmge.main.Constants;

import java.awt.*;
import javax.swing.*;


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
		System.out.println("updating UI");
		grid.update((TiledBoard)board);
	}
	
}
