package com.sudoku.user;

public interface UserInterface {
    void press(Object event);
    default void moveOver(Object event) {};
}
