package google.com.ortona.code_jam.kickstart.y2015.round1A;

import org.apache.commons.lang3.tuple.Pair;

public class MushroomMonster {

  public Pair<Integer, Integer> getMinMushrooms(int[] intervals) {
    return Pair.of(getAnyNumberEaten(intervals), getFixedRateEater(intervals));

  }

  public int getAnyNumberEaten(int[] intervals) {
    int mushEaten = 0;
    for (int i = 0; i < (intervals.length - 1); i++) {
      if (intervals[i] > intervals[i + 1]) {
        mushEaten += intervals[i] - intervals[i + 1];
      }
    }
    return mushEaten;
  }

  public int getFixedRateEater(int[] intervals) {
    int maxGap = 0;
    for (int i = 0; i < (intervals.length - 1); i++) {
      if (intervals[i] > intervals[i + 1]) {
        maxGap = Math.max(maxGap, intervals[i] - intervals[i + 1]);
      }
    }
    // count how many eaten
    int totEaten = 0;
    for (int i = 0; i < (intervals.length - 1); i++) {
      totEaten += Math.min(maxGap, intervals[i]);
    }
    return totEaten;
  }

}
