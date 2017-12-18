package com.example.helloworld.sudoku;

import com.example.helloworld.nqueenproblem.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sowryavummarao on 9/16/17.
 */
public class SudokuBoard {
    private Block[] blocks = new Block[9];

    public SudokuBoard() {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block();
        }
    }
    public void showBoard(){
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                Cell cell = getCell(r,c);
                System.out.print(Arrays.toString(getPossibleValuesByCell(cell)));
            }
            System.out.println();
        }
    }
    public void setValue(int row, int column, int value) {
        List<Cell> cells = getCellsByColumn(column);
        for (Cell c : cells) {
            removeValue(c, value);
        }
        cells = getCellsByRow(row);
        for (Cell c : cells) {
            removeValue(c, value);
        }
        Cell cell = getCell(row, column);
        setValue(cell, value);
        //Fix columns and rows
    }

    public void removeValue(int row, int column, int value) {
        Cell cell = getCell(row, column);
        removeValue(cell, value);
    }

    public void removeValue(Cell cell, int value) {
        Block block = blocks[cell.getBlockid()];
        block.remove(cell.getInternalRow(), cell.getInternalColumn(), value);
    }

    public void setValue(Cell cell, int value) {
        Block block = blocks[cell.getBlockid()];
        block.setValue(cell.getInternalRow(), cell.getInternalColumn(), value);
    }

    public int getBlockNumber(int row, int column) {
        return ((row / 3) * 3 + (column / 3));
    }

    public List<Cell> getCellsByRow(int row) {
        List<Cell> cellArray = new ArrayList<>();
        for (int c = 0; c < 9; c++) {
            cellArray.add(getCell(row, c));
        }
        return cellArray;
    }

    public List<Cell> getCellsByColumn(int column) {
        List<Cell> cellArray = new ArrayList<>();
        for (int r = 0; r < 9; r++) {
            cellArray.add(getCell(r, column));
        }
        return cellArray;
    }

    public Cell getCell(int row, int column) {
        return new Cell(getBlockNumber(row, column), row % 3, column % 3);
    }

    public SudokuBoard clone() {
        SudokuBoard clone = new SudokuBoard();
        for (int i = 0; i < blocks.length; i++) {
            clone.blocks[i] = blocks[i].clone();
        }
        return clone;
    }
    public Integer[] getPossibleValuesByCell(Cell cell) {
        Block block = blocks[cell.getBlockid()];
        return block.getPossibleValues(cell.getInternalRow(), cell.getInternalColumn());
    }
    public boolean solve(int row, int column, SudokuBoard starter) {
        if (row == 9) {
            starter.showBoard();
            return true;
        }
        if (column == 9) {
            return solve(row + 1, 0, starter);
        } else {
            Cell cell = starter.getCell(row, column);
            Integer[] possibleValues = starter.getPossibleValuesByCell(cell);
            //System.out.println(cell + Arrays.toString(possibleValues));
            if (possibleValues.length > 0) {
                for (Integer value : possibleValues) {
                    SudokuBoard clone = starter.clone();
                    clone.setValue(row, column, value);
                    //System.out.println("Setting value to " + value + "for cell " + cell);
                    boolean ret = solve(row, column + 1, clone);
                    if (ret){
                        return ret;
                    }
                }
            }
        }
        return false;
    }
    public static int[][] stringToInput(String string){
        int[][] inputArray = new int[81][3];
        String[] splitArray = string.split("\\|");
        int rowCounter = 0;
        int row = 0;
        for (String element: splitArray){
            for (int column= 0;column < 9;column++){
                if (element.charAt(column) != '0') {
                    inputArray[rowCounter][0] = row;
                    inputArray[rowCounter][1] = column;
                    char character = element.charAt(column);
                    inputArray[rowCounter][2] = Character.getNumericValue(character);
                    rowCounter += 1;
                }
            }
            row += 1;
        }
        int[][] newArray = new int[rowCounter][3];
        for (int i = 0; i < newArray.length;i++){
            newArray[i] = inputArray[i];
        }
        return newArray;
    }

    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
        String inputString = "006020507|700000980|080000600|000710000|260004000|007008200|041000090|008150000|050400000";
        int[][] inputArray = stringToInput(inputString);
        for (int[] element : inputArray) {
            board.setValue(element[0],element[1],element[2]);
        }
        board.solve(0,0,board);
//        board.setValue(0, 1, 8);
//        board.setValue(0, 5, 4);
//        board.setValue(0, 7, 9);
//        board.setValue(0, 8, 7);
//        board.setValue(1, 7, 8);
//        board.setValue(2, 0, 3);
//        board.setValue(2, 2, 6);
//        board.setValue(2, 6, 5);
//        board.setValue(3, 1, 7);
//        board.setValue(3, 3, 6);
//        board.setValue(3, 4, 2);
//        board.setValue(3, 7, 3);
//        board.setValue(4,3,7);
//        board.setValue(4, 5, 1);
//        board.setValue(5, 1, 2);
//        board.setValue(5, 4, 4);
//        board.setValue(5, 5, 5);
//        board.setValue(5, 7, 6);
//        board.setValue(6, 2, 7);
//        board.setValue(6, 6, 6);
//        board.setValue(6, 8, 9);
//        board.setValue(7, 1, 6);
//        board.setValue(8, 0, 5);
//        board.setValue(8, 1, 9);
//        board.setValue(8, 3, 4);
//        board.setValue(8, 7, 2);
//        board.solve(0,0,board);
        }
}
