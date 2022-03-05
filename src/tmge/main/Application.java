package tmge.main;

import java.util.Scanner;

import tmge.game.base.Player;
import tmge.game.bejeweled.BejeweledBoard;
import tmge.game.bejeweled.BejeweledEngine;
import tmge.ui.UserInterface;

public class Application {

	public static final void main(String[] args) throws InterruptedException {
		BejeweledBoard board = new BejeweledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
		UserInterface ui = new UserInterface(board);
		BejeweledEngine engine = new BejeweledEngine(board, Player.createNew(Constants.DEFAULT_USER));
		while( engine.tick() ) {
			ui.display();
			(new Scanner(System.in)).nextLine();
		}
	}
	
}
