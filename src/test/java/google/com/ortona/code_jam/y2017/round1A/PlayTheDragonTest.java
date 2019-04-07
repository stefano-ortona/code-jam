package google.com.ortona.code_jam.y2017.round1A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class PlayTheDragonTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "C-small-practice.in";
    defeatKinght(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "A-large-practice.in";
    defeatKinght(file);
  }

  public void defeatKinght(final String file) throws NumberFormatException, IOException {
    final List<int[]> allInputs = this.readFile(file);
    final PlayTheDragon pD = new PlayTheDragon();
    int count = 1;
    for (final int[] oneIn : allInputs) {
      final int out = pD.defeatKnife(oneIn[0], oneIn[1], oneIn[2], oneIn[3], oneIn[4], oneIn[5]);
      final String outS = out == -1 ? "IMPOSSIBLE" : out + "";
      System.out.println("Case #" + count + ": " + outS);
      count++;
    }
  }

  private List<int[]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<int[]> allInputs = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final int[] oneInp = new int[6];
      int j = 0;
      for (final String oneS : line.split(" ")) {
        oneInp[j] = Integer.parseInt(oneS);
        j++;
      }
      allInputs.add(oneInp);
    }
    reader.close();
    return allInputs;
  }

}
