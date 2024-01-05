/*
 * Hunter Broughton 
 * CS231A
 * 3/15/2023
 * 
 * 
 * The Board Class sets up a gameboard consisting of 81 cells for our sudoku puzzle. 
 * 
 * 
 * Please ensure this class is compiled before running the final program: sudoku.java
 * 
 * How to compile: 
 * Javac Board.java
 * 
 * 
 */


 //import libraries
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;


//start of the board class
public class Board {


    //main method - currently implemented to read in a .txt file from the command line
    //the main method will take this txt file and create/print a board object with the correct, matching data
    //this main method is set up to test the read method in our class. 


    public static void main(String[] args) {

        // create a scanner objecft
        Scanner scanner = new Scanner(System.in);

        //tell the user to enter the file name
        System.out.println("Enter board file name: ");

        //get the file name from the command line
        String fileName = scanner.nextLine();
        scanner.close();

        //create a board object
        Board myBoard = new Board();

        //read the file to the board object
        myBoard.read("board1.txt");

        //print out the created board 
        System.out.println(myBoard);
        
        


    }



    //instance field for a 2d array of cell objects
    private Cell[][] surface = new Cell[9][9];

    //the size will always be 9, hence the final keyword
    public static final int SIZE = 9;

    //instance field for if the board is finished
    boolean finished;

    //board default constructor - creates a board of only 0s
    Board(){
        for(int i = 0; i < surface.length; i++){
            for(int j = 0; j < surface[i].length; j++){
                surface[i][j] = new Cell(i, j, 0);
            }
        }
    }


    // Constructor that creates a board with a specified number of locked cells
    public Board(int lockedNum) {
        surface = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                surface[i][j] = new Cell(i, j, 0);
            }
        }

        int lockedCells = 0;
        Random rand = new Random();

        while (lockedCells < lockedNum) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);

            if (surface[row][col].getValue() == 0) {
                int value = rand.nextInt(9) + 1;
                if (validValue(row, col, value)) {
                    surface[row][col].setValue(value);
                    surface[row][col].setLocked(true);
                    lockedCells++;
                }
            }
        }
    }



    //draw method - creates the visual of the board for the final program - sudoku.java
    public void draw(Graphics g, int scale){
        for(int i = 0; i<getRows(); i++){
            for(int j = 0; j<getCols(); j++){
                get(i, j).draw(g, j*scale+5, i*scale+10, scale);
            }
        } if(finished){
            if(validSolution()){
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
            }
        }
    }

    //returns the number of COLs in the board
    public int getCols(){
        return SIZE;
    }

    //sets wether or not the board is finished
    public void setFinished(boolean var){
        this.finished = var;
    }


    //gets the finished status of the board
    public boolean getFinished(){
        return this.finished;
    }

    //gets the number of rows of the baord
    public int getRows(){
        return SIZE;
    }

    //gets a specified cell from the board
    public Cell get(int row, int col){
        return surface[row][col];
    }

    //returns wether or not a specified cell is locked
    public boolean isLocked(int r, int c){
        return (surface[r][c]).isLocked();
    }

    //returns the number of locekd cells on the board
    public int numLocked(){
        int numLocked = 0;
        for(int i = 0; i < surface.length; i++){
            for(int j = 0; j < surface[i].length; j++){
                if(surface[i][j].isLocked()){
                    numLocked++;
                }
            }
        }
        return numLocked;
    }

    //returns the value of a specified cell
    public int value(int r, int c){
        return surface[r][c].getValue();
    }


    //sets a specified cell to a specified value
    public void set(int row, int col, int value){
        surface[row][col].setValue(value);
    }

    //sets a specified cell to a specified locked status
    public void set(int row, int col, boolean locked){
        surface[row][col].setLocked(locked);
    }

    //returns wether or not a specified cell and value is a valid value
    public boolean validValue(int row, int col, int value) {
        for (int i = 0; i < getCols(); i++) {
            if (i != col && surface[row][i].getValue() == value) {
                return false;
            }
        }

        for (int i = 0; i < getRows(); i++) {
            if (i != row && surface[i][col].getValue() == value) {
                return false;
            }
        }

        int boxX = row / 3 * 3;
        int boxY = col / 3 * 3;
        for (int i = boxX; i < boxX + 3; i++) {
            for (int j = boxY; j < boxY + 3; j++) {
                if (i != row && j != col && surface[i][j].getValue() == value) {
                    return false;
                }
            }
        }
        return true;
    }
    
    


    //returns wether the board is a valid solution
    public boolean validSolution(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int value = surface[i][j].getValue();
                if(value == 0 || !validValue(i, j, value)){
                    return false;
                }
            }
        }
        return true;
    }


    //turns the board to a .txt file that is read in
    public boolean read(String filename) {
        try {
          // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
          FileReader fr = new FileReader(filename);
          // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
          BufferedReader br = new BufferedReader(fr);
          
          // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
          String line = br.readLine();
          // start a while loop that loops while line isn't null
          int counter = 0;
          while(line != null){
              // print line
              int counter2 = 0;
            //   System.out.println(line);
              // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
              String[] lineString = line.split("[ ]+");
              // print the size of the String array (you can use .length)
            //   System.out.println(lineString.length);
              // use the line to set various Cells of this Board accordingly
              for(String num: lineString){
                int number = Integer.parseInt(num);
                surface[counter][counter2].setValue(number);
                counter2++;
              }
              // assign to line the result of calling the readLine method of your BufferedReader object.
              line = br.readLine();
              counter++;
          }
          // call the close method of the BufferedReader
          br.close();
          return true;
        }
        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
    
        return false;
      }


      //returns a string representation of the board
    public String toString(){
        String returnedString = "";
        for(int i = 0; i < surface.length; i++){
            returnedString += "\n";
            for(int j = 0; j < surface[i].length; j++){
                returnedString += surface[i][j] + " ";
            }
        }
        return returnedString;
    }
}
