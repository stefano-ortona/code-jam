package google.com.ortona.code_jam.y2019.round1A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlienRhyme {

  public int maxRhyme(List<String> words) {
    words = buildEqualLengthString(words);
    final Map<Character, List<String>> grouping = groupByChar(words, words.get(0).length() - 1);
    int totRhymes = 0;
    for (final List<String> values : grouping.values()) {
      if (values.size() > 1) {
        totRhymes += findRhymeNextCharacter(values, words.get(0).length() - 1, false);
      }
    }
    return totRhymes;
  }

  private List<String> buildEqualLengthString(List<String> words) {
    int maxLength = words.get(0).length();
    for (final String oneW : words) {
      maxLength = Math.max(maxLength, oneW.length());
    }
    final List<String> newLengthString = new ArrayList<>();
    final StringBuilder prefix = new StringBuilder();
    for (int i = 0; i < maxLength; i++) {
      prefix.append("$");
    }

    for (final String oneW : words) {
      if (oneW.length() < maxLength) {
        newLengthString.add(prefix.substring(0, maxLength - oneW.length()) + oneW);
      } else {
        newLengthString.add(oneW);
      }
    }
    return newLengthString;
  }

  public int findRhymeNextCharacter(List<String> words, int index, boolean spare) {
    if ((words.size() == 2) || (index < 0)) {
      return 2;
    }
    final Map<Character, List<String>> grouping = groupByChar(words, index);
    int totRhymes = 0;
    // count spare words
    final long spareWords = grouping.keySet().stream().filter(c -> (c == '$') || (grouping.get(c).size() == 1)).count();
    if ((spareWords >= 2) || ((spareWords == 1) && spare)) {
      totRhymes += 2;
      spare = false;
    } else if (spareWords == 1) {
      spare = true;
    }
    for (final char oneC : grouping.keySet()) {
      if (oneC != '$') {
        final List<String> curWords = grouping.get(oneC);
        if (curWords.size() >= 2) {
          totRhymes += findRhymeNextCharacter(curWords, index - 1, spare);
        }
      }
    }
    return totRhymes;
  }

  private Map<Character, List<String>> groupByChar(List<String> words, int index) {
    final Map<Character, List<String>> grouping = new HashMap<Character, List<String>>();
    for (final String w : words) {
      final char curChar = w.charAt(index);
      final List<String> curStrings = grouping.getOrDefault(curChar, new LinkedList<>());
      curStrings.add(w);
      grouping.put(curChar, curStrings);
    }
    return grouping;
  }

  public static void main(String[] args) {
    final AlienRhyme aR = new AlienRhyme();
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      final int nWords = in.nextInt();
      in.nextLine();
      final List<String> words = new ArrayList<>();
      for (int j = 0; j < nWords; j++) {
        words.add(in.nextLine());

      }
      System.out.println("Case #" + i + ": " + aR.maxRhyme(words));
    }
    in.close();
  }

}
