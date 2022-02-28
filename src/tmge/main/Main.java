package tmge.main;

import tmge.game.base.GameEngine;
import tmge.game.base.Player;
import tmge.game.tiles.TiledBoard;
import tmge.ui.UserInterface;

public class Main {

	public static void main(String[] args) {
		TiledBoard tb = new TiledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		UserInterface ui = null;
		// TODO: Set UI to implementation
		Player player = Player.createNew(Constants.DEFAULT_USER);
		GameEngine ge = null;
		// TODO: Set ge to implementation
		while( ge.tick() ) {
			ui.display();
		}
		
		// ui.display(tb);
	}
	
}
