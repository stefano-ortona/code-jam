package google.com.ortona.code_jam.y2014.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class CookieClickerAlphaTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "B-small-practice.in";
    executeTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "B-large-practice.in";
    executeTest(file);
  }

  public void executeTest(final String file) throws NumberFormatException, IOException {
    final List<double[]> allNum = this.readFile(file);
    int count = 1;
    final CookieClickerAlpha cA = new CookieClickerAlpha();
    for (final double[] oneInp : allNum) {
      final double curSol = cA.getMinimumSolvingTime(oneInp[0], oneInp[1], oneInp[2]);
      System.out.println("Case #" + count + ": " + curSol);
      count++;
    }
  }

  private List<double[]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<double[]> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final double[] in = new double[3];
      final String[] lineSplit = reader.readLine().split(" ");
      for (int j = 0; j < lineSplit.length; j++) {
        in[j] = Double.parseDouble(lineSplit[j]);
      }
      allNum.add(in);
    }
    reader.close();
    return allNum;
  }

}
