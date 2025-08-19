package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements  WinningStrategy{
    HashMap<Integer , HashMap<Character , Integer> >counts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board , Move move) {
        // our hashmap will store a hashmap for every row
        // for every row we will store the count of each symbol
        // 0th row - > symbol 'X' - > 5
        //similarly 1st row -> symbol 'Y' -> 2

        int row = move.getCell().getRow();
        Character sym = move.getPlayer().getSymbol().getSymb();

        if(!counts.containsKey(row)){
            counts.put(row , new HashMap<>());
        }
        HashMap<Character , Integer>countRow = counts.get(row);
        if(!countRow.containsKey(sym)){
            countRow.put(sym,0);
        }
        countRow.put(sym , countRow.get(sym)+1);

        if(countRow.get(sym)== board.getSize()){
            return true;
        }
        return false;
    }
}
