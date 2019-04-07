package google.com.ortona.code_jam.y2018.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SolutionSaveUniverse {

  public int saveUniverse(String s, long maxDamage) {
    // count number of shoot
    int numShoot = 0;
    long curDamage = 0;
    int curPower = 1;
    int index;
    for (index = s.length() - 1; index >= 0; index--) {
      if (s.charAt(index) == 'S') {
        break;
      }
    }
    if (index == -1) {
      return 0;
    }
    s = s.substring(0, index + 1);
    final int[] seenC = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      int curSeenC = i > 0 ? seenC[i - 1] : 0;
      if (s.charAt(i) == 'S') {
        numShoot++;
        curDamage += curPower;
      } else {
        curSeenC++;
        curPower *= 2;
      }
      seenC[i] = curSeenC++;
    }
    if (numShoot > maxDamage) {
      return -1;
    }
    int moves = 0;
    final char[] sequence = s.toCharArray();
    int curIndex = s.length() - 1;
    while (curDamage > maxDamage) {
      boolean found = false;
      for (int i = curIndex; i >= 1; i--) {
        if ((sequence[i] == 'S') && (sequence[i - 1] == 'C')) {
          // swap
          sequence[i] = 'C';
          sequence[i - 1] = 'S';
          curIndex = Math.min(i + 1, s.length() - 1);
          found = true;
          moves++;
          final int powerSoFar = (int) Math.pow(2, seenC[i]);
          curDamage -= powerSoFar / 2;
          seenC[i - 1] = seenC[i - 1] - 1;
          break;
        }
      }
      if (!found) {
        // should never happen
        throw new RuntimeException("ERROR!");
      }
    }
    return moves;
  }

  public void readAndSolve() {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int testNumber = 1; testNumber <= t; ++testNumber) {
      final long d = in.nextLong();
      // read number of horses
      final String s = in.nextLine().substring(1);
      final int curSol = saveUniverse(s, d);
      final String out = curSol == -1 ? "IMPOSSIBLE" : curSol + "";
      System.out.println("Case #" + testNumber + ": " + out);
    }
    in.close();

  }

  public static void main(String[] args) {
    final SolutionSaveUniverse solInstance = new SolutionSaveUniverse();
    solInstance.readAndSolve();
  }

}
