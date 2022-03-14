package tmge.main;

import java.util.Scanner;

import tmge.game.base.Player;
import tmge.game.bejeweled.BejeweledBoard;
import tmge.game.bejeweled.BejeweledEngine;
import tmge.game.bejeweled.BejeweledGrid;
import tmge.game.columns.ColumnsBoard;
import tmge.game.columns.ColumnsEngine;
import tmge.game.columns.ColumnsGrid;

public class ConsolePrompts {

	static Scanner myObj = new Scanner(System.in);

	public static void userPrompt() throws InterruptedException {
		String userInput = "";
		while (!userInput.equals("4") && !userInput.equals("(4)")) {
			System.out.println(
					"Welcome to the TMGE Application! To save high scores, you must create or login to a player account. Otherwise, you can play as a guest.\n"
							+ "What would you like to do?\n(1) Create new account\n(2) Login to an existing account\n(3) Play as a guest\n(4) Exit");
			userInput = myObj.nextLine();
			switch (userInput) {
			case "1":
			case "(1)":
				System.out.println("Creating a new user. \nPlease enter your desired username for the new user.");
				String playerName = myObj.nextLine();
				System.out.println(
						"Success, user has been successfully created! You are now logged in as: " + playerName + ".");
				gamePrompt(playerName);
				break;
			case "2":
			case "(2)":
				// Can also list existing users similar to switch options. Would need Need
				// access to players map somehow, though. Created using existing functions.
				System.out.println("Please enter a username to login as (Player account must already exist).");
				String loginName = myObj.nextLine();
				if (Player.getPlayer(loginName) != null) {
					gamePrompt(loginName);
				} else {
					System.out.println("Login failed. User " + loginName
							+ " does not exist. Please create a new player account for this username.");
				}
				break;
			case "3":
			case "(3)":
				gamePrompt("guest");
				break;
			case "4":
			case "(4)":
				System.out.println("Exiting TMGE Application. Thank you for playing.");
				break;
			default:
				System.out.println("Invalid input, please try again.");
				break;
			}
		}
	}

	public static void gamePrompt(String playerName) throws InterruptedException {
		Player currentPlayer;
		String userInput = "";
		if (playerName.equals("guest")) {
			currentPlayer = Player.getOrCreate(Constants.DEFAULT_USER);
			System.out.println("Playing as guest.");
		} else {
			currentPlayer = Player.getOrCreate(playerName);
			System.out.println("Logging in as user: " + currentPlayer.getUsername() + ".");
		}
		printPlayerScores(currentPlayer);
		while (!userInput.equals("4") && !userInput.equals("(4)")) {
			System.out.println(
					"What game would you like to play?\n(1) Bejeweled\n(2) Columns\n(3) View my high scores\n(4) Log out");
			userInput = myObj.nextLine();
			switch (userInput) {
			case "1":
			case "(1)":
				BejeweledBoard bejeweledBoard = new BejeweledBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
				BejeweledEngine bejeweledEngine = new BejeweledEngine(bejeweledBoard, currentPlayer);
				BejeweledGrid bejeweledGrid = new BejeweledGrid(bejeweledBoard.height, bejeweledBoard.width,
						bejeweledEngine);
				while (bejeweledEngine.tick()) {
					bejeweledGrid.update(bejeweledBoard, bejeweledEngine.score);
					Thread.sleep(100);
				}
				bejeweledGrid.takedown();
				System.out.println("Game over. Your score was " + bejeweledEngine.score + " Press 1 to play again.");
				break;
			case "2":
			case "(2)":
				ColumnsBoard columnsBoard = new ColumnsBoard(Constants.BOARD_HEIGHT, Constants.BOARD_WIDTH);
				ColumnsEngine columnsEngine = new ColumnsEngine(columnsBoard, currentPlayer);
				ColumnsGrid columnsGrid = new ColumnsGrid(columnsBoard.height, columnsBoard.width, columnsEngine);
				while (columnsEngine.tick()) {
					columnsGrid.update(columnsBoard, columnsEngine.score);
					Thread.sleep(250);
				}
				columnsGrid.takedown();
				System.out.println("Game over. Your score was " + columnsEngine.score + ". Press 2 to play again.");
				break;
			case "3":
			case "(3)":
				printPlayerScores(currentPlayer);
				break;
			case "4":
			case "(4)":
				System.out.println("Logging out " + currentPlayer.getUsername() + ". Thank you for playing.");
				break;
			default:
				System.out.println("Invalid input, please try again.");
				break;
			}
		}
	}

	public static void printPlayerScores(Player currentPlayer) {
		System.out.println("Your high score for Bejeweled is currently: " + currentPlayer.getHighScore("BEJEWELED"));
		System.out.println("Your high score for Columns is currently: " + currentPlayer.getHighScore("COLUMNS"));
	}
}
