package controller;

import models.Game;
import models.GameState;
import models.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController {


    public Game startGame(int dimension,
                          List<Player> players,
                          List<WinningStrategy> winningstrategies){
        return Game.getBuilder().
                setDimension(dimension).
                setPlayers(players).
                setWinningStrategies(winningstrategies).build();
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void makeMove(Game game ){

    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){

    }
    public  void display(Game game){
        game.displayBoard();
    }

}

//1. start the game - take the input ,set attributes , validate the atrributes
//2. while the game is in_process
    //2.1 Display the board
    //2.2 make the move , check for the winner , update the state
// 3.check the state
    // if game state is success
    //display the winner and end the game
    //else if game state is draw
    //declare the draw
