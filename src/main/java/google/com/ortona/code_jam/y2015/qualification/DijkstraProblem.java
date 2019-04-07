package google.com.ortona.code_jam.y2015.qualification;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public class DijkstraProblem {

  private static final Logger LOG = LoggerFactory.getLogger(DijkstraProblem.class);

  Map<String, Boolean> middleCache;

  int[][] quaternionMatrix = new int[4][4];

  public DijkstraProblem() {
    this.middleCache = Maps.newHashMap();

    quaternionMatrix[0][0] = 1;
    quaternionMatrix[0][1] = 2;
    quaternionMatrix[0][2] = 3;
    quaternionMatrix[0][3] = 4;

    quaternionMatrix[1][0] = 2;
    quaternionMatrix[1][1] = -1;
    quaternionMatrix[1][2] = 4;
    quaternionMatrix[1][3] = -3;

    quaternionMatrix[2][0] = 3;
    quaternionMatrix[2][1] = -4;
    quaternionMatrix[2][2] = -1;
    quaternionMatrix[2][3] = 2;

    quaternionMatrix[3][0] = 4;
    quaternionMatrix[3][1] = 3;
    quaternionMatrix[3][2] = -2;
    quaternionMatrix[3][3] = -1;
  }

  public boolean smart(String inputString, long repetition) {
    if (repetition <= 6) {
      return bruteForce(inputString, repetition);
    }

    for (int i = 1; i <= 6; i++) {
      if (invokeBruteForceParametrized(inputString, repetition, i)) {
        return true;
      }
    }
    return false;
  }

  private boolean invokeBruteForceParametrized(final String inputString, final long repetition, int curNum) {
    if ((curNum % 2) == (repetition % 2)) {
      return (((repetition - curNum) % 4) == 0) && bruteForce(inputString, curNum);
    }
    return false;
  }

  public boolean bruteForce(String input, long repetition) {
    final StringBuilder sB = new StringBuilder();
    for (int i = 0; i < repetition; i++) {
      sB.append(input);
    }
    final String inputString = sB.toString();
    if (inputString.length() < 3) {
      return false;
    }
    final boolean[] left = computeValueForLeft(inputString, 0, inputString.length() - 3, 2);
    final boolean[] right = computeValueForRight(inputString);
    // check middle position
    for (int start = 1; start <= (inputString.length() - 2); start++) {
      if (left[start - 1] == true) {
        int curRow = convertChar(inputString.charAt(start));
        if (curRow == 3) {
          if (right[start + 1]) {
            // LOG.info("Found a possible result with first end:'{}', second end:'{}' (both included).", start - 1,
            // start);
            return true;
          }
        }
        for (int end = start + 1; end <= (inputString.length() - 2); end++) {
          final int curColumn = convertChar(inputString.charAt(end));
          curRow = quaternionMultiply(curRow, curColumn);
          if (curRow == 3) {
            if (right[end + 1]) {
              // LOG.info("Found a possible result with first end:'{}', second end:'{}' (both included).", start - 1,
              // end);
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private boolean[] computeValueForLeft(String inputString, int start, int end, int value) {
    final boolean[] out = new boolean[inputString.length()];
    int curRow = convertChar(inputString.charAt(0));
    if (curRow == value) {
      out[0] = true;
    }
    for (int i = start + 1; i <= end; i++) {
      final int curColumn = convertChar(inputString.charAt(i));
      curRow = quaternionMultiply(curRow, curColumn);
      if (curRow == value) {
        out[i] = true;
      }
    }
    return out;
  }

  private boolean[] computeValueForRight(String inputString) {
    final boolean out[] = new boolean[inputString.length()];
    int curColumn = convertChar(inputString.charAt(inputString.length() - 1));
    if (curColumn == 4) {
      out[inputString.length() - 1] = true;
    }
    for (int i = inputString.length() - 2; i >= 2; i--) {
      final int curRow = convertChar(inputString.charAt(i));
      curColumn = quaternionMultiply(curRow, curColumn);
      if (curColumn == 4) {
        out[i] = true;
      }
    }
    return out;
  }

  private int quaternionMultiply(int row, int column) {
    int mutliplySign = -1;
    if (((row > 0) && (column > 0)) || ((row < 0) && (column < 0))) {
      mutliplySign = 1;
    }
    return mutliplySign * quaternionMatrix[convertCharMatrix(row)][convertCharMatrix(column)];
  }

  public int convertChar(char inputChar) {
    switch (inputChar) {
    case '1':
      return 1;
    case 'i':
      return 2;
    case 'j':
      return 3;
    case 'k':
      return 4;
    }
    throw new RuntimeException("Impossible!");
  }

  private int convertCharMatrix(int input) {
    return Math.abs(input) - 1;
  }

  public static void main(String[] args) {
    final String input = "kikkiij";
    final int repetition = 1323;
    final DijkstraProblem dP = new DijkstraProblem();
    System.out.println(dP.smart(input, repetition));
    // System.out.println(dP.bruteForce(input, repetition));

  }

}
