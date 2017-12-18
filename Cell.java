package com.example.helloworld.sudoku;

/**
 * Created by sowryavummarao on 9/16/17.
 */
//blockid
    // row, column within block
public class Cell {
    private int blockid;
    private int internalRow;
    private int internalColumn;
    private int value;
    public Cell(int blockid, int internalRow, int internalColumn) {
        this.blockid = blockid;
        this.internalRow = internalRow;
        this.internalColumn = internalColumn;
        value = 0;
    }
    public Cell(int blockid, int internalRow, int internalColumn,int value) {
        this.blockid = blockid;
        this.internalRow = internalRow;
        this.internalColumn = internalColumn;
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public int getBlockid(){
        return blockid;
    }
    public int getInternalRow(){
        return internalRow;
    }
    public int getInternalColumn(){
        return internalColumn;
    }
    public String toString(){
        return blockid +" - "+ internalRow + " - " + internalColumn;
    }
//    public static void main(String[] args) {
//        Cell cell = new Cell(1,1,1);
//        Cell cell1 = new Cell(1,1,1,1);
//        System.out.println(cell.getValue());
//        System.out.println(cell1.getValue());
//    }
}


