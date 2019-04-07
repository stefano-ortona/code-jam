package google.com.ortona.code_jam.y2017.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class BathroomStallAssignment {

  public Pair<Long, Long> assignStall(long stall, long people) {
    final TreeSet<Stall> availableStalls = new TreeSet<Stall>(new StallComparator());
    addStall(new Stall(stall + 1, stall, 0), true, availableStalls);
    Stall nextStall = null;
    for (int i = 0; i < people; i++) {
      nextStall = availableStalls.pollFirst();
      addStall(nextStall, true, availableStalls);
      addStall(nextStall, false, availableStalls);
    }
    final Pair<Long, Long> p = new Pair<Long, Long>();
    p.x = Math.max(nextStall.leftEmtpy, nextStall.rigthEmtpy);
    p.y = Math.min(nextStall.leftEmtpy, nextStall.rigthEmtpy);
    return p;

  }

  private void addStall(Stall previousStall, boolean left, TreeSet<Stall> availableStalls) {
    if ((left && (previousStall.leftEmtpy == 0)) || (!left && (previousStall.rigthEmtpy == 0))) {
      return;
    }
    final long length = left ? previousStall.leftEmtpy : previousStall.rigthEmtpy;
    long leftLenght = length / 2;
    final long rightLenght = length / 2;
    long stallId = left ? previousStall.id - (length / 2) - 1 : previousStall.id + (length / 2);
    if ((length % 2) == 0) {
      leftLenght--;
    } else {
      if (!left) {
        stallId++;
      }
    }
    availableStalls.add(new Stall(stallId, leftLenght, rightLenght));
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final BathroomStallAssignment bA = new BathroomStallAssignment();
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      final long n = in.nextLong();
      final long m = in.nextLong();
      final Pair<Long, Long> out = bA.assignStall(n, m);
      System.out.println("Case #" + i + ": " + out.x + " " + out.y);
    }
    in.close();
  }

  class Pair<T, K> {
    public T x;
    public K y;
  }

  class Stall {
    long id;

    public long leftEmtpy;
    public long rigthEmtpy;

    public Stall(long id, long leftEmpty, long rightEmtpy) {
      this.id = id;
      this.leftEmtpy = leftEmpty;
      this.rigthEmtpy = rightEmtpy;
    }

    @Override
    public String toString() {
      return id + "_(" + leftEmtpy + "," + rigthEmtpy + ")";
    }

  }

  class StallComparator implements Comparator<Stall> {

    @Override
    public int compare(Stall o1, Stall o2) {
      final long firstFurhtestMin = Math.min(o1.leftEmtpy, o1.rigthEmtpy);
      final long secondFurthestMin = Math.min(o2.leftEmtpy, o2.rigthEmtpy);
      if (firstFurhtestMin != secondFurthestMin) {
        final long diff = secondFurthestMin - firstFurhtestMin;
        return diff < 0 ? -1 : 1;
      }
      final long firstFurhtestMax = Math.max(o1.leftEmtpy, o1.rigthEmtpy);
      final long secondFurthestMax = Math.max(o2.leftEmtpy, o2.rigthEmtpy);
      if (firstFurhtestMax != secondFurthestMax) {
        final long diff = secondFurthestMax - firstFurhtestMax;
        return diff < 0 ? -1 : 1;
      }
      final long diff = o1.id - o2.id;
      return diff < 0 ? -1 : 1;
    }

  }

}
