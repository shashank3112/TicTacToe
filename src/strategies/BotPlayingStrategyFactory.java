package strategies;

import models.BotLevel;

public class BotPlayingStrategyFactory {
    public static  BotPlayingStrategy getBotPlayingStratgey(BotLevel botLevel){
        if(botLevel.equals(BotLevel.EASY)){
            return new EasyBotPlayingStratgey();
        }
        return null;
    }
}
