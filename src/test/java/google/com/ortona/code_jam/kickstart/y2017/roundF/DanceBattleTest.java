package google.com.ortona.code_jam.kickstart.y2017.roundF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class DanceBattleTest {
  @Test
  public void testSmall() throws NumberFormatException, IOException {
    final String file = "B-small-practice.in";
    assignCakePiece(file);
  }

  @Test
  public void testBig() throws NumberFormatException, IOException {
    final String file = "B-large-practice.in";
    assignCakePiece(file);
  }

  public void assignCakePiece(final String file) throws NumberFormatException, IOException {
    final List<DanceBattleInput> allInputs = this.readFile(file);
    final DanceBattle dB = new DanceBattle();
    int count = 1;
    for (final DanceBattleInput oneInput : allInputs) {
      final int out = dB.solve(oneInput.energy, oneInput.opponents);
      System.out.println("Case #" + count++ + ": " + out);
    }
  }

  private List<DanceBattleInput> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final List<DanceBattleInput> allInputs = Lists.newArrayList();
    final int lineNum = Integer.parseInt(reader.readLine());
    for (int i = 0; i < lineNum; i++) {
      final String line = reader.readLine();
      final int energy = Integer.parseInt(line.split(" ")[0]);
      final List<Integer> opponents = readOpponents(reader.readLine());
      final DanceBattleInput input = new DanceBattleInput();
      input.opponents = opponents;
      input.energy = energy;
      allInputs.add(input);
    }
    reader.close();
    return allInputs;
  }

  private List<Integer> readOpponents(String line) throws IOException {
    final String[] parts = line.split(" ");
    final List<Integer> out = Lists.newArrayList();
    for (final String oneNumber : parts) {
      out.add(Integer.parseInt(oneNumber));
    }
    return out;
  }

}
