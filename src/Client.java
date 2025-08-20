import controller.GameController;
import models.*;
import strategies.ColWinningStratgey;
import strategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        GameController gc = new GameController();

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1, "shashank" , PlayerType.HUMAN , new Symbol('X')));
//        players.add(new HumanPlayer(2, "shekhar" , PlayerType.HUMAN , new Symbol('Y')));
//        players.add(new HumanPlayer(3, "Aman" , PlayerType.HUMAN , new Symbol('Z')));
//        players.add(new HumanPlayer(4, "Shubham" , PlayerType.HUMAN , new Symbol('T')));
        players.add(new BotPlayer(2 , "Bot1" , PlayerType.Bot ,new Symbol('B') , BotLevel.EASY));

        Game game = gc.startGame(3 , players , List.of(new RowWinningStrategy(),new ColWinningStratgey()));

        gc.display(game);
        while(gc.checkState(game).equals(GameState.IN_PROGRESS)){
            gc.makeMove(game);
            gc.display(game);

            System.out.println("Do you want to undo the move Y or N");
            Scanner sc = new Scanner(System.in);
            String undo = sc.nextLine();
            if(undo.toLowerCase().equals("y")){
                gc.undo(game);
                System.out.println("Undo is successful");
                gc.display(game);
            }
        }

        if(gc.checkState(game).equals(GameState.SUCCESS)){
            System.out.println(gc.getWinner(game).getName()+" won the game");
        }
        else if(gc.checkState(game).equals(GameState.DRAW)){
            System.out.println("game is draw");
        }

    }
}
