package com.sudoku.user;

public interface UserInterface {
    void press(Object event);
    void moveOver(Object event);
    default void exited(Object event) {};
}
