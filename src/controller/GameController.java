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

}
