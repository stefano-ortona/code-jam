package google.com.ortona.code_jam.y2015.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class StandingOvationTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "A-small-practice.in";
    getMinimunNumberAdditionaPeopleTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "A-large-practice.in";
    getMinimunNumberAdditionaPeopleTest(file);
  }

  public void getMinimunNumberAdditionaPeopleTest(final String file) throws NumberFormatException, IOException {
    final List<String> allNum = this.readFile(file);
    final StandingOvation sO = new StandingOvation();
    int count = 1;
    for (final String oneNum : allNum) {
      final int curNum = sO.getMinimunNumberAdditionaPeople(oneNum);
      System.out.println("Case #" + count + ": " + curNum);
      count++;
    }
  }

  private List<String> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<String> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String[] line = reader.readLine().split(" ");
      final int curShy = Integer.parseInt(line[0]);
      final String input = line[1];
      if (curShy != (input.length() - 1)) {
        System.out.println("ERRROR!");
      }
      allNum.add(line[1]);
    }
    reader.close();
    return allNum;
  }

}
