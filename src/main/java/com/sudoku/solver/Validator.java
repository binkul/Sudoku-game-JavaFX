package com.sudoku.solver;

import com.sudoku.data.Data;

public class Validator {

/*
    public static boolean checkSudoku(SudokuBoard sudokuBoard) {
        boolean result;
        for (int row = 0; row < Data.DIMENSION; row++) {
            for (int column = 0; column < Data.DIMENSION; column++) {
                result = checkUnique(sudokuBoard.getOneRowElements(row)) &
                        checkUnique(sudokuBoard.getOneColumnElements(column)) &
                        checkUnique(sudokuBoard.getOneSectionElements(row, column));
                if (!result) return false;
            }
        }
        return true;
    }

    private static boolean checkUnique(List<SudokuElement> elements) {
        return getAll(elements) == getUnique(elements);
    }

    private static long getAll(List<SudokuElement> elements) {
        return elements.stream()
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .count();
    }

    private static long getUnique(List<SudokuElement> elements) {
        return elements.stream()
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .collect(Collectors.toSet())
                .size();
    }
*/

    public static boolean[] getUniqueNumbers(int[][] sudoku, int row, int column) {
        boolean[] result = new boolean[Data.SIZE + 1];

        getUniqueInRow(result, sudoku, row);
        getUniqueInColumn(result, sudoku, column);
        getUniqueInSection(result, sudoku, row, column);
        result[sudoku[row][column]] = false;

        return result;
    }

    private static void getUniqueInRow(boolean[] result, int[][] sudoku, int row) {
        for (int i = 0; i < Data.SIZE; i++) {
            int value = sudoku[row][i];
            if (value != 0) {
                result[value] = true;
            }
        }
    }

    private static void getUniqueInColumn(boolean[] result, int[][] sudoku, int column) {
        for (int i = 0; i < Data.SIZE; i++) {
            int value = sudoku[i][column];
            if (value != 0) {
                result[value] = true;
            }
        }
    }

    private static void getUniqueInSection(boolean[] result, int[][] sudoku, int row, int column) {
        int x = 3 * (row / 3);
        int y = 3 * (column / 3);
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                int value = sudoku[i][j];
                if (value != 0) {
                    result[value] = true;
                }
            }
        }
    }
}
