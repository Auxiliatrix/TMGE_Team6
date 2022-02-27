package tmge.main;

import tmge.game.base.GameEnvironment;
import tmge.game.base.Player;
import tmge.game.tiles.TiledBoard;
import tmge.ui.UserInterface;

public class Main {

	public static void main(String[] args) {
		UserInterface ui = null;
		// TODO: Set UI to implementation
		TiledBoard tb = new TiledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		Player player = Player.createNew(Constants.DEFAULT_USER);
		GameEnvironment ge = new GameEnvironment(tb, player);
		
		// ui.display(tb);
	}
	
}
