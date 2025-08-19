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
    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        //if input is outside boundary
        if(row <0 || row>= board.getSize() || col<0 || col>= board.getSize()){
            return false;
        }
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }
    public void makeMove(){
        Player currentPlayer = players.get(nextPlayerIdx);
        System.out.println("It's "+currentPlayer.getName()+"'s turn" );

        Move move = currentPlayer.makeMove(board);
        if(!validateMove(move)){
            System.out.println("Its not a valid move");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cellToChange = board.getGrid().get(row).get(col);
        cellToChange.setSymbol(currentPlayer.getSymbol());
        cellToChange.setCellState(CellState.FILLED);

        move.setCell(cellToChange);
        move.setPlayer(currentPlayer);

        moves.add(move);
        nextPlayerIdx = (nextPlayerIdx + 1)%players.size();

        // we need to check if there is any state change in game due to this move
        if(checkWinner(move)){
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);

        }else if(moves.size() == board.getSize() * board.getSize()){//won't work if some cells are not allowed to be played
            setWinner(null);
            setGameState(GameState.DRAW);
            System.out.println("game is draw");
        }
    }

    public boolean checkWinner(Move move){
        for(WinningStrategy strategy: winningStrategies){
            if(strategy.checkWinner(board , move)==true){
                return true;
            }
        }
        return false;
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
