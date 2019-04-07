package google.com.ortona.code_jam.y2015.round1A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import google.com.ortona.code_jam.kickstart.y2015.round1A.MushroomMonster;

public class MushroomMonsterTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "A-small-practice.in";
    executeTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "A-large-practice.in";
    executeTest(file);
  }

  public void executeTest(final String file) throws NumberFormatException, IOException {
    final List<int[]> allProbs = this.readFile(file);
    int count = 1;
    final MushroomMonster mushroom = new MushroomMonster();
    for (final int[] oneProbl : allProbs) {
      final Pair<Integer, Integer> sol = mushroom.getMinMushrooms(oneProbl);
      System.out.println("Case #" + count + ": " + sol.getLeft() + " " + sol.getRight());
      count++;
    }
  }

  private List<int[]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final int lineNum = Integer.parseInt(reader.readLine());
    final List<int[]> allProblems = new ArrayList<>();
    for (int i = 0; i < lineNum; i++) {
      reader.readLine();
      final String[] intervalsSplit = reader.readLine().split(" ");
      final int[] intervals = new int[intervalsSplit.length];
      for (int j = 0; j < intervalsSplit.length; j++) {
        intervals[j] = Integer.parseInt(intervalsSplit[j]);
      }
      allProblems.add(intervals);
    }
    reader.close();
    return allProblems;
  }

}
