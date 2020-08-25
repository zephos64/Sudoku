package main.java;

import main.java.other.Grid;
import main.java.sudokuSolver.Solver;

public class test {
    public static void main(String[] args) {
        System.out.println("Here's a sudoku!");
        Grid sudoku = new Grid();

        //sudoku.createGrid("530070000600195000098000060800060003400803001700020006060000280000419005000080079");

        // This can be solved with just single-candidcies method
        sudoku.createGrid("026500090500079004300010000600000807075020010010000400000308902700060040030200100");

        //sudoku.createGrid("000002904087009003409000050028090000000007000000080006000400078010060000034900001");
        // Just BT, around 32
        // SC and BT: 6 to 16

        sudoku.printGrid();
        //https://en.wikipedia.org/wiki/Sudoku

        System.out.println("======================================");

        long startTime = System.currentTimeMillis();
        Solver test = new Solver(sudoku);
        test.attemptGrid();
        long endTime = System.currentTimeMillis();
        test.printGrid();

        System.out.println("======================================");
        System.out.println("Elapsed time: " + String.valueOf(endTime-startTime));
    }
}
