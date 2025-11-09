package com.coolcsn.sudoku_api.controller;

import com.coolcsn.sudoku_api.model.GridDtos.Grid;
import com.coolcsn.sudoku_api.model.GridDtos.SolveResponse;
import com.coolcsn.sudoku_api.model.GridDtos.ValidateResponse;
import com.coolcsn.sudoku_api.service.SudokuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SudokuController {

    private final SudokuService service;

    public SudokuController(SudokuService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/sudoku/generate")
    public int[][] generate(@RequestParam(defaultValue = "easy") String difficulty) {
        return service.generate(difficulty);
    }

    @PostMapping("/sudoku/validate")
    public ValidateResponse validate(@RequestBody @Valid Grid grid) {
        boolean[][] bad = service.conflicts(grid.cells());
        boolean any = false;

        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (bad[r][c]) any = true;

        return new ValidateResponse(!any, bad);
    }

    @PostMapping("/sudoku/solve")
    public ResponseEntity<SolveResponse> solve(@RequestBody @Valid Grid grid) {
        int[][] solved = service.solve(grid.cells());
        if (solved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new SolveResponse(true, solved));
    }
}
