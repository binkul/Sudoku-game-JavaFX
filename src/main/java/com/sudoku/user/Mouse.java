package com.sudoku.user;

import com.sudoku.data.SudokuData;
import com.sudoku.game.Game;
import com.sudoku.gui.NumberForm;
import javafx.scene.input.MouseEvent;

public class Mouse implements UserInterface {
    private final Game game;

    private int x, y;
    private int x_old = -1;
    private int y_old = -1;

    public Mouse(Game game) {
        this.game = game;
    }

    @Override
    public void press(Object event) {
        if (isMouseValid(event)) return;

        int number = game.getSudoku()[x][y];

        String answer = NumberForm.show(number);
        if (answer.length() > 0 && !answer.equals("Remove")) {
            game.getSudoku()[x][y] = Integer.parseInt(answer);
        } else {
            game.getSudoku()[x][y] = 0;
        }
        game.getGraphicDriver().drawCrossBar(game.getSudokuField().getCanvas(), x, y);
    }

    @Override
    public void moveOver(Object event) {
        if (isMouseValid(event)) return;

        if (x != x_old || y != y_old) {
            x_old = x;
            y_old = y;
            game.getGraphicDriver().drawCrossBar(game.getSudokuField().getCanvas(), x, y);
        }
    }

    @Override
    public void exited(Object event) {
        game.getGraphicDriver().clear(game.getSudokuField().getCanvas());
        game.getGraphicDriver().drawNet(game.getSudokuField().getCanvas());
        game.getGraphicDriver().printNumbers(game.getSudokuField().getCanvas());
        x_old = -1;
        y_old = -1;
    }

    private boolean isOnTile(MouseEvent event) {
        int x_dif = (int)event.getY() % SudokuData.TILE_DIMENSION;
        int y_dif = (int)event.getX() % SudokuData.TILE_DIMENSION;
        return x_dif >=4 && y_dif >=4;
    }

    private boolean isMouseValid(Object event) {
        if (!(event instanceof MouseEvent)) return true;
        MouseEvent mouseEvent = (MouseEvent)event;

        x = (int)mouseEvent.getY() / SudokuData.TILE_DIMENSION;
        y = (int)mouseEvent.getX() / SudokuData.TILE_DIMENSION;

        return (!isOnTile(mouseEvent));
    }
}
