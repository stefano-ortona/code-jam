package google.com.ortona.code_jam.y2017.round1A;

import java.util.Comparator;

public class DragonNodeComparator implements Comparator<DragonNode> {

  @Override
  public int compare(DragonNode o1, DragonNode o2) {

    if (o1.isBetterNode(o2)) {
      return -1;
    }
    if (o2.isBetterNode(o1)) {
      return 1;
    }

    final int first = (o1.aD + o1.aK) - o1.hD - o1.hK;
    final int second = (o2.aD + o2.aK) - o2.hD - o2.hK;
    if (first > second) {
      return -1;
    }
    if (second > first) {
      return 1;
    }
    if ((o1.aD == o2.aD) && (o1.aK == o2.aK) && (o1.hD == o2.hD) && (o1.aD == o2.aD)) {
      return 0;
    }
    return -1;
  }

}
