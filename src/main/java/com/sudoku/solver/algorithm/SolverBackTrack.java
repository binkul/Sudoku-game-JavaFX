package com.sudoku.solver.algorithm;

import com.sudoku.data.Data;
import com.sudoku.solver.Solver;
import com.sudoku.sudoku.SudokuBoard;
import com.sudoku.sudoku.SudokuElement;

import java.util.ArrayDeque;
import java.util.Deque;

public class SolverBackTrack {
    private final Solver solver;
    private SudokuBoard sudokuBoardCopy;
    private SudokuElement sudokuElement;

    public SolverBackTrack(Solver solver) {
        this.solver = solver;
    }

    public boolean process(SudokuBoard sudokuBoard) {
        this.sudokuBoardCopy = sudokuBoard;
        Deque<StackElement> stack = new ArrayDeque<>();
        StackElement stackElement;
        Result result = Result.NONE;
        int index;

        while(true) {

            switch (result) {
                case FULL_FILLED:
                    solver.setSudokuBoard(sudokuBoardCopy);
                    return true;
                case ERROR:
                    boolean loop;
                    do {
                        if (stack.size() == 0) return false;
                        stackElement = stack.pop();
                        index = (stackElement.getNumberIndex()) + 1;
                        if (index < stackElement.getCandidatesSize()) {
                            // recover present state
                            sudokuBoardCopy = stackElement.getSudokuBoard();
                            sudokuElement = stackElement.getSudokuElement();
                            if (putOnStack(stack, index)) return false;
                            loop = false;
                        } else {
                            loop = true;
                        }
                    } while (loop);
                    break;
                default:
                    sudokuElement = findFirstEmpty(sudokuBoardCopy);
                    if (putOnStack(stack, 0)) return false;
            }
            result = solver.runStandard(sudokuBoardCopy);
        }
    }

    private boolean putOnStack(Deque<StackElement> stack, int index) {

        StackElement stackElement = new StackElement(sudokuBoardCopy, sudokuElement, index);
        stack.push(stackElement);

        sudokuBoardCopy = sudokuBoardCopy.deepCopy();
        sudokuElement = findFirstEmpty(sudokuBoardCopy);

        if (sudokuElement == null) return true;
        int number = sudokuElement.getCandidate(index);
        sudokuElement.setNumber(number, Data.FONT_BACK_ALGORITHM_COLOR);

        return false;
    }

    private SudokuElement findFirstEmpty(SudokuBoard sudokuBoard) {
        for (int i = 0; i < Data.SIZE; i++) {
            for (int j = 0; j < Data.SIZE; j++) {
                if (sudokuBoard.getNumber(i, j) == Data.EMPTY) return sudokuBoard.getElement(i, j);
            }
        }
        return null;
    }
}
