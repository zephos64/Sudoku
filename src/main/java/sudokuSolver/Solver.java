package main.java.sudokuSolver;

import main.java.other.Grid;

public class Solver {
    int firstValue;
    public Solver() {
        firstValue = 1;
    }

    public void attemptGrid(Grid grid) {
        setFirstValue(grid);

        // Need to implement backtracking, not complete fail-out
        while(!attemptGrid_helper(grid)) {
            firstValue++;
            if(firstValue > 10) {
                System.out.println("Bug found, failing out");
                return;
            }

            grid.resetGrid();
            setFirstValue(grid);
        }
    }

    private boolean attemptGrid_helper(Grid grid) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(!grid.isSpotEmpty(i, j)) {
                    break;
                }

                boolean setValue = false;
                for(int guess = 1; guess < 10; guess++) {
                    if (grid.isSpotValidForValue(i, j, guess)) {
                        grid.setSpotValue(i, j, guess);
                        setValue = true;
                        break;
                    }
                }

                if(!setValue) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setFirstValue(Grid grid) {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!grid.isSpotEmpty(i, j)) {
                    break;
                }
                for(; firstValue < 10; firstValue++) {
                    if (grid.isSpotValidForValue(i, j, firstValue)) {
                        grid.setSpotValue(i, j, firstValue);
                        return;
                    }
                }
            }
        }
    }
}
