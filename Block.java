package com.example.helloworld.sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sowryavummarao on 9/16/17.
 */
//Unnecessary sets?
public class Block {
    private Set<Integer>[][] array = new HashSet[3][3];
    public Block() {
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                array[r][c] = new HashSet<Integer>();
                for (int i = 1; i < 10; i++) {
                    array[r][c].add(i);
                }
            }
        }
    }
    public Block(Block block){
        for (int r = 0; r < array.length;r++){
            for(int c = 0;c<array[r].length;c++){
                array[r][c] = new HashSet<>(block.array[r][c]);
            }
        }
    }
    public Block clone(){
        Block clone = new Block();
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                clone.array[r][c] = new HashSet<>(array[r][c]);
            }
        }
        return clone;
    }
    public void remove(int row,int column, int value){
        array[row][column].remove(value);
    }
    public boolean isRemovable(int row,int column,int value){
        return !(array[row][column].size() == 1 && array[row][column].contains(value));
    }
    public void setValue(int row, int column, int value) {
        if (isFeasible(row, column, value)) {
            for (int r = 0; r < array.length; r++) {
                for (int c = 0; c < array[r].length; c++) {
                    if (r == row && c == column) {
                        array[row][column].clear();
                        array[row][column].add(value);
                    } else {
                        array[r][c].remove(value);
                    }
                }
            }
        }
    }
    public int getValue(int row, int column){
        if (array[row][column].size() == 1){
            return (Integer) array[row][column].toArray()[0];
        }else{
            throw new IllegalArgumentException("For row " + row + " and column " + column + ", there is more than one possible value.");
        }
    }
    public boolean isFeasible(int row, int column, int value){
        for (int r = 0;r < array.length;r++){
            for(int c = 0;c < array[r].length;c++){
                if (r == row && c == column) {
                    continue;
                }
                else{
                    if (array[r][c].size() == 1 & array[r][c].contains(value)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public Integer[] getPossibleValues(int row, int column) {
        return array[row][column].toArray(new Integer[array[row][column].size()]);
    }
}
