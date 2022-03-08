package tmge.main;

import tmge.game.base.Player;
import tmge.game.columns.ColumnsBoard;
import tmge.game.columns.ColumnsEngine;
import tmge.game.columns.ColumnsGrid;

public class ColumnsApp {
	
	public static final void main(String[] args) throws InterruptedException {
		ColumnsBoard board = new ColumnsBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		ColumnsEngine engine = new ColumnsEngine(board, Player.createNew(Constants.DEFAULT_USER));
		ColumnsGrid grid = new ColumnsGrid(board.height, board.width, engine);
		while( engine.tick() ) {
			grid.update(board);
			Thread.sleep(250);
		}
		grid.takedown();
	}
	
}
