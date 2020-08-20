package main.java.other;

public class GridGuess {
    int x;
    int y;
    int guess;

    public GridGuess() {
        guess = 0;
        x = -1;
        y = -1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGuess() {
        return guess;
    }
}
