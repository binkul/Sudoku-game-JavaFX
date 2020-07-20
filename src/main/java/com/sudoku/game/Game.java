package com.sudoku.game;

import com.sudoku.graphic.GraphicDriver;
import com.sudoku.graphic.SudokuGraphic;
import com.sudoku.gui.SudokuForm;
import com.sudoku.solver.Solver;
import com.sudoku.sudoku.SudokuBoard;
import com.sudoku.user.Mouse;
import com.sudoku.user.UserInterface;
import lombok.Getter;

@Getter
public class Game {
    private final UserInterface userInterface;
    private final SudokuForm sudokuForm;
    private final SudokuGraphic sudokuGraphic;
    private final GraphicDriver graphicDriver;
    private SudokuBoard sudoku;

    public Game() {
        sudokuForm = new SudokuForm(this);
        userInterface = new Mouse(this);
        sudokuGraphic = new SudokuGraphic(userInterface, this);
        graphicDriver = new GraphicDriver(this);
    }

    public void initGame() {
        sudoku = new SudokuBoard();
        sudokuForm.open();
        repaintField();
    }

    public void repaintField() {
        graphicDriver.clear(sudokuGraphic.getCanvas());
        graphicDriver.drawNet(sudokuGraphic.getCanvas());
        graphicDriver.printNumbers(sudokuGraphic.getCanvas());
    }

    public void resolve() {
        Solver solver = new Solver(sudoku);
        solver.process();
        sudoku = solver.getSudokuBoard();
        repaintField();
    }

    public void startGame() {
        sudoku = new SudokuBoard();
        repaintField();
    }

    public void templateShow() {

    }

    public void randomGame() {

    }

    public SudokuBoard getSudoku() {
        return sudoku;
    }
}
