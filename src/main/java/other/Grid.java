package main.java.other;

public class Grid {
    private int[][] subGrid;
    private int[][] originalGrid;

    public Grid() {
        subGrid = new int[9][9];
        originalGrid = new int[9][9];
    }
    

    public boolean isSpotValidForValue(int x, int y, int value) {
        boolean row = doesRowHaveValue(y, value);
        boolean col = doesColHaveValue(x, value);
        boolean grid = doesSubGridHaveValue(x, y, value);
        return !(row || col || grid);
    }

    public void setSpotValue(int x, int y, int value) {
        subGrid[y][x] = value;
    }

    public boolean isSpotEmpty(int x, int y) {
        if(subGrid[y][x] == 0)
            return true;
        return false;
    }

    private boolean doesRowHaveValue(int y, int value) {
        for(int i = 0; i < subGrid.length; i++) {
            if(subGrid[y][i] == value) return true;
        }
        return false;
    }

    private boolean doesColHaveValue(int x, int value) {
        for(int i = 0; i < subGrid.length; i++) {
            if(subGrid[i][x] == value) return true;
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
                if(subGrid[yMin][xMin] == value) return true;
            }
        }
        return false;
    }

    private int numToSubGrid(int num) {
        if(num < 3) return 0;
        if(num < 6) return 1;
        return 2;
    }

    public boolean createGrid(String grid, String delimiter) {
        String[] splitGrid = grid.split(delimiter);
        if(splitGrid.length != 81) {
            System.out.println("Grid of bad size, size is " + splitGrid.length);
            return false;
        }
        int x = 0, y = 0;
        for(String s : splitGrid) {
            subGrid[y][x] = Integer.valueOf(s);
            originalGrid[y][x] = Integer.valueOf(s);
            x++;
            if(x >= 9) {
                x = 0;
                y++;
            }
        }

        return true;
    }

    public void resetGrid() {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                subGrid[y][x] = originalGrid[y][x];
            }
        }
    }

    public void printGrid() {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                System.out.print(subGrid[y][x] + " ");
            }
            System.out.println();
        }
    }
}
