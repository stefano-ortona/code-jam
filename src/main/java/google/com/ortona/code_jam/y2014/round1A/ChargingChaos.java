package google.com.ortona.code_jam.y2014.round1A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChargingChaos {

  public int minNumSwitches(String[] flow, String[] devices) {
    final Map<Set<Integer>, List<int[]>> switchToChangeToPair = new HashMap<>();
    for (int i = 0; i < flow.length; i++) {
      for (int j = 0; j < devices.length; j++) {
        final Set<Integer> key = getSwitchToChange(flow[i], devices[j]);
        final List<int[]> covered = switchToChangeToPair.getOrDefault(key, new LinkedList<>());
        covered.add(new int[] { i, j });
        switchToChangeToPair.put(key, covered);
      }
    }
    // get minimun set of changes
    int curMin = flow.length + 1;
    for (final Set<Integer> oneSwitch : switchToChangeToPair.keySet()) {
      if (oneSwitch.size() < curMin) {
        final List<int[]> coveredPairs = switchToChangeToPair.get(oneSwitch);
        if ((coveredPairs.size() >= flow.length)
            && canCover(coveredPairs, 0, new HashSet<>(), new HashSet<>(), flow.length)) {
          curMin = oneSwitch.size();
        }
      }
    }
    return curMin == (flow.length + 1) ? -1 : curMin;
  }

  private boolean canCover(List<int[]> pairs, int curIndex, Set<Integer> first, Set<Integer> second, int target) {
    if ((first.size() == target) && (second.size() == target)) {
      return true;
    }
    if (curIndex >= pairs.size()) {
      return false;
    }
    boolean canCover = false;
    final int[] curPair = pairs.get(curIndex);
    if (!first.contains(curPair[0]) && !second.contains(curPair[1])) {
      first.add(curPair[0]);
      second.add(curPair[1]);
      canCover = canCover(pairs, curIndex + 1, first, second, target);
      // backtrack
      first.remove(curPair[0]);
      second.remove(curPair[1]);
    }
    return canCover ? canCover : canCover(pairs, curIndex + 1, first, second, target);
  }

  private Set<Integer> getSwitchToChange(String flow, String device) {
    final Set<Integer> toFlip = new HashSet<>();
    for (int i = 0; i < flow.length(); i++) {
      if (flow.charAt(i) != device.charAt(i)) {
        toFlip.add(i);
      }
    }
    return toFlip;
  }

}
