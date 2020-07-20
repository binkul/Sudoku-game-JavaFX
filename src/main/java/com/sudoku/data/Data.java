package com.sudoku.data;

import javafx.scene.paint.Color;

public class Data {
    public static final int EMPTY = 0;
    public final static int SIZE = 9;
    public static final int SECTION_LENGTH = 3;

    public final static int TEXT_X_START = 13;
    public final static int TEXT_Y_START = 44;

    public final static int LINE_WIDTH = 4;
    public final static int TILE_DIMENSION = 48;
    public final static int DIMENSION = (TILE_DIMENSION * SIZE) + LINE_WIDTH;

    public static final int MARGIN = 40;
    public final static int WINDOW_WIDTH = DIMENSION + (3 * MARGIN);
    public final static int WINDOW_HEIGHT = DIMENSION + (4 * MARGIN);

    public final static Color POINTER_COLOR = Color.RED;
    public final static Color LINE_COLOR = Color.DARKBLUE;
    public final static Color BACKBOARD_COLOR = Color.LIGHTGREEN;

    public final static Color FONT_USER_COLOR = Color.BLACK;
    public final static Color FONT_SINGLE_ALGORITHM_COLOR = Color.DARKRED;
    public final static Color FONT_STANDARD_ALGORITHM_COLOR = Color.GREEN;
    public final static Color FONT_BACK_ALGORITHM_COLOR = Color.BLUE;
}
