package com.coolcsn.sudoku_api.logic;

public class SudokuSolver {

    public boolean solve(int[][] grid) {
        int[] pos = findEmpty(grid);
        if (pos == null) return true;

        int r = pos[0], c = pos[1];

        for (int v = 1; v <= 9; v++) {
            if (safe(grid, r, c, v)) {
                grid[r][c] = v;
                if (solve(grid)) return true;
                grid[r][c] = 0;
            }
        }
        return false;
    }

    private int[] findEmpty(int[][] g) {
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (g[r][c] == 0) return new int[]{r, c};
        return null;
    }

    private boolean safe(int[][] g, int r, int c, int v) {
        for (int i = 0; i < 9; i++)
            if (g[r][i] == v || g[i][c] == v) return false;

        int br = (r/3)*3, bc = (c/3)*3;

        for (int i = br; i < br+3; i++)
            for (int j = bc; j < bc+3; j++)
                if (g[i][j] == v) return false;

        return true;
    }

    public boolean[][] conflicts(int[][] g) {
        boolean[][] bad = new boolean[9][9];

        for (int r = 0; r < 9; r++) markLine(g, bad, r, true);
        for (int c = 0; c < 9; c++) markLine(g, bad, c, false);

        for (int br = 0; br < 3; br++)
            for (int bc = 0; bc < 3; bc++) {
                int[] seen = new int[10];
                for (int r = br*3; r < br*3+3; r++)
                    for (int c = bc*3; c < bc*3+3; c++) {
                        int v = g[r][c];
                        if (v != 0 && ++seen[v] > 1) bad[r][c] = true;
                    }
            }
        return bad;
    }

    private void markLine(int[][] g, boolean[][] bad, int idx, boolean row) {
        int[] seen = new int[10];

        for (int i = 0; i < 9; i++) {
            int r = row ? idx : i;
            int c = row ? i : idx;

            int v = g[r][c];
            if (v != 0 && ++seen[v] > 1) bad[r][c] = true;
        }
    }
}
