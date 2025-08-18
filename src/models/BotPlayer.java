package models;

public class BotPlayer extends Player{
    private BotLevel level;
    public BotPlayer(long id , String name , PlayerType playerType ,Symbol symbol,BotLevel level){
        super(id , name , playerType , symbol);
        this.level = level;

    }

    public BotLevel getLevel() {
        return level;
    }

    public void setLevel(BotLevel level) {
        this.level = level;
    }
}
