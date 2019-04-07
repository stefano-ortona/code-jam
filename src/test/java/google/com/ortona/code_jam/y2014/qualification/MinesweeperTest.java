package google.com.ortona.code_jam.y2014.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class MinesweeperTest {

  @Test
  public void testExample() throws NumberFormatException, IOException {
    final String file = "c-example.in";
    executeTest(file);
  }

  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "C-small-practice.in";
    executeTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "C-large-practice.in";
    executeTest(file);
  }

  public void executeTest(final String file) throws NumberFormatException, IOException {
    final List<int[]> allNum = this.readFile(file);
    int count = 1;
    final Minesweeper mS = new Minesweeper();
    for (final int[] oneInp : allNum) {
      final char[][] board = mS.solvePuzzle(oneInp[0], oneInp[1], oneInp[2]);
      System.out.println("Case #" + count + ":");
      if (board == null) {
        System.out.println("Impossible");
      } else {
        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[0].length; j++) {
            System.out.print(board[i][j]);
          }
          System.out.println();
        }
      }
      count++;
    }
  }

  private List<int[]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<int[]> allNum = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final int[] in = new int[3];
      final String[] lineSplit = reader.readLine().split(" ");
      for (int j = 0; j < lineSplit.length; j++) {
        in[j] = Integer.parseInt(lineSplit[j]);
      }
      allNum.add(in);
    }
    reader.close();
    return allNum;
  }

}
