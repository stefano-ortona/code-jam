package google.com.ortona.code_jam.y2019.round1A;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class AlienRhymeTest {
  private final AlienRhyme sol = new AlienRhyme();

  @Test
  public void test1() {
    Assert.assertEquals(2, sol.maxRhyme(Arrays.asList("PROL", "MOL")));
  }

  @Test
  public void test2() {
    Assert.assertEquals(6, sol.maxRhyme(Arrays.asList("EFROG", "FROG", "CROLOG", "COG", "CIOG", "ARCIOG")));
  }

  @Test
  public void test3() {
    Assert.assertEquals(12, sol.maxRhyme(Arrays.asList("EFROG", "FROG", "CROLOG", "COG", "CIOG", "ARCIOG", "EFROK",
        "FROK", "CROLOK", "COK", "CIOK", "ARCIOK")));
  }

  @Test
  public void test4() {
    Assert.assertEquals(6,
        sol.maxRhyme(Arrays.asList("M", "AAAM", "AAAAAM", "AAAAAAAAAM", "AAAAAAAAAAM", "AAAAAAAAAAAAAAAAAM")));
  }

}
