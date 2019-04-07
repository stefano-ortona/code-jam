package google.com.ortona.code_jam.y2017.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class TidyNumbersTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "B-small-practice.in";
    tidyNumbersTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "B-large-practice.in";
    tidyNumbersTest(file);
  }

  public void tidyNumbersTest(final String file) throws NumberFormatException, IOException {
    final List<Long> allNum = this.readFile(file);
    final List<Long> outNum = Lists.newArrayList();
    final TidyNumbers t = new TidyNumbers();
    int count = 1;
    for (final long oneNum : allNum) {
      final long curTidy = t.getLatestTidy(oneNum);
      System.out.println(oneNum + "---->Case #" + count + ": " + curTidy);
      count++;
      outNum.add(curTidy);
    }
  }

  private List<Long> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<Long> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      allNum.add(Long.parseLong(reader.readLine()));
    }
    reader.close();
    return allNum;
  }

}
