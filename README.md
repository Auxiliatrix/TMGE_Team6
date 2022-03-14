# Team 6 Tile Matching Game Environment

Team 6 Group Members: Alina Kim, Taylor Lopez, Michael Le, Tianyi Lyu, Justin Quach, Huan Nguyen, Emilio Millan

# Project Description

This project contains our final submission for a tile-matching game environment framework, as well as the implementation for two tile matching games: Columns and Bejeweled. The game logic that handles each of the games can be found in their respective directories (for example `tmge/game/bejeweled`). The framework and logic the tile-matching games were built upon can be found in the `tmge/game/base` directory. 

This application uses Java 16. It also uses components of the Java Swing package for the implementation of its user interface. 

You are currently in the `implementation` branch. This branch contains the demo games built using the framework, and can be run by following the instructions below. In order to see the interface upon which games can be built, please access the `framework` branch.

# Play Bejeweled or Columns

To play a game of Bejeweled or Columns, first run the command `java -jar TMGE.jar` in the command console. Alternatively, compile the code base and run the `Application.java` class.

A prompt will appear on the console asking the user to create a Player account; this is optional and for scoring purposes, it won't affect the tile-matching game play. 

Select the *(1) create new account option*, and enter a username for the account when prompted. Option *(3) play as a guest* may also be selected, but any scores played as a guest will not be saved. 

After logging in, a new prompt will appear asking which of the two tile matching games the user would like to play. 

Select one, and a new window will appear with the selected game.

Once the game reaches a game over state, or the game window is closed, the user's score will be printed to the console, along with the game prompt. 

Users can play either game as much as they want, view their current high scores, or log out and into other users. 

To exit the application, simply log out and select the *(4) exit* option from the prompt. 

## Bejeweled

Click a tile to select it. Click another, cardinally adjacent tile (one that is one tile to the north, east, south, or west) to swap their positions. You may also deselect a tile by clicking on it a second time.
If this swap forms a line of three or more tiles of the same color in either the vertical or horizontal direction, the tiles will be removed from play, and your score will increase.
If no such line is formed, then the tiles will become deselected, and no changes will be made.
More tiles will fall to replace the missing tiles. You may exit the game at any time by closing the game window.

## Columns

Press the left and right arrow keys to move the falling columns to the left and to the right, respectively. Press the up key to cycle the order of the colors in the column. Press the down key to make the column fall faster.
Columns will stop falling (and no longer be controllable) once they land upon either the ground, or another piece.
The objective is to form lines of the same color in any direction, including diagonal. The game is over once pieces no longer have room to spawn in, although the game can be exited at any time by closing the game window.

Thank you for using and playing our Tile Maching Game Environment! :) 