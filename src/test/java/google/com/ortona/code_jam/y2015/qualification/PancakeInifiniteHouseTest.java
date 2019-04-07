package google.com.ortona.code_jam.y2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class PancakeInifiniteHouseTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "B-small-practice.in";
    computeMinimumTimeTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "B-large-practice.in";
    computeMinimumTimeTest(file);
  }

  public void computeMinimumTimeTest(final String file) throws NumberFormatException, IOException {
    final List<List<Integer>> allNum = this.readFile(file);
    final PancakeInfiniteHouse pH = new PancakeInfiniteHouse();
    int count = 1;
    for (final List<Integer> oneNum : allNum) {
      final int curNum = pH.computeMinimumTime(oneNum);
      System.out.println("Case #" + count + ": " + curNum);
      count++;
    }
  }

  private List<List<Integer>> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<List<Integer>> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final int inputSize = Integer.parseInt(reader.readLine());
      final String[] line = reader.readLine().split(" ");
      if (line.length != inputSize) {
        System.out.println("ERRROR!");

      } else {
        final List<Integer> current = Lists.newArrayList();
        for (int j = 0; j < inputSize; j++) {
          current.add(Integer.parseInt(line[j]));
        }
        allNum.add(current);
      }
    }
    reader.close();
    return allNum;
  }

}
