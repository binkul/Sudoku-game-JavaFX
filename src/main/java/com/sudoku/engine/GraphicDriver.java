package com.sudoku.engine;

import com.sudoku.data.SudokuData;
import com.sudoku.game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GraphicDriver {
    private final Game game;

    public GraphicDriver(Game game) {
        this.game = game;
    }

    public void clear(Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, SudokuData.DIMENSION, SudokuData.DIMENSION);
    }

    public void drawNet(Canvas canvas) {
        canvas.getGraphicsContext2D().setStroke(Color.DARKBLUE);
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

    public void drawCrossBar(Canvas canvas, int x, int y) {
        canvas.getGraphicsContext2D().setFill(Color.LIGHTGREEN);

        clear(canvas);
        drawVertColumnBar(canvas, y);
        drawHorizColumnBar(canvas, x);
        drawAreaBar(canvas, x, y);
        drawNet(canvas);
        drawPointer(canvas, x, y);
        printNumbers(canvas);
    }

    private void drawVertColumnBar(Canvas canvas, int y) {
        canvas.getGraphicsContext2D().fillRect(y * SudokuData.TILE_DIMENSION, 0, SudokuData.TILE_DIMENSION, SudokuData.DIMENSION);
    }

    private void drawHorizColumnBar(Canvas canvas, int x) {
        canvas.getGraphicsContext2D().fillRect(0, x * SudokuData.TILE_DIMENSION, SudokuData.DIMENSION, SudokuData.TILE_DIMENSION);
    }

    private void drawAreaBar(Canvas canvas, int x, int y) {
        int x_dif = y / 3;
        int y_dif = x / 3;
        x_dif *= (SudokuData.TILE_DIMENSION * 3);
        y_dif *= (SudokuData.TILE_DIMENSION * 3);
        canvas.getGraphicsContext2D().fillRect(x_dif, y_dif, (3 * SudokuData.TILE_DIMENSION), (3 * SudokuData.TILE_DIMENSION));
    }

    private void drawPointer(Canvas canvas, int x, int y) {
        canvas.getGraphicsContext2D().setStroke(Color.RED);
        int x_dif = y * SudokuData.TILE_DIMENSION + 2;
        int y_dif = x * SudokuData.TILE_DIMENSION + 2;
        canvas.getGraphicsContext2D().strokeRect(x_dif, y_dif, SudokuData.TILE_DIMENSION, SudokuData.TILE_DIMENSION);
    }

    public void printNumbers(Canvas canvas) {
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().setFont(Font.font("Arial", FontWeight.BOLD, 48));

        for (int row = 0; row < SudokuData.SIZE; row++) {
            printRow(canvas, row);
        }
    }

    private void printRow(Canvas canvas, int row) {
        for (int column = 0; column < SudokuData.SIZE; column++) {
            int number = game.getSudoku()[row][column];
            if (number == 0) continue;
            canvas.getGraphicsContext2D().fillText(
                    String.valueOf(number),
                    SudokuData.TEXT_X_START + (column * SudokuData.TILE_DIMENSION),
                    SudokuData.TEXT_Y_START + (row * SudokuData.TILE_DIMENSION),
                    SudokuData.TILE_DIMENSION);
        }
    }
}
