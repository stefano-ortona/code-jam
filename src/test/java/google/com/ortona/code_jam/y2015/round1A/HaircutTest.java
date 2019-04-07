package google.com.ortona.code_jam.y2015.round1A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import google.com.ortona.code_jam.kickstart.y2015.round1A.Haircut;

public class HaircutTest {

  @Test
  public void testExample() throws NumberFormatException, IOException {
    final String file = "b-example.in";
    executeTest(file);
  }

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
    final List<ProblemContainer> allProbs = this.readFile(file);
    int count = 1;
    final Haircut haircut = new Haircut();
    for (final ProblemContainer oneProbl : allProbs) {
      final int solution = haircut.getWaitingTime(oneProbl.posInLine, oneProbl.servingTime);
      System.out.println("Case #" + count + ": " + solution);
      count++;
    }
  }

  private List<ProblemContainer> readFile(final String file) throws NumberFormatException, IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(file)));
    final int lineNum = Integer.parseInt(reader.readLine());
    final List<ProblemContainer> allProblems = new ArrayList<>();
    for (int i = 0; i < lineNum; i++) {
      final int numInLine = Integer.parseInt(reader.readLine().split(" ")[1]);
      final String[] servTimeSplit = reader.readLine().split(" ");
      final int[] servingTimes = new int[servTimeSplit.length];
      for (int j = 0; j < servingTimes.length; j++) {
        servingTimes[j] = Integer.parseInt(servTimeSplit[j]);
      }
      final ProblemContainer pC = new ProblemContainer();
      pC.servingTime = servingTimes;
      pC.posInLine = numInLine;
      allProblems.add(pC);
    }
    reader.close();
    return allProblems;
  }

  private class ProblemContainer {
    int[] servingTime;
    int posInLine;
  }

}
