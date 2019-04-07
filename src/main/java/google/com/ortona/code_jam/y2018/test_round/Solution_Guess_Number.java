package google.com.ortona.code_jam.y2018.test_round;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution_Guess_Number {

  public static void solveSingleTest(Scanner in, long lower, long upper, int maxAttempts) {
    // read lower, upper bound
    String output = "";
    int att = 0;
    while (!output.equals("CORRECT")) {
      att++;
      if (att > maxAttempts) {
        throw new RuntimeException("Too many attempts!");
      }
      if (upper <= lower) {
        throw new RuntimeException("Should never happen!");
      }
      final long nextGuess = ((upper - lower) / 2) + lower + 1;
      // nextGuess = (upper - lower) == 1 ? upper : nextGuess;
      System.out.println(nextGuess);
      output = in.nextLine();
      if (output.equals("TOO_SMALL")) {
        lower = nextGuess;
      }
      if (output.equals("TOO_BIG")) {
        upper = nextGuess - 1;
      }
      if (output.equals("WRONG_ANSWER")) {
        throw new RuntimeException("Should never happen!");
      }
    }
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int testCases = in.nextInt();
    for (int i = 0; i < testCases; i++) {
      final long lower = in.nextLong();
      final long upper = in.nextLong();
      final int maxAttempts = in.nextInt();
      in.nextLine();
      solveSingleTest(in, lower, upper, maxAttempts);
    }
    in.close();
  }

}
