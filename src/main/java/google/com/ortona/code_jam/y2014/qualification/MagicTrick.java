package google.com.ortona.code_jam.y2014.qualification;

import java.util.Set;

import com.google.common.collect.Sets;

public class MagicTrick {

  public String getGuessedCard(int[][] firstBoard, int[][] secondBoard, int firstGuess, int secondGuess) {

    final Set<Integer> firstNum = Sets.newHashSet();
    for (int i = 0; i < firstBoard[firstGuess].length; i++) {
      firstNum.add(firstBoard[firstGuess][i]);
    }

    Integer guessedNumber = null;
    for (int i = 0; i < secondBoard[secondGuess].length; i++) {
      final int curNum = secondBoard[secondGuess][i];
      if (firstNum.contains(curNum)) {
        if (guessedNumber != null) {
          return "Bad magician!";
        }
        guessedNumber = curNum;
      }
    }
    return guessedNumber == null ? "Volunteer cheated!" : guessedNumber + "";
  }

}
