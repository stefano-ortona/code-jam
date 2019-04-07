package google.com.ortona.code_jam.y2017.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ModelAssignmentTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "D-small-practice.in";
    assignModelTest(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "C-large-practice.in";
    assignModelTest(file);
  }

  public void assignModelTest(final String file) throws NumberFormatException, IOException {
    final List<char[][]> allGrids = this.readFile(file);
    final ModelAssignmentSmart ma = new ModelAssignmentSmart();
    int count = 1;
    for (final char[][] oneGrid : allGrids) {
      System.out.println("Solving with grid size: " + oneGrid.length);
      final String out = ma.assignModel(oneGrid);
      System.out.println("Case #" + count + ":\n" + out);
      count++;
    }
  }

  private List<char[][]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<char[][]> allGrids = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final int first = Integer.parseInt(line.split(" ")[0]);
      final int second = Integer.parseInt(line.split(" ")[1]);
      final char[][] grid = new char[first][first];
      for (int j = 0; j < first; j++) {
        Arrays.fill(grid[j], '.');
      }
      readGrid(reader, second, grid);
      allGrids.add(grid);
    }
    reader.close();
    return allGrids;
  }

  final void readGrid(BufferedReader reader, int lines, char[][] grid) throws IOException {
    for (int i = 0; i < lines; i++) {
      final String line = reader.readLine();
      final String[] split = line.split(" ");
      final int row = Integer.parseInt(split[1]) - 1;
      final int column = Integer.parseInt(split[2]) - 1;
      final char symbol = split[0].charAt(0);
      grid[row][column] = symbol;
    }
  }

}
