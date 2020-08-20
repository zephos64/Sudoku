package main.java.sudokuSolver;

import main.java.other.Grid;
import main.java.other.GridGuess;

import java.util.Stack;

public class Solver {

    public void attemptGrid(Grid grid) {
        attempGrid_helper_stack(grid);
    }

    private void attempGrid_helper_stack(Grid grid) {
        // Milliseconds taken for this: 11-13
        // Milliseconds taken for this: 9-12 with loc hint
        Stack<GridGuess> guessStack = new Stack<>();

        GridGuess guess = getNextGuessLoc(grid, 0, 0);
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
                guess = getNextGuessLoc(grid, guess.getX(), guess.getY());
            } else {
                // Reset spots, to ensure we dont get some odd collisions
                guess = guessStack.pop();
                grid.setSpotValue(guess.getX(), guess.getY(), 0);
            }
        }
    }

    private GridGuess getNextGuessLoc(Grid grid, int x, int y) {
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
     * @param grid
     * @return
     */
    private boolean attemptGrid_helper_recursive(Grid grid) {
        // Milliseconds taken for this: 6-9
        // Find first allowed area
        int x = -1, y = -1;
        for(int i = 0; i < grid.getSize(); i++) {
            for(int j = 0; j < grid.getSize(); j++) {
                if(grid.isSpotEmpty(j, i)) {
                    x = j;
                    y = i;
                    break;
                }
            }
            if(x != -1) break;
        }

        // No more valid solutions
        if(x == -1) return true;

        for(int guess = 1; guess <= grid.getSize(); guess++) {
            if(grid.isSpotValidForValue(x, y, guess)) {
                grid.setSpotValue(x, y, guess);
                boolean childGuess = attemptGrid_helper_recursive(grid);

                if(!childGuess) {
                    grid.setSpotValue(x, y, 0);
                }
            }
        }

        // Could not find a valid solution, fail out
        if(grid.isSpotEmpty(x, y)) return false;

        return true;
    }
}
