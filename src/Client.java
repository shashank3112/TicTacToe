import controller.GameController;
import models.*;
import strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        GameController gc = new GameController();

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1, "shashank" , PlayerType.HUMAN , new Symbol('X')));
        players.add(new HumanPlayer(2, "shekhar" , PlayerType.HUMAN , new Symbol('Y')));
        players.add(new HumanPlayer(3, "Aman" , PlayerType.HUMAN , new Symbol('Z')));
        players.add(new HumanPlayer(4, "Shubham" , PlayerType.HUMAN , new Symbol('T')));

        Game game = gc.startGame(5 , players , List.of(new RowWinningStrategy()));

        gc.display(game);
        while(gc.checkState(game).equals(GameState.IN_PROGRESS)){
            gc.makeMove(game);
        }

        if(gc.checkState(game).equals(GameState.SUCCESS)){
            System.out.println(gc.getWinner(game).getName()+"won the game");
        }
        else if(gc.checkState(game).equals(GameState.DRAW)){
            System.out.println("game is draw");
        }

    }
}
