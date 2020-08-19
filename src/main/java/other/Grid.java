package main.java.other;

public class Grid {
    private SubGrid[][] subGrid;

    public Grid() {
        subGrid = new SubGrid[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                subGrid[i][j] = new SubGrid();
            }
        }
    }

    

    public boolean isSpotValidForValue(int x, int y, int value) {
        return true;
    }

    public boolean doesRowHaveValue(int x, int y, int value) {
        return true;
    }

    public boolean doesColHaveValue(int x, int y, int value) {
        return true;
    }

    public boolean doesSubGridHaveValue(int x, int y, int value) {
        return true;
    }

    private int numToGrid(int num) {
        if(num < 3) return 0;
        else if(num < 6) return 1;
        else return 2;
    }

    private int numToGridCell(int num) {
        return num%3;
    }

    public boolean createGrid(String grid, String delimiter) {
        String[] splitGrid = grid.split(delimiter);
        if(splitGrid.length != 81) {
            System.out.println("Grid of bad size, size is " + splitGrid.length);
            return false;
        }
        int x = 0, y = 0;
        for(String s : splitGrid) {
            subGrid[numToGrid(y)][numToGrid(x)].setCell(
                    numToGridCell(x), numToGridCell(y), Integer.valueOf(s));
            x++;
            if(x >= 9) {
                x = 0;
                y++;
            }
        }

        return true;
    }

    public void printGrid() {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                System.out.print(subGrid[numToGrid(y)][numToGrid(x)].getCell(
                        numToGridCell(x), numToGridCell(y)) + " " );
            }
            System.out.println();
        }
    }
}
