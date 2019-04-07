package google.com.ortona.code_jam.y2013.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution_B {

  public boolean lawnmowerSuccess(int[][] inputLawn) {
    // first compute rows
    final int[] maxRowComputes = new int[inputLawn.length];
    int minEl = inputLawn[0][0];

    for (int row = 0; row < inputLawn.length; row++) {
      maxRowComputes[row] = findMaxInRow(inputLawn, row);
      minEl = Math.min(minEl, maxRowComputes[row]);
    }

    for (int column = 0; column < inputLawn[0].length; column++) {
      if (!isOkColumn(inputLawn, maxRowComputes, column)) {
        if (!allSameInColumn(inputLawn, column) || (minEl < inputLawn[0][column])) {
          return false;
        }
      }
    }
    return true;

  }

  private int findMaxInRow(int[][] input, int row) {
    int max = input[row][0];
    for (int j = 1; j < input[0].length; j++) {
      max = Math.max(max, input[row][j]);
    }
    return max;
  }

  private boolean isOkColumn(int[][] input, int[] target, int column) {
    for (int i = 0; i < target.length; i++) {
      if (input[i][column] != target[i]) {
        return false;
      }
    }
    return true;
  }

  private boolean allSameInColumn(int[][] input, int column) {
    final int el = input[0][column];
    for (int i = 1; i < input.length; i++) {
      if (input[i][column] != el) {
        return false;
      }
    }
    return true;
  }

  public void readAndSolve() {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int testNumber = 1; testNumber <= t; ++testNumber) {
      final int row = in.nextInt();
      // read number of horses
      final int column = in.nextInt();
      final int[][] input = new int[row][column];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
          input[i][j] = in.nextInt();
        }
      }
      final boolean curSol = this.lawnmowerSuccess(input);
      final String out = curSol ? "YES" : "NO";
      System.out.println("Case #" + testNumber + ": " + out);
    }
    in.close();

  }

  public static void main(String[] args) {
    final Solution_B solInstance = new Solution_B();
    solInstance.readAndSolve();
  }

}
