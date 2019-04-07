package google.com.ortona.code_jam.y2016.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import google.com.ortona.code_jam.y2016.qualification.CountingSheep;

public class CountingSheepTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "A-small-practice.in";
    countingSheepTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "A-large-practice.in";
    countingSheepTest(file);
  }

  public void countingSheepTest(final String file) throws NumberFormatException, IOException {
    final List<Long> allNum = this.readFile(file);
    int count = 1;
    final CountingSheep cS = new CountingSheep();
    for (final Long oneNum : allNum) {
      System.out.println(oneNum + "---->Case #" + count + ": " + cS.countSheep(oneNum));
      count++;
    }
  }

  private List<Long> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<Long> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final long first = Long.parseLong(line);
      allNum.add(first);
    }
    reader.close();
    return allNum;
  }

}
