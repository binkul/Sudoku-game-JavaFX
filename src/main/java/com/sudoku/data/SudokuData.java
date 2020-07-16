package com.sudoku.data;

public class SudokuData {
    public final static int SIZE = 9;
    public final static int LINE_WIDTH = 4;
    public final static int TILE_DIMENSION = 48;
    public final static int DIMENSION = (TILE_DIMENSION * SIZE) + LINE_WIDTH;

    public static final int MARGIN = 40;
    public final static int WINDOW_WIDTH = DIMENSION + (3 * MARGIN);
    public final static int WINDOW_HEIGHT = DIMENSION + (4 * MARGIN);
}
