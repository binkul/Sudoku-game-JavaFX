package com.sudoku.user;

import com.sudoku.data.Data;
import com.sudoku.game.Game;
import com.sudoku.gui.NumberForm;
import com.sudoku.solver.Validator;
import javafx.scene.input.MouseEvent;

public class Mouse implements UserInterface {
    private final Game game;

    private int x, y;
    private int x_old = -1;
    private int y_old = -1;
    private Integer value;

    public Mouse(Game game) {
        this.game = game;
        this.value = 0;
    }

    @Override
    public void press(Object event) {
        if (isMouseValid(event)) return;

        int number = game.getSudoku()[x][y];
        boolean[] numbers = Validator.getUniqueNumbers(game.getSudoku(), x, y);

        NumberForm numberForm = new NumberForm(numbers, this);
        value = 0;
        numberForm.open();
        game.getSudoku()[x][y] = value;
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

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    private boolean isOnTile(MouseEvent event) {
        int x_dif = (int)event.getY() % Data.TILE_DIMENSION;
        int y_dif = (int)event.getX() % Data.TILE_DIMENSION;
        return x_dif >=4 && y_dif >=4;
    }

    private boolean isMouseValid(Object event) {
        if (!(event instanceof MouseEvent)) return true;
        MouseEvent mouseEvent = (MouseEvent)event;

        x = (int)mouseEvent.getY() / Data.TILE_DIMENSION;
        y = (int)mouseEvent.getX() / Data.TILE_DIMENSION;

        return (!isOnTile(mouseEvent));
    }
}
