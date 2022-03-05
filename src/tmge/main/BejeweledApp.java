package tmge.main;

import tmge.game.base.Player;
import tmge.game.bejeweled.BejeweledBoard;
import tmge.game.bejeweled.BejeweledEngine;
import tmge.game.bejeweled.BejeweledGrid;
import tmge.ui.UserInterface;

public class BejeweledApp {

	public static final void main(String[] args) throws InterruptedException {
		BejeweledBoard board = new BejeweledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		BejeweledEngine engine = new BejeweledEngine(board, Player.createNew(Constants.DEFAULT_USER));
		BejeweledGrid grid = new BejeweledGrid(board.height, board.width, engine);
		UserInterface ui = new UserInterface(board, grid);
		while( engine.tick() ) {
			ui.display();
			Thread.sleep(100);
		}
	}
	
}
