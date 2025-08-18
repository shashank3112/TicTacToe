package models;

public class Symbol {
    private char sym;
    private  String colour;

    public Symbol(char sym){
        this.sym = sym;
    }
    public char getSymb() {
        return sym;
    }

    public void setSymb(char sym) {
        this.sym = sym;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
