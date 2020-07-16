package com.sudoku.user;

import com.sudoku.game.Game;

public class Mouse implements UserInterface {
    private final Game game;

    public Mouse(Game game) {
        this.game = game;
    }

    @Override
    public void press(Object event) {

    }
}
