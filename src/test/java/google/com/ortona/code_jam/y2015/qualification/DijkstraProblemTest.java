package google.com.ortona.code_jam.y2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.google.common.collect.Lists;

public class DijkstraProblemTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "C-small-practice.in";
    bruteForceTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "C-large-practice.in";
    bruteForceTest(file);
  }

  public void bruteForceTest(final String file) throws NumberFormatException, IOException {
    final List<Pair<String, Long>> allNum = this.readFile(file);
    final DijkstraProblem dP = new DijkstraProblem();
    int count = 1;
    for (final Pair<String, Long> oneNum : allNum) {
      if (count > 0) {
        final boolean cur = dP.bruteForce(oneNum.getLeft(), oneNum.getRight());
        final String output = cur ? "YES" : "NO";
        System.out.println("Case #" + count + ": " + output);
      }
      count++;
    }
  }

  private List<Pair<String, Long>> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<Pair<String, Long>> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final long repetition = Long.parseLong(reader.readLine().split(" ")[1]);
      final String input = reader.readLine();
      allNum.add(Pair.of(input, repetition));
    }
    reader.close();
    return allNum;
  }

}
