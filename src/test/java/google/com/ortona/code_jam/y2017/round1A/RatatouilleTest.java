package google.com.ortona.code_jam.y2017.round1A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RatatouilleTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "B-small-practice.in";
    assignCakePiece(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "A-large-practice.in";
    assignCakePiece(file);
  }

  public void assignCakePiece(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final int testCases = Integer.parseInt(reader.readLine());
    final Ratatouille r = new Ratatouille();
    for (int i = 1; i <= testCases; i++) {
      final int ingrNumber = Integer.parseInt(reader.readLine().split(" ")[0]);
      final int out = r.maximumNumberPackage(readFile(ingrNumber, reader));
      System.out.println("Case #" + i + ": " + out);
    }
    reader.close();
  }

  private RatatouilleStatus readFile(int ingrNumber, BufferedReader reader) throws NumberFormatException, IOException {
    String[] lineSplit = reader.readLine().split(" ");
    final Map<Integer, Integer> quantityNeeded = Maps.newHashMap();
    for (int i = 0; i < lineSplit.length; i++) {
      quantityNeeded.put(i, Integer.parseInt(lineSplit[i]));
    }

    final List<Package> availPackage = Lists.newArrayList();
    for (int i = 0; i < ingrNumber; i++) {
      lineSplit = reader.readLine().split(" ");
      for (int j = 0; j < lineSplit.length; j++) {
        availPackage.add(new Package((i * lineSplit.length) + j, i, Integer.parseInt(lineSplit[j])));
      }
    }
    final RatatouilleStatus s = new RatatouilleStatus(availPackage, quantityNeeded);
    return s;
  }

}
