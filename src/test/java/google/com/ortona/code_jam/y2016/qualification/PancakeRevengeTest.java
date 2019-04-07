package google.com.ortona.code_jam.y2016.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import google.com.ortona.code_jam.y2016.qualification.PancakeRevenge;

public class PancakeRevengeTest {

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "B-small-practice.in";
    flipPancakeTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "B-large-practice.in";
    flipPancakeTest(file);
  }

  public void flipPancakeTest(final String file) throws NumberFormatException, IOException {
    final List<char[]> allPancakes = this.readFile(file);
    final PancakeRevenge pR = new PancakeRevenge();
    int count = 1;
    for (final char[] pancake : allPancakes) {
      System.out.println("Case #" + count + ": " + pR.flipPancake(pancake));
      count++;
    }
  }

  private List<char[]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<char[]> allPancakes = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final char[] pancake = readPancake(reader.readLine());
      allPancakes.add(pancake);
    }
    reader.close();
    return allPancakes;
  }

  final char[] readPancake(String line) throws IOException {
    final char[] pancake = new char[line.length()];
    for (int i = 0; i < pancake.length; i++) {
      pancake[i] = line.charAt(i);
    }
    return pancake;
  }

}
