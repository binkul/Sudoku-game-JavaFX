package com.sudoku.game;

import com.sudoku.engine.GraphicDriver;
import com.sudoku.gui.SudokuForm;
import com.sudoku.user.Mouse;
import com.sudoku.user.UserInterface;
import lombok.Getter;

@Getter
public class Game {
    private final UserInterface userInterface;
    private final SudokuForm sudokuForm;
    private final SudokuField sudokuField;
    private final GraphicDriver graphicDriver;

    public Game() {
        sudokuForm = new SudokuForm(this);
        userInterface = new Mouse(this);
        sudokuField = new SudokuField(userInterface, this);
        graphicDriver = new GraphicDriver(this);
    }

    public void initGame() {
        sudokuForm.open();
        graphicDriver.drawNet(sudokuField.getCanvas());
    }

    public void resolve() {

    }

    public void startGame() {

    }

    public void templateShow() {

    }

    public void randomGame() {

    }
}
