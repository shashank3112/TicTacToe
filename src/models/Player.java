package models;

import java.util.Scanner;

public abstract  class Player {
    private long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scn = new Scanner(System.in);
    public Player(long id, String name, PlayerType playerType, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board){
        System.out.println("Please enter the row to make move");
        int row = scn.nextInt();
        System.out.println("Please enter the col to make move");
        int col = scn.nextInt();

        return new Move(new Cell(row , col ) , this);
    }
}
