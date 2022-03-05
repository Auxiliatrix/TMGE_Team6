package tmge.main;

import java.util.Scanner;

import tmge.game.base.Player;
import tmge.game.bejeweled.BejeweledBoard;
import tmge.game.bejeweled.BejeweledEngine;
import tmge.game.bejeweled.BejeweledGrid;
import tmge.game.bejeweled.BejeweledUI;
import tmge.ui.UserInterface;

public class Application {

	public static final void main(String[] args) throws InterruptedException {
		BejeweledBoard board = new BejeweledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		BejeweledEngine engine = new BejeweledEngine(board, Player.createNew(Constants.DEFAULT_USER));
		BejeweledGrid grid = new BejeweledGrid(board.height, board.width, engine);
		UserInterface ui = new BejeweledUI(board, grid);
		while( engine.tick() ) {
			ui.display();
			Thread.sleep(100);
		}
	}
	
}
