package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class ColWinningStratgey implements WinningStrategy{
    HashMap<Integer , HashMap<Character , Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character sym = move.getPlayer().getSymbol().getSymb();
        //if the hashmap corresponding to the column has not been addded
        if(!counts.containsKey(col)){
            counts.put(col , new HashMap<>());
        }
        //fetching hashmap of that column col
        HashMap<Character , Integer> countCol = counts.get(col);
        //if sym key is not present in the hashmap then add it with 0 as value
        if(!countCol.containsKey(sym)){
            countCol.put(sym , 0);
        }
        // increment the value of sym count by 1
        countCol.put(sym , countCol.get(sym) +1 );

        if(countCol.get(sym)== board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Character symb = move.getPlayer().getSymbol().getSymb();
        HashMap<Character , Integer>countCol = counts.get(col);
        int x = countCol.get(symb);
        countCol.put(symb , x-1);
    }
}
