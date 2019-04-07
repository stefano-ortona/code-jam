package google.com.ortona.code_jam.y2018.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class Solution_B {

  public int sortTriple(List<Long> input) {

    int curIt = input.size() - 1;
    while (true) {
      // sort
      final boolean sor = sort(input);
      if (!sor) {
        return isValid(input);
      }
      if ((curIt < (input.size() - 1)) && (input.get(curIt) < input.get(curIt + 1))) {
        return (input.size() - curIt) + 1;
      }
      curIt--;
    }
  }

  private int isValid(List<Long> input) {
    for (int i = 0; i < (input.size() - 1); i++) {
      if (input.get(i) < input.get(i + 1)) {
        return (input.size() - i) + 1;
      }
    }
    return -1;
  }

  private boolean sort(List<Long> input) {
    boolean found = false;
    for (int i = 0; i < (input.size() - 2); i++) {
      if (input.get(i) < input.get(i + 2)) {
        final long temp = input.get(i);
        input.set(i, input.get(i + 2));
        input.set(i + 2, temp);
        found = true;
      }
    }
    return found;
  }

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
    final Solution_B solInstance = new Solution_B();
    // solInstance.readAndSolve();
    final List<Long> in = Lists.newLinkedList();
    in.add(8L);
    in.add(9L);
    in.add(7L);

    System.out.println(solInstance.sortTriple(in));

  }

}
