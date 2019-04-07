package google.com.ortona.code_jam.y2015.qualification;

import java.util.Map;

import com.google.common.collect.Maps;

public class StandingOvation {

  public int getMinimunNumberAdditionaPeople(String input) {
    final Map<Integer, Integer> shylevel2quantity = buildInitialMap(input);
    int additionalPeople = 0;
    int curAudienceStoodUp = shylevel2quantity.get(0);
    for (int i = 1; i < input.length(); i++) {
      if (curAudienceStoodUp < i) {
        additionalPeople += i - curAudienceStoodUp;
        curAudienceStoodUp += i - curAudienceStoodUp;
      }
      curAudienceStoodUp += shylevel2quantity.get(i);
    }
    return additionalPeople;
  }

  private Map<Integer, Integer> buildInitialMap(final String input) {
    final Map<Integer, Integer> shylevel2quantity = Maps.newHashMap();
    for (int i = 0; i < input.length(); i++) {
      final int quantity = Integer.parseInt(input.charAt(i) + "");
      shylevel2quantity.put(i, quantity);
    }
    return shylevel2quantity;
  }

  public static void main(String[] args) {
    final int maxShy = 0;
    final String input = "1";
    final StandingOvation sO = new StandingOvation();
    System.out.println(sO.getMinimunNumberAdditionaPeople(input));

  }

}
