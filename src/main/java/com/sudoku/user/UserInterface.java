package com.sudoku.user;

public interface UserInterface {
    void press(Object event);
    void moveOver(Object event);
    void setValue(Integer value);
    default void exited(Object event) {};
}
