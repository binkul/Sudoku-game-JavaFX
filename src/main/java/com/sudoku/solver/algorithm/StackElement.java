package com.sudoku.solver.algorithm;

import com.sudoku.sudoku.SudokuBoard;
import com.sudoku.sudoku.SudokuElement;

public class StackElement {
    private final SudokuBoard sudokuBoard;
    private final SudokuElement sudokuElement;
    private final int numberIndex;

    public StackElement(SudokuBoard sudokuBoard, SudokuElement sudokuElement, int numberIndex) {
        this.sudokuBoard = sudokuBoard;
        this.sudokuElement = sudokuElement;
        this.numberIndex = numberIndex;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public SudokuElement getSudokuElement() {
        return sudokuElement;
    }

    public int getNumberIndex() {
        return numberIndex;
    }

    public int getCandidatesSize() {
        return sudokuElement.getCandidatesSize();
    }
}
