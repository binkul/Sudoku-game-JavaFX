package com.sudoku.sudoku;

import com.sudoku.data.Data;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuElement {
    private static final Color FONT_COLOR = Data.FONT_USER_COLOR;
    private int number;
    private List<Integer> candidates;
    private Color fontColor;

    public SudokuElement() {
        number = Data.EMPTY;
        candidates = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        fontColor = FONT_COLOR;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumber(int number, Color fontColor) {
        this.number = number;
        this.fontColor = fontColor;
    }

    public List<Integer> getCandidates() {
        return candidates;
    }

    void setCandidates(List<Integer> candidates) {
        this.candidates = candidates;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void removeCandidate(Integer number) {
        candidates.remove(number);
    }

    public int getCandidatesSize() {
        return candidates.size();
    }

    public Integer getCandidate(int index) {
        return candidates.get(index);
    }

    public boolean containCandidate(Integer number) {
        return candidates.contains(number);
    }

    @Override
    public String toString() {
        return "SudokuElement{" + "Number = " + number +
                ", Possible Numbers = " +
                candidates.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "[", "]"))+
                '}';
    }
}
