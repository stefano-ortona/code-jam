package google.com.ortona.code_jam.y2019.round1B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManhattanCrepCart {

  public String mostPopularRectangle(List<String> positions, int max) {
    // first compute horizontal number
    final int[] horizontal = new int[max + 1];
    final int[] vertical = new int[max + 1];
    for (final String onePos : positions) {
      final char dir = onePos.charAt(4);
      if (dir == 'E') {
        final int num = Integer.parseInt(onePos.charAt(0) + "");
        for (int i = num + 1; i <= max; i++) {
          horizontal[i]++;
        }
      }
      if (dir == 'W') {
        final int num = Integer.parseInt(onePos.charAt(0) + "");
        for (int i = num - 1; i >= 0; i--) {
          horizontal[i]++;
        }
      }
      if (dir == 'S') {
        final int num = Integer.parseInt(onePos.charAt(2) + "");
        for (int i = num - 1; i >= 0; i--) {
          vertical[i]++;
        }
      }
      if (dir == 'N') {
        final int num = Integer.parseInt(onePos.charAt(2) + "");
        for (int i = num + 1; i <= max; i++) {
          vertical[i]++;
        }
      }
    }
    // compute best horizontal and vertical
    int bestHorizontal = -1;
    int horizontalCoordinate = -1;
    for (int i = 0; i <= max; i++) {
      if (horizontal[i] > bestHorizontal) {
        bestHorizontal = horizontal[i];
        horizontalCoordinate = i;
      }
    }
    int bestVertical = -1;
    int verticalCoordinate = -1;
    for (int i = 0; i <= max; i++) {
      if (vertical[i] > bestVertical) {
        bestVertical = vertical[i];
        verticalCoordinate = i;
      }
    }
    return horizontalCoordinate + " " + verticalCoordinate;
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    final ManhattanCrepCart sol = new ManhattanCrepCart();
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      final int people = in.nextInt();
      final int max = in.nextInt();
      in.nextLine();
      final List<String> allLines = new ArrayList<>();
      for (int j = 0; j < people; j++) {
        allLines.add(in.nextLine());
      }
      System.out.println("Case #" + i + ": " + sol.mostPopularRectangle(allLines, max));
    }
    in.close();
  }

}
