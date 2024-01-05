/*
Hunter Broughton 
CS231A
 * 3/14/2023
 * 
 * This is the Exploration file 
 * This class implements an indepth simulation of our sudoku.java class
 * 
 *
 * The exploration class tests a range of values for the number of locked cells on the board
 * it then executes a sudoku game with the specified number of locked cells for a specified number of trials
 * in the simulation, the average run time and number of successful solves is recorded for each test in the simulation.
 * After executing the simulation, our data is printed to a .txt file, where it can be used to create a graph in excel.
 * 
 * how to run in terminal: 
 * javac Exploration.java
 * java Exploration
 */



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Exploration {

    public static void main(String[] args) throws InterruptedException {
        int[] numLocked = {1, 5, 10, 15, 20, 25, 30, 35, 40};
        int trials = 10;
        int delay = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {
            writer.write("Initial Values, Successes, Total Trials, Average Time (ms)\n");

            for (int initialValuesCount : numLocked) {
                int successes = 0;
                long totalTime = 0;

                for (int i = 0; i < trials; i++) {
                    Sudoku sudoku = new Sudoku(initialValuesCount, false);

                    long startTime = System.currentTimeMillis();
                    boolean solved = sudoku.solve(delay);
                    long endTime = System.currentTimeMillis();

                    if (solved) {
                        successes++;
                        totalTime += (endTime - startTime);
                    }
                }

                double averageTime = successes > 0 ? (double) totalTime / successes : 0;

                writer.write(initialValuesCount + ", " + successes + ", " + trials + ", " + averageTime + "\n");
                System.out.println("Simulation completed for " + initialValuesCount + " initial values.");
            }

            System.out.println("Results saved in results.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


