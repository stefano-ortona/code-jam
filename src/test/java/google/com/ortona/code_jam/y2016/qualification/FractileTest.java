package google.com.ortona.code_jam.y2016.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import com.google.common.collect.Lists;

import google.com.ortona.code_jam.y2016.qualification.Fractiles;

public class FractileTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "D-small-practice.in";
    tidyNumbersTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "";
    tidyNumbersTest(file);
  }

  public void tidyNumbersTest(final String file) throws NumberFormatException, IOException {
    final List<Triple<Long, Long, Long>> allNum = this.readFile(file);
    int count = 1;
    final Fractiles fr = new Fractiles();
    for (final Triple<Long, Long, Long> oneNum : allNum) {
      final List<Long> allPos = fr.revealFractileSimple(oneNum.getLeft(), oneNum.getMiddle());
      final StringBuilder sB = new StringBuilder();
      allPos.forEach(p -> sB.append(p).append(" "));
      System.out.println("Case #" + count + ": " + sB.substring(0, sB.length() - 1));
      count++;
    }
  }

  private List<Triple<Long, Long, Long>> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<Triple<Long, Long, Long>> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String[] compLines = reader.readLine().split(" ");
      allNum.add(Triple.of(Long.parseLong(compLines[0]), Long.parseLong(compLines[1]), Long.parseLong(compLines[2])));
    }
    reader.close();
    return allNum;
  }

}
