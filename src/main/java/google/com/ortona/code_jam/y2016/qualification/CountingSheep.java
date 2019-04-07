package google.com.ortona.code_jam.y2016.qualification;

import java.util.Set;

import com.google.common.collect.Sets;

public class CountingSheep {

  public long countSheep(long initial) {
    if (initial == 0) {
      return -1;
    }
    final Set<Integer> digitSeen = Sets.newHashSet();
    long curNum = initial;
    addDigit(curNum, digitSeen);
    int i = 2;
    while (digitSeen.size() < 10) {
      curNum = initial * i;
      i++;
      addDigit(curNum, digitSeen);
    }
    return curNum;

  }

  private void addDigit(long number, Set<Integer> curDigit) {
    final String numString = number + "";
    for (int i = 0; i < numString.length(); i++) {
      curDigit.add(Integer.parseInt(numString.charAt(i) + ""));
    }
  }

  public static void main(String[] args) {
    final CountingSheep cS = new CountingSheep();
    for (int i = 0; i < 1000000; i++) {
      System.out.println(i + "--->" + cS.countSheep(i));
    }
    System.out.println(cS.countSheep(1000000));
  }

}
