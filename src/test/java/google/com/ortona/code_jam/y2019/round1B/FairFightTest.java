package google.com.ortona.code_jam.y2019.round1B;

import org.junit.Assert;
import org.junit.Test;

public class FairFightTest {
  private final FairFight solution = new FairFight();

  @Test
  public void test1() {
    final int sol = solution.numberFairFight(new int[] { 1, 1, 1, 8 }, new int[] { 8, 8, 8, 8 }, 0);
    Assert.assertEquals(4, sol);
  }

  @Test
  public void test2() {
    final int sol = solution.numberFairFight(new int[] { 0, 1, 1 }, new int[] { 1, 1, 0 }, 0);
    Assert.assertEquals(4, sol);
  }

  @Test
  public void test3() {
    final int sol = solution.numberFairFight(new int[] { 3 }, new int[] { 3 }, 0);
    Assert.assertEquals(1, sol);
  }

  @Test
  public void test4() {
    final int sol = solution.numberFairFight(new int[] { 0, 8, 0, 8, 0 }, new int[] { 4, 0, 4, 0, 4 }, 0);
    Assert.assertEquals(0, sol);
  }

  @Test
  public void test5() {
    final int sol = solution.numberFairFight(new int[] { 1, 0, 0 }, new int[] { 0, 1, 2 }, 0);
    Assert.assertEquals(1, sol);
  }

  @Test
  public void test6() {
    final int sol = solution.numberFairFight(new int[] { 1, 2, 3, 4, 5 }, new int[] { 5, 5, 5, 5, 10 }, 2);
    Assert.assertEquals(7, sol);
  }

}
