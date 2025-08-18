package models;

import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private List<Move> moves;
    private Player winner;
    private List<WinningStrategy> winningStrategies;
    private int nextPlayerIdx;

    private Game(Builder gamebuilder){
        board =  new Board(gamebuilder.dimension);
        players = gamebuilder.players;
        winningStrategies = gamebuilder.winningStrategies;
        winner = null;
        gameState = GameState.IN_PROGRESS;
        nextPlayerIdx = 0;
        moves = new ArrayList<>();
    }
    public int getNextPlayerIdx() {
        return nextPlayerIdx;
    }

    public void setNextPlayerIdx(int nextPlayerIdx) {
        this.nextPlayerIdx = nextPlayerIdx;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
    public void displayBoard(){
        board.display();
    }
    public static  Builder getBuilder(){
        return new Builder();
    }
    public static class  Builder{
        private int  dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        private void validate(){
            //1. check player count
            if(players.size()!=dimension-1) throw new RuntimeException("inavlid players count");

            //2. we want to have only one bot in the game
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.Bot)) botCount++;
            }
            if(botCount >1 ) throw new RuntimeException("more than 1 bot is not allowed");

            //3. Every player should have a seperate symbol
            Set<Character> hashset = new HashSet<>();
            for(Player player:players){
                if(hashset.contains(player.getSymbol()))
                    throw new RuntimeException("Every player must have distinct symbol");
                hashset.add(player.getSymbol().getSymb());
            }
        }
        public Game build(){
            //validations
            validate();
            return new Game(this);
        }
    }
}
