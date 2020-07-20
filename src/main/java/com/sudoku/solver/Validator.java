package com.sudoku.solver;

import com.sudoku.data.Data;
import com.sudoku.sudoku.SudokuBoard;
import com.sudoku.sudoku.SudokuElement;

import java.util.List;
import java.util.stream.Collectors;

public class Validator {

    public static boolean checkSudoku(SudokuBoard sudokuBoard) {
        boolean result;
        for (int row = 0; row < Data.SIZE; row++) {
            for (int column = 0; column < Data.SIZE; column++) {
                result = checkUnique(sudokuBoard.getOneRowElements(row)) &
                        checkUnique(sudokuBoard.getOneColumnElements(column)) &
                        checkUnique(sudokuBoard.getOneSectionElements(row, column));
                if (!result) return false;
            }
        }
        return true;
    }

    private static boolean checkUnique(List<SudokuElement> elements) {
        return countAll(elements) == countUnique(elements);
    }

    private static long countAll(List<SudokuElement> elements) {
        return elements.stream()
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .count();
    }

    private static long countUnique(List<SudokuElement> elements) {
        return elements.stream()
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .collect(Collectors.toSet())
                .size();
    }

    public static boolean[] getUniqueNumbers(SudokuBoard sudoku, int row, int column) {
        boolean[] result = new boolean[Data.SIZE + 1];

        removeRepeated(result, sudoku.getOneRowElements(row));
        removeRepeated(result, sudoku.getOneColumnElements(column));
        removeRepeated(result, sudoku.getOneSectionElements(row, column));
        result[sudoku.getElement(row, column).getNumber()] = false;

        return result;
    }

    private static void removeRepeated(boolean[] result, List<SudokuElement> elements) {
        for (SudokuElement element : elements) {
            int value = element.getNumber();
            if (value != Data.EMPTY) {
                result[value] = true;
            }
        }
    }
}
