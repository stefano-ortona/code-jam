package google.com.ortona.code_jam.y2014.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class MagicTrickTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "A-small-practice.in";
    executeTest(file);
  }

  private void executeTest(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final int lineNum = Integer.parseInt(reader.readLine());
    int count = 1;
    final MagicTrick mT = new MagicTrick();
    for (int i = 0; i < lineNum; i++) {
      final int firstGuess = Integer.parseInt(reader.readLine());
      final int[][] firstGrid = readGrid(reader);
      final int secondGuess = Integer.parseInt(reader.readLine());
      final int[][] secondGrid = readGrid(reader);
      final String out = mT.getGuessedCard(firstGrid, secondGrid, firstGuess - 1, secondGuess - 1);
      System.out.println("Case #" + count + ": " + out);
      count++;
    }
    reader.close();
  }

  final int[][] readGrid(BufferedReader reader) throws IOException {
    final int[][] grid = new int[4][4];
    for (int i = 0; i < 4; i++) {
      final String line = reader.readLine();
      final String[] split = line.split(" ");
      for (int j = 0; j < split.length; j++) {
        grid[i][j] = Integer.parseInt(split[j]);
      }
    }
    return grid;
  }

}
