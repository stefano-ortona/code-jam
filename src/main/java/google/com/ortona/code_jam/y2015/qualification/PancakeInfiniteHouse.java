package google.com.ortona.code_jam.y2015.qualification;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class PancakeInfiniteHouse {

  public int computeMinimumTime(List<Integer> initialPlate) {
    int bestPosTime = Collections.max(initialPlate);
    for (int i = 1; i < bestPosTime; i++) {
      int curScore = i;
      final List<Integer> curInput = Lists.newArrayList(initialPlate);
      boolean needsMore = computeMinimumTime(curInput, i);
      boolean isElegible = true;
      while (needsMore) {
        curScore++;
        // check can do better
        if ((curBestPosTime(curInput, i) + curScore) >= bestPosTime) {
          isElegible = false;
          break;
        }
        needsMore = computeMinimumTime(curInput, i);
      }
      if (isElegible) {
        bestPosTime = Math.min(bestPosTime, curScore);
      }
    }
    return bestPosTime;
  }

  private long curBestPosTime(List<Integer> curList, int maxPancakePerPlate) {
    return curList.stream().filter(el -> el > maxPancakePerPlate).count();
  }

  public boolean computeMinimumTime(List<Integer> initialPlate, int maxPancakePerPlate) {
    if (initialPlate.isEmpty()) {
      return false;
    }
    final Integer maxCurPan = Collections.max(initialPlate);
    if (maxCurPan > maxPancakePerPlate) {
      initialPlate.remove(maxCurPan);
      final int newNum = Math.abs(maxPancakePerPlate - maxCurPan);
      if (newNum > maxPancakePerPlate) {
        initialPlate.add(newNum);
      }
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    final List<Integer> input = new LinkedList<Integer>();

    input.add(4);
    // input.add(5);
    // input.add(7);
    // input.add(8);

    final PancakeInfiniteHouse pH = new PancakeInfiniteHouse();
    System.out.println(pH.computeMinimumTime(input));
  }

}
