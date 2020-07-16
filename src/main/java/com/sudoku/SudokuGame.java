package com.sudoku;

import com.sudoku.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class SudokuGame extends Application {
    Game game = new Game();

    @Override
    public void start(Stage primaryStage) {
        game.initGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
