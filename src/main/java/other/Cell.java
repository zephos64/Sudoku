package main.java.other;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int size = 9;

    private int value;
    private ArrayList<Integer> guesses;

    public Cell() {
        value = 0;
        guesses = new ArrayList<Integer>();
        for(int i = 1; i <= size; i++) {
            guesses.add(i);
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getGuessAmnt() {
        return guesses.size();
    }

    public void removeGuess(int value) {
        for(int i = 0; i < guesses.size(); i++) {
            if(guesses.get(i) == value) {
                guesses.remove(i);
                return;
            }
        }
    }

    public int getGuess(int index) {
        return guesses.get(index);
    }
}
