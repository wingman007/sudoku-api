package com.coolcsn.sudoku_api.model;

import jakarta.validation.constraints.NotNull;

public class GridDtos {

    public record Grid(@NotNull int[][] cells) {}

    public record ValidateResponse(boolean valid, boolean[][] conflicts) {}

    public record SolveResponse(boolean solvable, int[][] cells) {}
}
