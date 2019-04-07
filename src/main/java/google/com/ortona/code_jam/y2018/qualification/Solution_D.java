package google.com.ortona.code_jam.y2018.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution_D {

  public void readAndSolve() {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int testNumber = 1; testNumber <= t; ++testNumber) {
      final int firstInt = in.nextInt();
      // read number of horses
      final int inputNumbers = in.nextInt();
      for (int h = 1; h <= inputNumbers; h++) {
        final int otherINput = in.nextInt();
      }
      final Object curSol = null;
      System.out.println("Case #" + testNumber + ": " + curSol);
    }
    in.close();

  }

  public static void main(String[] args) {
    final Solution_D solInstance = new Solution_D();
    // solInstance.readAndSolve();

  }

}
