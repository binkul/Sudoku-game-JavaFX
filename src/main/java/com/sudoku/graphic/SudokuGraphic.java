package com.sudoku.graphic;

import com.sudoku.data.Data;
import com.sudoku.game.Game;
import com.sudoku.user.UserInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

@Getter
public class SudokuGraphic extends Canvas {
    private final UserInterface userInterface;
    private final Game game;

    public SudokuGraphic(UserInterface userInterface, Game game) {
        super(Data.DIMENSION, Data.DIMENSION);
        this.userInterface = userInterface;
        this.game = game;
        initField();
    }

    private void initField() {
        if (game != null) {
            getGraphicsContext2D().getCanvas().addEventHandler(MouseEvent.MOUSE_PRESSED, this::enterMousePressed);
            getGraphicsContext2D().getCanvas().addEventHandler(MouseEvent.MOUSE_MOVED, this::enterMouseMove);
            getGraphicsContext2D().getCanvas().addEventHandler(MouseEvent.MOUSE_EXITED, this::exitMouse);
        }
    }

    @Override
    public GraphicsContext getGraphicsContext2D() {
        return super.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return super.getGraphicsContext2D().getCanvas();
    }

    private void enterMousePressed(MouseEvent event) {
        event.consume();
        if (userInterface == null) return;

        userInterface.press(event);
    }

    private void enterMouseMove(MouseEvent event) {
        event.consume();
        if (userInterface == null) return;

        userInterface.moveOver(event);
    }

    private void exitMouse(MouseEvent event) {
        event.consume();
        if (userInterface == null) return;

        userInterface.exited(event);
    }

    public void enterKeyPressed(KeyEvent event) {
        event.consume();
        if (userInterface == null) return;

        userInterface.moveOver(event);
    }
}
