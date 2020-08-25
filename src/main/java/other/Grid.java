package main.java.other;

public class Grid {
    int size = 9;

    private final Cell[][] grid;

    public Grid() {
        grid = new Cell[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isSolved() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(grid[i][j].getValue() == 0) return false;
            }
        }

        return true;
    }

    public boolean isSpotValidForValue(int x, int y, int value) {
        boolean row = doesRowHaveValue(y, value);
        boolean col = doesColHaveValue(x, value);
        boolean grid = doesSubGridHaveValue(x, y, value);
        return !(row || col || grid);
    }

    public boolean isSpotValidForGuess(int x, int y, int guess) {
        boolean row = doesRowHaveGuess(x, y, guess);
        boolean col = doesColHaveGuess(x, y, guess);
        boolean grid = doesSubGridHaveGuess(x, y, guess);
        return !(row || col || grid);
    }

    public void setSpotValue(int x, int y, int value) {
        grid[y][x].setValue(value);
    }

    public void setSpotValueAndRemoveGuesses(int x, int y, int value) {
        grid[y][x].setValue(value);
        removeGuessesFromGrid(x, y, value);
    }

    public boolean isSpotEmpty(int x, int y) {
        return grid[y][x].getValue() == 0;
    }

    public Cell getSpot(int x, int y) {
        return grid[y][x];
    }

    private boolean doesRowHaveValue(int y, int value) {
        for(int i = 0; i < grid.length; i++) {
            if(grid[y][i].getValue() == value) return true;
        }
        return false;
    }

    private boolean doesRowHaveGuess(int x, int y, int guess) {
        for(int i = 0; i < grid.length; i++) {
            if(x == i) continue;
            for(int j = 0; j < grid[y][i].getGuessAmnt(); j++) {
                if(grid[y][i].getGuess(j) == guess) return true;
            }

        }
        return false;
    }

    private boolean doesColHaveValue(int x, int value) {
        for(int i = 0; i < grid.length; i++) {
            if(grid[i][x].getValue() == value) return true;
        }
        return false;
    }

    private boolean doesColHaveGuess(int x, int y, int guess) {
        for(int i = 0; i < grid.length; i++) {
            if(y == i) continue;
            for(int j = 0; j < grid[i][x].getGuessAmnt(); j++) {
                if(grid[i][x].getGuess(j) == guess) return true;
            }
        }
        return false;
    }

    private boolean doesSubGridHaveValue(int x, int y, int value) {
        int xMinOrig = 0, yMinOrig = 0, xMax = 0, yMax = 0;

        xMinOrig = numToSubGrid(x);
        yMinOrig = numToSubGrid(y);
        xMax = xMinOrig+3;
        yMax = yMinOrig+3;

        for(int xMin = xMinOrig; xMin < xMax; xMin++) {
            for(int yMin = yMinOrig; yMin < yMax; yMin++) {
                if(yMin != y && xMin != x && grid[yMin][xMin].getValue() == value) return true;
            }
        }
        return false;
    }

    private boolean doesSubGridHaveGuess(int x, int y, int guess) {
        int xMinOrig = 0, yMinOrig = 0, xMax = 0, yMax = 0;

        xMinOrig = numToSubGrid(x);
        yMinOrig = numToSubGrid(y);
        xMax = xMinOrig+3;
        yMax = yMinOrig+3;

        for(int xMin = xMinOrig; xMin < xMax; xMin++) {
            for(int yMin = yMinOrig; yMin < yMax; yMin++) {
                for(int j = 0; j < grid[yMin][xMin].getGuessAmnt(); j++) {
                    if(grid[yMin][xMin].getValue() == guess) return true;
                }
            }
        }
        return false;
    }

    private int numToSubGrid(int num) {
        if(num < 3) return 0;
        if(num < 6) return 3;
        return 6;
    }

    public boolean createGrid(String grid) {
        String delimiter = "";
        String[] splitGrid = grid.split(delimiter);
        if(splitGrid.length != size * size) {
            System.out.println("Grid of bad size, size is " + splitGrid.length);
            return false;
        }
        int x = 0, y = 0;
        for(String s : splitGrid) {
            this.grid[y][x].setValue(Integer.valueOf(s));
            removeGuessesFromGrid(x, y, Integer.valueOf(s));
            x++;
            if(x >= size) {
                x = 0;
                y++;
            }
        }

        return true;
    }

    private void removeGuessesFromGrid(int x, int y, int value) {
        // Clear column
        for(int i = 0; i < grid.length; i++) {
            grid[i][x].removeGuess(value);
        }

        // Clear row
        for(int i = 0; i < grid.length; i++) {
            grid[y][i].removeGuess(value);
        }

        // Clear subGrid
        int xMinOrig = numToSubGrid(x);
        int yMinOrig = numToSubGrid(y);
        int xMax = xMinOrig+3;
        int yMax = yMinOrig+3;

        for(int xMin = xMinOrig; xMin < xMax; xMin++) {
            for(int yMin = yMinOrig; yMin < yMax; yMin++) {
                grid[yMin][xMin].removeGuess(value);
            }
        }
    }

    public void printGrid() {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                System.out.print(grid[y][x].getValue() + " ");
            }
            System.out.println();
        }
    }
}
