package com.sudoku.game;

import com.sudoku.engine.GraphicDriver;
import com.sudoku.gui.SudokuForm;
import com.sudoku.user.Mouse;
import com.sudoku.user.UserInterface;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class Game {
    private final UserInterface userInterface;
    private final SudokuForm sudokuForm;
    private final SudokuField sudokuField;
    private final GraphicDriver graphicDriver;
    private int[][] sudoku = {  {1, 0, 0, 0 ,4 ,0, 0, 6, 7},
                                {0, 3, 0, 2 ,0 ,0, 0, 5, 0},
                                {4, 0, 0, 0 ,0 ,0, 0, 0, 0},
                                {0, 0, 0, 8 ,0 ,0, 0, 0, 0},
                                {0, 0, 0, 0 ,0 ,6, 5, 0, 4},
                                {0, 0, 0, 7 ,0 ,0, 0, 0, 0},
                                {6, 0, 0, 0 ,0 ,0, 1, 0, 0},
                                {2, 0, 3, 0 ,0 ,0, 0, 2, 0},
                                {0, 0, 8, 0 ,1 ,0, 0, 0, 3}}; //new int[9][9];

    public Game() {
        sudokuForm = new SudokuForm(this);
        userInterface = new Mouse(this);
        sudokuField = new SudokuField(userInterface, this);
        graphicDriver = new GraphicDriver(this);
/*
        for (int i = 0; i < sudoku.length; i++) {
            System.out.println(Arrays.toString(sudoku[i]));
        }
*/
    }

    public void initGame() {
        sudokuForm.open();
        repaintField();
    }

    public void repaintField() {
        graphicDriver.clear(sudokuField.getCanvas());
        graphicDriver.drawNet(sudokuField.getCanvas());
        graphicDriver.printNumbers(sudokuField.getCanvas());
    }

    public void resolve() {

    }

    public void startGame() {

    }

    public void templateShow() {

    }

    public void randomGame() {

    }

    public int[][] getSudoku() {
        return sudoku;
    }
}
