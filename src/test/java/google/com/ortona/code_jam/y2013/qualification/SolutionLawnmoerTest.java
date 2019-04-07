package google.com.ortona.code_jam.y2013.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class SolutionLawnmoerTest {
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
    final List<int[][]> allGrids = this.readFile(file);
    final Solution_B sol = new Solution_B();
    int count = 1;
    for (final int[][] oneGrid : allGrids) {
      final boolean curSol = sol.lawnmowerSuccess(oneGrid);
      final String out = curSol ? "YES" : "NO";
      System.out.println("Case #" + count + ": " + out);
      count++;
    }
  }

  private List<int[][]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<int[][]> allGrids = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final int first = Integer.parseInt(line.split(" ")[0]);
      final int second = Integer.parseInt(line.split(" ")[1]);
      final int[][] grid = new int[first][second];
      readGrid(reader, grid);
      allGrids.add(grid);
    }
    reader.close();
    return allGrids;
  }

  final void readGrid(BufferedReader reader, int[][] grid) throws IOException {
    for (int i = 0; i < grid.length; i++) {
      final String[] line = reader.readLine().split(" ");
      for (int j = 0; j < line.length; j++) {
        grid[i][j] = Integer.parseInt(line[j]);
      }
    }
  }

}
