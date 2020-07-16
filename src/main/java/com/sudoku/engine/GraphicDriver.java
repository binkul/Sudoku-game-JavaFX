package com.sudoku.engine;

import com.sudoku.data.SudokuData;
import com.sudoku.game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GraphicDriver {
    private final Game game;

    public GraphicDriver(Game game) {
        this.game = game;
    }

    public void drawNet(Canvas canvas) {
        canvas.getGraphicsContext2D().setStroke(Color.RED);
        canvas.getGraphicsContext2D().setLineWidth(SudokuData.LINE_WIDTH);

        drawVertLines(canvas);
        drawHorizLines(canvas);
    }

    private void drawVertLines(Canvas canvas) {
        for (int i = 2; i <= SudokuData.DIMENSION; i+=SudokuData.TILE_DIMENSION) {
            canvas.getGraphicsContext2D().strokeLine(0, i, SudokuData.DIMENSION, i);
        }
    }

    private void drawHorizLines(Canvas canvas) {
        for (int i = 2; i <= SudokuData.DIMENSION; i+=SudokuData.TILE_DIMENSION) {
            canvas.getGraphicsContext2D().strokeLine(i, 0, i, SudokuData.DIMENSION);
        }
    }
}
