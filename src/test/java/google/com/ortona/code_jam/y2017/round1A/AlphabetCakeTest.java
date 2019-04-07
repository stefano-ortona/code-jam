package google.com.ortona.code_jam.y2017.round1A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class AlphabetCakeTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "A-small-practice.in";
    assignCakePiece(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "A-large-practice.in";
    assignCakePiece(file);
  }

  public void assignCakePiece(final String file) throws NumberFormatException, IOException {
    final List<char[][]> allGrids = this.readFile(file);
    final AlphabetCake aC = new AlphabetCake();
    int count = 1;
    for (final char[][] oneGrid : allGrids) {
      final char[][] out = aC.assignCakePiece(oneGrid);
      System.out.println("Case #" + count + ":");
      for (int i = 0; i < out.length; i++) {
        System.out.println(out[i]);
      }
      count++;
    }
  }

  private List<char[][]> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<char[][]> allGrids = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final int row = Integer.parseInt(line.split(" ")[0]);
      final int column = Integer.parseInt(line.split(" ")[1]);
      final char[][] grid = new char[row][column];
      readGrid(reader, row, grid);
      allGrids.add(grid);
    }
    reader.close();
    return allGrids;
  }

  final void readGrid(BufferedReader reader, int lines, char[][] grid) throws IOException {
    for (int i = 0; i < lines; i++) {
      final String line = reader.readLine();
      for (int j = 0; j < line.length(); j++) {
        grid[i][j] = line.charAt(j);
      }
    }
  }

}
