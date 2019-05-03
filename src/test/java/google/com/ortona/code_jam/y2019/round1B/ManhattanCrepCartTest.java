package google.com.ortona.code_jam.y2019.round1B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class ManhattanCrepCartTest {

  private final Random r = new Random();
  private final String[] directions = new String[] { "N", "S", "W", "E" };

  private final ManhattanCrepCart solution = new ManhattanCrepCart();

  @Test
  public void test1() {
    final List<String> coordinates = Arrays.asList("5 5 N");
    final String sol = solution.mostPopularRectangle(coordinates, 10);
    Assert.assertEquals("0 6", sol);
  }

  @Test
  public void test2() {
    final List<String> coordinates = Arrays.asList("2 4 N", "2 6 S", "1 5 E", "3 5 W");
    final String sol = solution.mostPopularRectangle(coordinates, 10);
    Assert.assertEquals("2 5", sol);
  }

  @Test
  public void test3() {
    final List<String> coordinates = Arrays.asList("0 2 S", "0 3 N", "0 3 N", "0 4 N", "0 5 S", "0 5 S", "0 8 S",
        "1 5 W");
    final String sol = solution.mostPopularRectangle(coordinates, 10);
    Assert.assertEquals("0 4", sol);
  }

  @Test
  public void test4() {
    final List<String> coordinates = Arrays.asList("4 0 E", "6 3 W", "0 4 N", "0 6 S");
    final String sol = solution.mostPopularRectangle(coordinates, 10);
    Assert.assertEquals("5 5", sol);
  }

  @Test
  public void test5() {
    final List<String> coordinates = Arrays.asList("4 0 E", "5 0 E", "6 0 E", "8 0 W", "5 0 W", "4 0 W");
    final String sol = solution.mostPopularRectangle(coordinates, 10);
    Assert.assertEquals("7 0", sol);
  }

  @Test
  public void testBig() {
    final List<String> coordinates = new ArrayList<>();
    final int max = 100000;
    for (int i = 0; i < 500; i++) {
      coordinates.add(getRandomCoordinate(max));
    }
    final String sol = solution.mostPopularRectangle(coordinates, max);
    System.out.println(sol);
  }

  private String getRandomCoordinate(int max) {
    final int first = r.nextInt(max + 1);
    final int second = r.nextInt(max + 1);
    return first + " " + second + " " + directions[r.nextInt(4)];
  }

}
