/*
 * Hunter Broughton 
 * CS231 
 * 3/15/2023
 * 
 * This is the Sudoku.java file
 * 
 * this class implements a sudoku solver for a sudoku board.
 * 
 * currently, it is set up to solve a randomized sudoku board, with a specified number of locked cells (number specified in the main method)
 * 
 * to run this file, ensure all other files in the folder are compiled
 * 
 * how to run:
 * javac Sudoku.java
 * java Sudoku
 */


//import random
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


//start of the Sudoku class
public class Sudoku {


    //main mehtod - currently implemented to create a randomized sudoku baord and solve it
    public static void main(String[] args) throws InterruptedException {

    

        Sudoku mySudoku = new Sudoku(25);
        //mySudoku.mySudokuBoard.read("board2.txt");
        System.out.println("Initial Board:");
        System.out.println(mySudoku.mySudokuBoard);

        if (mySudoku.solve(0)) {
            System.out.println("Solved Board:");
            System.out.println(mySudoku.mySudokuBoard);
        } else {
            System.out.println("No solution found");
        }

        
    }


    //instance fields for the board, and the landscape display
    Board mySudokuBoard;
    private LandscapeDisplay ld;

    //Sudoku construcotr - creates a sudoku board with a specified number fo locked cells
    Sudoku(int numLocked) {
        this.mySudokuBoard = new Board(numLocked);
        this.ld = new LandscapeDisplay(mySudokuBoard);
    }

    //sudoku constructor that specifies if you want to dispaly the sudoku board - wont display the board in the exploration.
    Sudoku(int numLocked, boolean display){
        this.mySudokuBoard = new Board(numLocked);
        if(display){
            this.ld = new LandscapeDisplay(mySudokuBoard);
        }
    }
    

    //finds the next valid value for a cell in the sudoku board
    //returns the value if a valid value is avaliable, returns 0 if no valid value is found
    public int findNextValue(int startingValue, int row, int col) {
        for (int i = startingValue + 1; i <= 9; i++) {
            if (this.mySudokuBoard.validValue(row, col, i)) {
                return i;
            }
        }
        return 0;
    }


    //finds the next cell to solve in the sudoku board
    //once the cell is found, the method uses findNextValue to find the next valid value of the cell
    //if a valid value is found, the cell is set to that value and that cell is returned
    //otherwise, null is returned
    public Cell findNextCell() {
        for (int i = 0; i < mySudokuBoard.getRows(); i++) {
            for (int j = 0; j < mySudokuBoard.getCols(); j++) {
                Cell cell = this.mySudokuBoard.get(i, j);
                if (cell.getValue() == 0 && !cell.isLocked()) {
                    int value = findNextValue(cell.getValue(), i, j);
                    if (value != 0) {
                        cell.setValue(value);
                        return cell;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }


    /*
     * the solve method solves the sudoku board using findNextCell and findNextValue
     */
    public boolean solve(int delay) throws InterruptedException {

        //create a stack to store the values of the sudoku board
        Stack<Cell> stack = new LinkedList<>();
    
        //while the stack size is less than the number of unlocked cells
        while (stack.size() < ((mySudokuBoard.getRows() * mySudokuBoard.getCols()) - mySudokuBoard.numLocked())) {

            //update the state of the displayed board
            if (delay > 0) {
                Thread.sleep(delay);
            }
            if (ld != null) {
                ld.repaint();
            }



            //store the next cell as a vairable, next
            Cell next = findNextCell();
    

            //while next is null and the size of the stack is greater than 0 
            while(next == null && stack.size()>0){

                //pop a cell of the stack
                Cell cell = stack.pop();

                //find the next valid value for the cell
                int nextValue = findNextValue(cell.getValue(), cell.getRow(), cell.getCol());

                //if the value does note equal 0, set the cell to that value, and set next to that cell
                if(nextValue!=0){
                    cell.setValue(nextValue);
                    next = cell;
                }

                //otherwise, set the cells value to 0 to continue the process of backtracking
                else{
                    cell.setValue(0);
                }
            }

            //if next is null, the board is finished and there is no solution
            if (next == null) {
                mySudokuBoard.setFinished(true);
                if(ld != null){
                    ld.repaint();
                }
                return false;

        
            //otherwise, push next onto the stack and continue the solving process
            } else {
                stack.push(next);
            }
        }

        //while loop finished, the sudokuBoard is finsihed being solved, and their is a valid solution
        mySudokuBoard.setFinished(true);
        if(ld != null){
            ld.repaint();
        }
        return true;
    }
}

    


