package tmge.main;

import tmge.game.base.Player;
import tmge.game.bejeweled.BejeweledBoard;
import tmge.game.bejeweled.BejeweledEngine;
import tmge.game.bejeweled.BejeweledGrid;

public class BejeweledApp {

	public static final void main(String[] args) throws InterruptedException {
		BejeweledBoard board = new BejeweledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		BejeweledEngine engine = new BejeweledEngine(board, Player.createNew(Constants.DEFAULT_USER));
		BejeweledGrid grid = new BejeweledGrid(board.height, board.width, engine);
		while( engine.tick() ) {
			grid.update(board, engine.score);
			Thread.sleep(100);
		}
		grid.takedown();
	}
	
}
