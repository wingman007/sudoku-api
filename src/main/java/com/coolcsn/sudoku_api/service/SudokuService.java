package com.coolcsn.sudoku_api.service;

import com.coolcsn.sudoku_api.logic.SudokuSolver;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SudokuService {

    private final SudokuSolver solver = new SudokuSolver();
    private final Random rnd = new Random();

    public int[][] generate(String difficulty) {
        int[][] grid = solvedTemplate();

        int removals = switch (difficulty.toLowerCase()) {
            case "hard" -> 55;
            case "medium" -> 45;
            default -> 35;
        };

        for (int k = 0; k < removals; k++) {
            int r = rnd.nextInt(9);
            int c = rnd.nextInt(9);
            grid[r][c] = 0;
        }

        return grid;
    }

    public boolean[][] conflicts(int[][] grid) {
        return solver.conflicts(grid);
    }

    public int[][] solve(int[][] grid) {
        int[][] copy = copyOf(grid);
        boolean ok = solver.solve(copy);
        return ok ? copy : null;
    }

    private static int[][] copyOf(int[][] a) {
        int[][] b = new int[9][9];
        for (int r = 0; r < 9; r++)
            System.arraycopy(a[r], 0, b[r], 0, 9);
        return b;
    }

    private int[][] solvedTemplate() {
        return new int[][] {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
    }
}
