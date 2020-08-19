package main.java.other;

public class SubGrid {
    private int[][] cells;
    private boolean[] nums;

    public SubGrid() {
        cells = new int[3][3];
        nums = new boolean[10];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = false;
        }
    }

    public int getCell(int x, int y) {
        return cells[y][x];
    }

    public void setCell(int x, int y, int value) {
        cells[y][x] = value;
        nums[value] = true;
    }

    public boolean doesSubGridHaveValue(int value) {
        if(nums[value]) return false;
        return true;
    }
}
