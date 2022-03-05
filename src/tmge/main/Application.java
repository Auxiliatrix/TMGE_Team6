package tmge.main;

import tmge.game.base.Board;
import tmge.game.base.GameEngine;
import tmge.game.base.Player;
import tmge.game.base.TiledBoard;
import tmge.ui.UserInterface;

public class Application {

	public static final void main(String[] args) {
		Board board = new TiledBoard<Color>(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		UserInterface ui = new UserInterface(board);
		GameEngine ge = new GameEngine(board, Player.createNew(Constants.DEFAULT_USER));
		// TODO: Set ge to implementation
		while( ge.tick() ) {
			ui.display();
			Thread.sleep(Constants.TICK_DURATION);
		}
		
		// ui.display(tb);
	}
	
}
