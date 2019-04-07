package google.com.ortona.code_jam.y2018.test_round;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Sets;

public class Solution_Senate_Evacuation {

  public String evacuateSenators(StringBuilder curSolution, TreeSet<Party> curParties) {
    final int curSize = getTotSenators(curParties);
    if (wrongSolution(curParties, curSize)) {
      throw new RuntimeException("Sould never happen!");
    }
    final Party first = curParties.pollFirst();
    curSolution.append(first.c);
    first.curSize--;
    if (first.curSize > 0) {
      curParties.add(first);
    }
    if (curSize != 3) {
      final Party second = curParties.pollFirst();
      second.curSize--;
      curSolution.append(second.c);
      if (second.curSize > 0) {
        curParties.add(second);
      }
    }
    if (curParties.size() > 0) {
      curSolution.append(" ");
    } else {
      return curSolution.toString();
    }
    return evacuateSenators(curSolution, curParties);
  }

  private int getTotSenators(Collection<Party> allParties) {
    final AtomicInteger count = new AtomicInteger(0);
    allParties.forEach(p -> count.addAndGet(p.curSize));
    return count.get();
  }

  private boolean wrongSolution(TreeSet<Party> curParties, int curSize) {
    final int majority = curSize / 2;
    return curParties.stream().filter(p -> p.curSize > majority).findFirst().isPresent();
  }

  class Party implements Comparable<Party> {
    char c;
    int curSize;

    public Party(char c, int size) {
      this.c = c;
      this.curSize = size;

    }

    @Override
    public int compareTo(Party o) {
      if (o.curSize != this.curSize) {
        return o.curSize - this.curSize;
      }
      return this.c - o.c;
    }

    @Override
    public String toString() {
      return c + "->" + curSize;
    }
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final Solution_Senate_Evacuation sE = new Solution_Senate_Evacuation();
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      final TreeSet<Party> allParties = Sets.newTreeSet();
      // read number of parties
      final int numParties = in.nextInt();
      char curChar = 'A';
      for (int p = 1; p <= numParties; p++) {
        final int curSize = in.nextInt();
        allParties.add(sE.new Party(curChar++, curSize));
      }
      final String curSol = sE.evacuateSenators(new StringBuilder(), allParties);
      System.out.println("Case #" + i + ": " + curSol);
    }
    in.close();
  }

}
