package google.com.ortona.code_jam.y2019.round1B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FairFight {

  public int numberFairFight(int[] firstSkills, int[] secondSkills, int maxDiff) {
    int faresFights = 0;
    for (int i = 0; i < firstSkills.length; i++) {
      for (int j = i; j < firstSkills.length; j++) {
        if (isFairFight(i, j, firstSkills, secondSkills, maxDiff)) {
          faresFights++;
        }
      }
    }
    return faresFights;
  }

  private boolean isFairFight(int i, int j, int[] firstSkills, int[] secondSkills, int maxDiff) {
    // get first best
    int firstBest = firstSkills[i];
    for (int index = i + 1; index <= j; index++) {
      firstBest = Math.max(firstBest, firstSkills[index]);
    }
    // get second best
    int secondBest = secondSkills[i];
    for (int index = i + 1; index <= j; index++) {
      secondBest = Math.max(secondBest, secondSkills[index]);
    }
    return Math.abs(firstBest - secondBest) <= maxDiff;
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    final FairFight sol = new FairFight();
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      in.nextInt();
      final int max = in.nextInt();
      in.nextLine();
      final String[] firstSkillsString = in.nextLine().split(" ");
      final int[] firstSkills = new int[firstSkillsString.length];
      for (int j = 0; j < firstSkillsString.length; j++) {
        firstSkills[j] = Integer.parseInt(firstSkillsString[j]);
      }
      final String[] secondSkillsString = in.nextLine().split(" ");
      final int[] secondSkills = new int[secondSkillsString.length];
      for (int j = 0; j < secondSkillsString.length; j++) {
        secondSkills[j] = Integer.parseInt(secondSkillsString[j]);
      }
      System.out.println("Case #" + i + ": " + sol.numberFairFight(firstSkills, secondSkills, max));
    }
    in.close();
  }

}
