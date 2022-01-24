package Labs.lab2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private int[][] square;
    private boolean[] possible;
    private int totalSqs;
    private int sum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // n must be above 2, in order to satisfy magic square
        if(n <= 2){
            System.out.println("Please enter N above 2!!!");
        }

        else{
            System.out.println("N = " + n);
            Main magicSquare = new Main(n);
            magicSquare.fill(0, 0);
        }

    }

    // Creates an empty MagicSquare object.
    Main(int n) {
        // Fill in an empty square; 0 represents unfilled.
        square = new int[n][n];
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                square[i][j] = 0;

        // Start with all numbers being possible.
        totalSqs = n*n;
        possible = new boolean[totalSqs];
        for (int i=0; i<totalSqs; i++)
            possible[i] = true;

        sum = n*(n*n+1)/2;
    }

    public void fill(int row, int col) {
        // Ignore incorrect squares
        if (!checkRows() || !checkCols() || !checkDiags())
            return;

        // Filled everything, so print =)
        if (row == square.length) {

            System.out.println("Done!!");
            for(int[] rws: square){
                System.out.println(Arrays.toString(rws));
            }

            return;
        }

        // Try each possible number for this square.
        for (int i=0; i<totalSqs; i++) {
            if (possible[i]) {
                square[row][col] = i+1;
                possible[i] = false;

                // Go to the next square.
                int newcol = col+1;
                int newrow = row;
                if (newcol == square.length) {
                    newrow++;
                    newcol = 0;
                }

                // Recursively fill.
                fill(newrow, newcol);

                // Undo this square.
                square[row][col] = 0;
                possible[i] = true;

            }
        }
    }

    // Returns true iff all the rows are okay.
    public boolean checkRows() {

        // Try each row.
        for (int i=0; i<square.length; i++) {

            int test = 0;
            boolean unFilled = false;

            // Add up the values in this row.
            for (int j=0; j<square[i].length; j++) {
                test += square[i][j];
                if (square[i][j] == 0)
                    unFilled = true;
            }

            // If the row is filled and adds up to the wrong number,
            // this is an invalid square.
            if (!unFilled && test != sum)
                return false;
        }

        // Never found proof of an invalid row.
        return true;
    }

    // Returns true iff all the cols are okay.
    public boolean checkCols() {

        // Try each column.
        for (int j=0; j<square[0].length; j++) {

            int test = 0;
            boolean unFilled = false;

            // Add up the values in this col.
            for (int i=0; i<square.length; i++) {
                test += square[i][j];
                if (square[i][j] == 0)
                    unFilled = true;
            }

            // If the column is filled and adds up to the wrong number,
            // this is an invalid square.
            if (!unFilled && test != sum)
                return false;
        }

        // Never found proof of an invalid col.
        return true;
    }

    // Returns true iff all the diagonals are okay.
    public boolean checkDiags() {

        // Checking Forward Diagonal
        int test = 0;
        boolean unFilled = false;
        for (int i=0; i<square.length; i++) {
            test += square[i][i];
            if (square[i][i] == 0)
                unFilled = true;
        }

        if (!unFilled && test != sum)
            return false;

        // Checking Backward Diagonal
        test = 0;
        unFilled = false;
        for (int i=0; i<square.length; i++) {
            test += square[i][square.length-1-i];
            if (square[i][square.length-1-i] == 0)
                unFilled = true;
        }

        if (!unFilled && test != sum)
            return false;

        // Both diagonals were fine.
        return true;
    }
}


