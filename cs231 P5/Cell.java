/*
 * Hunter Broughton 
 * CS231
 * 
 * 3/11/2023
 * 
 * This is the Cell.java class:
 * The cell.java class implements the cell object, which is represented as a singular integer between 0 and 9
 * on a sudoku board
 * 
 * ensure this file is compiled before running the final program: sudoku.java
 * 
 * how to compile: 
 * 
 * javac Cell.java
 */


 //import libraries
import java.awt.Color;
import java.awt.Graphics;


//start of the cell class
public class Cell {

    //main method - currently implemented to test out a few of the cell methods and print out the resulting cell state
    public static void main(String[] args) {
        Cell myCell = new Cell();
        myCell.setValue(8);
        myCell.setLocked(true);
        System.out.println(myCell);
    }


    //instance fields for the row, column, value, and locked state of the cell
    int row; 
    int column;
    int value; 
    boolean locked;

    //default cell constructor - creates a cell with value 0, at (0, 0), and it wont be locked 
    public Cell(){
        this.row = 0;
        this.column = 0;
        this.value = 0;
        this.locked = false;
    }

    //cell constructor that specifies the row, column, and value of a cell
    public Cell(int row, int col, int value){
        this.row = row;
        this.column = col;
        this.value = value;
        this.locked = false;
    }

    //cell constructor that specifieds the row, column, value, and locked state of the cell
    public Cell(int row, int col, int value, boolean locked){
        this.row = row;
        this.column = col;
        this.value = value;
        this.locked = locked;
    }

    //gets the row of the cell
    public int getRow(){
        return this.row;
    }

    //gets the column of the cell
    public int getCol(){
        return this.column;
    }

    //gets the value of the cell
    public int getValue(){
        return this.value;
    }

    //sets the value of the cell
    public void setValue(int newVal){
        this.value = newVal;
    }

    //returns whether or not the cell is locked
    public boolean isLocked(){
        return this.locked;
    }

    //sets the cell to a specified lock state
    public void setLocked(boolean lock){
        this.locked = lock;
    }

    //creates a string representation of the cell (simply a string containing the cell value)
    public String toString(){
        String returnedString = "" + this.getValue();
        return returnedString;
    }

    //draws the cell onto the board - colored blue if locked, red if otherwise
    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
}
