/*
Hunter Broughton 
CS231A
 * 3/14/2023
 * 
 * This is the BoardTests.java file - a file dedicated to test the methods of the Board.java class
 * 
 * how to run in terminal: 
 * javac BoardTests.java
 * java -ea BoardTests
 */

import java.io.IOException;

public class BoardTests {
    public static void main(String[] args) throws IOException {

        // case 1: testing Board constructor and initialization
        {
            Board board = new Board();
            assert board != null : "Error in Board constructor";
            System.out.println("Board constructor and initialization passed");
        }

        // case 2: testing read()
        {
            Board board = new Board();
            board.read("board1.txt");
            System.out.println("read() passed");
        }

        // case 3: testing value()
        {
            Board board = new Board();
            board.read("board1.txt");
            int value = board.value(0, 3);
            assert value >= 1 && value <= 9 : "Error in value()";
            System.out.println("value() passed");
        }

        // case 4: testing set()
        {
            Board board = new Board();
            board.read("board1.txt");
            board.set(0, 0, 5);
            int newValue = board.value(0, 0);
            assert newValue == 5 : "Error in set()";
            System.out.println("set() passed");
        }

        // case 5: testing isLocked()
        {
            Board board = new Board();
            board.read("board1.txt");
            board.set(0, 0, true);
            assert board.isLocked(0, 0) : "Error in isLocked()";
            System.out.println("isLocked() passed");
        }

        // case 6: testing validValue()
        {
            Board board = new Board();
            board.read("board1.txt");
            boolean valid = board.validValue(0, 0, 5);
            assert valid : "Error in validValue()";
            System.out.println("validValue() passed");
        }

        // case 7: testing validSolution()
        {
            Board board = new Board();
            board.read("board1.txt");
            boolean valid = board.validSolution();
            assert !valid : "Error in validSolution()";
            System.out.println("validSolution() passed");
        }

        System.out.println("All tests passed!");
    }
}

