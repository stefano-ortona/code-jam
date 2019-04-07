package google.com.ortona.code_jam.y2017.round1A;

import java.util.Set;

import com.google.common.collect.Sets;

public class AlphabetCake {

  public char[][] assignCakePiece(char[][] input) {
    final Set<Character> seenChars = Sets.newHashSet();
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[0].length; j++) {
        final char curChar = input[i][j];
        if ((curChar != '?') && !seenChars.contains(curChar)) {
          final CakePiece curPiece = new CakePiece(i, j);
          curPiece.expand(curChar, input);
          seenChars.add(curChar);
        }
      }
    }
    if (!isAllCovered(input)) {
      throw new RuntimeException("It is not all covered!");
    }
    return input;
  }

  private boolean isAllCovered(char[][] status) {
    for (int i = 0; i < status.length; i++) {
      for (int j = 0; j < status[0].length; j++) {
        if (status[i][j] == '?') {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    char[][] inp = new char[2][2];
    inp[0] = new char[] { 'C', 'A' };
    inp[1] = new char[] { 'K', 'E' };

    final AlphabetCake aC = new AlphabetCake();
    inp = aC.assignCakePiece(inp);
    for (int i = 0; i < inp.length; i++) {
      System.out.println(inp[i]);
    }
  }

}
