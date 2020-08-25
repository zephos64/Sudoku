package main.java.sudokuSolver;

import main.java.other.Cell;
import main.java.other.Grid;
import main.java.other.GridGuess;

import java.util.Stack;

public class Solver {

    Grid grid;

    public Solver(Grid grid) {
        this.grid = grid;
    }

    public void attemptGrid() {

        //while(attemptGrid_singleCandidates()){};
        attemptGrid_hiddenSingles();

        // TODO: only do brute force if SC and HS cant find any more solutions
        //attempGrid_bruteForce_stack();
        //attemptGrid_bruteForce_recursive();
    }

    public void printGrid() {grid.printGrid();}

    private boolean attemptGrid_singleCandidates() {
        boolean hasGuess = false;
        for(int i = 0; i < grid.getSize(); i++) {
            for(int j = 0; j < grid.getSize(); j++) {
                if(grid.isSpotEmpty(i, j) && grid.getSpot(i, j).getGuessAmnt()==1) {
                    grid.setSpotValueAndRemoveGuesses(i, j, grid.getSpot(i, j).getGuess(0));
                    hasGuess = true;
                }
            }
        }

        return hasGuess;
    }

    private boolean attemptGrid_hiddenSingles() {
        boolean hasGuess = false;
        for(int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                Cell spot = grid.getSpot(i, j);
                for(int guessInd = 0; guessInd < spot.getGuessAmnt(); guessInd++) {
                    if(grid.isSpotEmpty(i, j) &&
                            grid.isSpotValidForGuess(i, j, spot.getGuess(guessInd))) {
                        grid.setSpotValueAndRemoveGuesses(i, j, spot.getGuess(guessInd));
                    }
                }
            }
        }
        return hasGuess;
    }

    private void attempGrid_bruteForce_stack() {
        Stack<GridGuess> guessStack = new Stack<>();

        GridGuess guess = getNextGuessLoc(0, 0);
        while(guess != null) {
            boolean foundGuess = false;
            for (int numGuess = guess.getGuess()+1; !foundGuess && numGuess <= grid.getSize(); numGuess++) {
                if (grid.isSpotValidForValue(guess.getX(), guess.getY(), numGuess)) {
                    grid.setSpotValue(guess.getX(), guess.getY(), numGuess);
                    guess.setGuess(numGuess);
                    foundGuess = true;
                }
            }

            if(foundGuess) {
                guessStack.push(guess);
                guess = getNextGuessLoc(guess.getX(), guess.getY());
            } else {
                // Reset spots, to ensure we dont get some odd collisions
                guess = guessStack.pop();
                grid.setSpotValue(guess.getX(), guess.getY(), 0);
            }
        }
    }

    private GridGuess getNextGuessLoc(int x, int y) {
        GridGuess guessCoord = new GridGuess();
        for(int i = y; i < grid.getSize(); i++) {
            for(int j = 0; j < grid.getSize(); j++) {
                if(grid.isSpotEmpty(j, i)) {
                    guessCoord.setX(j);
                    guessCoord.setY(i);
                    return guessCoord;
                }
            }
        }

        return null;
    }

    /**
     * Return true if finished successfully, false if could not guess next element
     * @return
     */
    private boolean attemptGrid_bruteForce_recursive() {
        if(grid.isSolved()) return true;

        GridGuess guessLoc = getNextGuessLoc(0, 0);

        // TODO refactor guessing, use Cell guess
        /*for(int guess = 1; guess <= grid.getSize(); guess++) {
            if(grid.isSpotValidForValue(guessLoc.getX(), guessLoc.getY(), guess)) {
                grid.setSpotValue(guessLoc.getX(), guessLoc.getY(), guess);
                boolean childGuess = attemptGrid_bruteForce_recursive();

                if(!childGuess) {
                    grid.setSpotValue(guessLoc.getX(), guessLoc.getY(), 0);
                }
            }
        }*/
        for(int guessIndx = 0;
            guessIndx < grid.getSpot(guessLoc.getX(), guessLoc.getY()).getGuessAmnt();
            guessIndx++) {
            int guess = grid.getSpot(guessLoc.getX(), guessLoc.getY()).getGuess(guessIndx);
            if(grid.isSpotValidForValue(guessLoc.getX(), guessLoc.getY(), guess)) {
                grid.setSpotValue(guessLoc.getX(), guessLoc.getY(), guess);
                boolean childGuess = attemptGrid_bruteForce_recursive();

                if(!childGuess) {
                    grid.setSpotValue(guessLoc.getX(), guessLoc.getY(), 0);
                }
            }
        }


        // Could not find a valid solution, fail out
        if(grid.isSpotEmpty(guessLoc.getX(), guessLoc.getY())) return false;

        return true;
    }
}
