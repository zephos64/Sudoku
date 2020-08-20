package main.java;

import main.java.other.Grid;
import main.java.sudokuSolver.Solver;

public class test {
    public static void main(String[] args) {
        System.out.println("Here's a sudoku!");
        Grid sudoku = new Grid();
        sudoku.createGrid("5.3.0.0.7.0.0.0.0.6.0.0.1.9.5.0.0.0.0.9.8.0.0.0.0.6.0.8.0.0.0.6.0.0.0.3.4.0.0.8.0.3.0.0.1.7.0.0.0.2.0.0.0.6.0.6.0.0.0.0.2.8.0.0.0.0.4.1.9.0.0.5.0.0.0.0.8.0.0.7.9",
                "\\.");
        sudoku.printGrid();
        //https://en.wikipedia.org/wiki/Sudoku

        System.out.println("======================================");

        long startTime = System.currentTimeMillis();
        Solver test = new Solver();
        test.attemptGrid(sudoku);
        long endTime = System.currentTimeMillis();
        sudoku.printGrid();

        System.out.println("======================================");
        System.out.println("Elapsed time: " + String.valueOf(endTime-startTime));
    }
}
