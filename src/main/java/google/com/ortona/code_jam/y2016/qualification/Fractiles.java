package google.com.ortona.code_jam.y2016.qualification;

import java.util.List;

import com.google.common.collect.Lists;

public class Fractiles {

  public List<Long> revealFractileSimple(long k, long c) {
    final List<Long> toClean = Lists.newArrayList();
    for (int i = 0; i < k; i++) {
      toClean.add((i * ((Double) Math.pow(k, c - 1)).longValue()) + 1);
    }
    return toClean;
  }

  public static void main(String[] args) {
    final Fractiles frac = new Fractiles();
    System.out.println(frac.revealFractileSimple(3, 2));
  }

}
