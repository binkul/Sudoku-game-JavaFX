package com.sudoku.sudoku;


import com.sudoku.data.Data;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuBoard {
    private List<SudokuRow> rows;

    public SudokuBoard() {
        rows = createRows();
    }

    private List<SudokuRow> createRows() {
        return IntStream.range(0, Data.SIZE)
                .mapToObj(i -> new SudokuRow())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<SudokuRow> getRows() {
        return rows;
    }

    private SudokuRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public SudokuElement getElement(int rowIndex, int columnIndex) {
        return getRow(rowIndex).getSudokuElement(columnIndex);
    }

    public void setNumber(int rowIndex, int columnIndex, int number) {
        getElement(rowIndex, columnIndex).setNumber(number);
    }

    public int getNumber(int rowIndex, int columnIndex) {
        return getElement(rowIndex, columnIndex).getNumber();
    }

    private void setCandidates(int rowIndex, int columnIndex, List<Integer> numbers) {
        getElement(rowIndex, columnIndex).setCandidates(numbers);
    }

    Color getFontColor(int rowIndex, int columnIndex) {
        return getElement(rowIndex, columnIndex).getFontColor();
    }

    private void setFontColor(int rowIndex, int columnIndex, Color fontColor) {
        getElement(rowIndex, columnIndex).setFontColor(fontColor);
    }

    public List<SudokuElement> getOneRowElements(int rowIndex) {
        return getRow(rowIndex).getSudokuRow();
    }

    public List<SudokuElement> getOneColumnElements(int columnIndex) {
        return rows.stream()
                .map(i -> i.getSudokuElement(columnIndex))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<SudokuElement> getOneSectionElements(int rowIndex, int columnIndex) {
        List<SudokuElement> result = new ArrayList<>();
        int rowStart = (rowIndex / Data.SECTION_LENGTH) * Data.SECTION_LENGTH;
        int columnStart = (columnIndex / Data.SECTION_LENGTH) * Data.SECTION_LENGTH;

        for (int i = rowStart; i < rowStart + Data.SECTION_LENGTH; i++) {
            for (int j = columnStart; j < columnStart + Data.SECTION_LENGTH; j++) {
                SudokuElement s = getRow(i).getSudokuElement(j);
                result.add(s);
            }
        }

        return result;
    }

    public SudokuBoard deepCopy() {
        SudokuBoard clonedBoard = new SudokuBoard();

        for (int i = 0; i < Data.SIZE; i++) {
            for (int j = 0; j < Data.SIZE; j++) {
                SudokuElement oldElement = getRow(i).getSudokuElement(j);
                List<Integer> possibleNumbers = new ArrayList<>(oldElement.getCandidates());
                clonedBoard.setCandidates(i, j, possibleNumbers);
                clonedBoard.setNumber(i, j, oldElement.getNumber());
                clonedBoard.setFontColor(i, j, oldElement.getFontColor());
            }
        }

        return clonedBoard;
    }
}
