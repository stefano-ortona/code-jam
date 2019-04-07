package google.com.ortona.code_jam.y2017.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import google.com.ortona.code_jam.y2017.qualification.BathroomStallAssignment.Pair;

public class BathroomStallAssignmentTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "C-small-practice-1.in";
    tidyNumbersTest(file);
  }

  @Test
  public void testSmall2() throws NumberFormatException, IOException {
    final String file = "C-small-practice-2.in";
    tidyNumbersTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "C-large-practice.in";
    tidyNumbersTest(file);
  }

  public void tidyNumbersTest(final String file) throws NumberFormatException, IOException {
    final List<Pair<Long, Long>> allNum = this.readFile(file);
    final List<Pair<Long, Long>> outNum = Lists.newArrayList();
    final BathroomStallAssignment bs = new BathroomStallAssignment();
    int count = 1;
    for (final Pair<Long, Long> oneNum : allNum) {
      final Pair<Long, Long> curSol = bs.assignStall(oneNum.x, oneNum.y);
      System.out.println(oneNum + "---->Case #" + count + ": " + curSol.x + " " + curSol.y);
      count++;
      outNum.add(curSol);
    }
  }

  private List<Pair<Long, Long>> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<Pair<Long, Long>> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    final BathroomStallAssignment s = new BathroomStallAssignment();
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final long first = Long.parseLong(line.split(" ")[0]);
      final long second = Long.parseLong(line.split(" ")[1]);
      final Pair<Long, Long> p = s.new Pair<Long, Long>();
      p.x = first;
      p.y = second;
      allNum.add(p);
    }
    reader.close();
    return allNum;
  }

}
