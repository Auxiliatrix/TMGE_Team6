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

Select one, and a new window will appear with the selected game. (Careful, it may need to be opened from the task bar and might not pop to the foreground). 

Once the game reaches a game over state, or the game window is closed, the user's score will be printed to the console, along with the game prompt. 

Users can play either game as much as they want, view their current high scores, or log out and into other users. 

To exit the application, simply log out and select the *(4) exit* option from the prompt. 

Thank you for using and playing our Tile Maching Game Environment! :) 