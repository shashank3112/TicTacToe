package models;

import strategies.BotPlayingStrategy;
import strategies.BotPlayingStrategyFactory;

public class BotPlayer extends Player{
    private BotLevel level;
    BotPlayingStrategy botPlayingStrategy;
    public BotPlayer(long id , String name , PlayerType playerType ,Symbol symbol,BotLevel level){
        super(id , name , playerType , symbol);
        this.level = level;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStratgey(level);
    }

    public BotLevel getLevel() {
        return level;
    }

    public void setLevel(BotLevel level) {
        this.level = level;
    }
    @Override
    public Move makeMove(Board board){
        return botPlayingStrategy.makeMove(board);
    }
}
