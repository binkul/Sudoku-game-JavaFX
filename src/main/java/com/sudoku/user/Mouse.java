package com.sudoku.user;

import com.sudoku.data.SudokuData;
import com.sudoku.game.Game;
import javafx.scene.input.MouseEvent;

public class Mouse implements UserInterface {
    private final Game game;

    private int x_old = -1;
    private int y_old = -1;

    public Mouse(Game game) {
        this.game = game;
    }

    @Override
    public void press(Object event) {

    }

    @Override
    public void moveOver(Object event) {
        if (!(event instanceof MouseEvent)) return;

        MouseEvent mouseEvent = (MouseEvent)event;
        int x = (int)mouseEvent.getY() / SudokuData.TILE_DIMENSION;
        int y = (int)mouseEvent.getX() / SudokuData.TILE_DIMENSION;
        if (!(isOnTile(mouseEvent, x, y))) return;

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

    private boolean isOnTile(MouseEvent event, int x, int y) {
        int x_dif = (int)event.getY() % SudokuData.TILE_DIMENSION;
        int y_dif = (int)event.getX() % SudokuData.TILE_DIMENSION;
        return x_dif >=4 && y_dif >=4;
    }
}
