/*
Hunter Broughton 
CS231A
 * 3/14/2023
 * 
 * This is the CellTests.java file - a file dedicated to test the methods of the Cell.java class
 * 
 * how to run in terminal: 
 * javac CellTests.java
 * java -ea CellTests
 */



public class CellTests {
    public static void main(String[] args) {

        // case 1: testing default Cell constructor
        {
            Cell cell = new Cell();
            assert cell != null : "Error in default Cell constructor";
            System.out.println("Default Cell constructor passed");
        }

        // case 2: testing Cell constructor with row, col, and value
        {
            Cell cell = new Cell(1, 2, 3);
            assert cell != null : "Error in Cell constructor with row, col, and value";
            System.out.println("Cell constructor with row, col, and value passed");
        }

        // case 3: testing Cell constructor with row, col, value, and locked
        {
            Cell cell = new Cell(1, 2, 3, true);
            assert cell != null : "Error in Cell constructor with row, col, value, and locked";
            System.out.println("Cell constructor with row, col, value, and locked passed");
        }

        // case 4: testing getRow()
        {
            Cell cell = new Cell(1, 2, 3);
            assert cell.getRow() == 1 : "Error in getRow()";
            System.out.println("getRow() passed");
        }

        // case 5: testing getCol()
        {
            Cell cell = new Cell(1, 2, 3);
            assert cell.getCol() == 2 : "Error in getCol()";
            System.out.println("getCol() passed");
        }

        // case 6: testing getValue()
        {
            Cell cell = new Cell(1, 2, 3);
            assert cell.getValue() == 3 : "Error in getValue()";
            System.out.println("getValue() passed");
        }

        // case 7: testing setValue()
        {
            Cell cell = new Cell(1, 2, 3);
            cell.setValue(5);
            assert cell.getValue() == 5 : "Error in setValue()";
            System.out.println("setValue() passed");
        }

        // case 8: testing isLocked()
        {
            Cell cell = new Cell(1, 2, 3, true);
            assert cell.isLocked() : "Error in isLocked()";
            System.out.println("isLocked() passed");
        }

        // case 9: testing setLocked()
        {
            Cell cell = new Cell(1, 2, 3);
            cell.setLocked(true);
            assert cell.isLocked() : "Error in setLocked()";
            System.out.println("setLocked() passed");
        }

        System.out.println("All tests passed!");
    }
}
