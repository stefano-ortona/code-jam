package google.com.ortona.code_jam.y2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import com.google.common.collect.Lists;

public class OminousOminoTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "D-small-practice.in";
    bruteForceTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "C-large-practice.in";
    bruteForceTest(file);
  }

  public void bruteForceTest(final String file) throws NumberFormatException, IOException {
    final List<Triple<Integer, Integer, Integer>> allNum = this.readFile(file);
    int count = 1;
    final OminousOmino oO = new OminousOmino();
    for (final Triple<Integer, Integer, Integer> oneNum : allNum) {
      final boolean cur = oO.isNotWinnable(oneNum.getLeft(), oneNum.getMiddle(), oneNum.getRight());
      final String output = cur ? "RICHARD" : "GABRIEL";
      System.out.println("Case #" + count + ": " + output);
      count++;
    }
  }

  private List<Triple<Integer, Integer, Integer>> readFile(final String file)
      throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<Triple<Integer, Integer, Integer>> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String[] input = reader.readLine().split(" ");
      allNum.add(Triple.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
    }
    reader.close();
    return allNum;
  }

}
