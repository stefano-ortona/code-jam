package google.com.ortona.code_jam.y2017.qualification;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ModelAssignmentBruteForce {

  private final AtomicInteger score = new AtomicInteger(-1);
  String bestSequence;

  public String assignModel(char[][] grid) {
    assignModel(grid, 0, 0, "");
    System.out.println("Final score: " + score);
    return bestSequence;
  }

  public void assignModel(char[][] grid, int row, int column, String bestSequence) {
    if (column >= grid[0].length) {
      row++;
      column = 0;
    }
    if (row >= grid.length) {
      // finish, compute score
      final int curScore = computeScore(grid, null, null);
      if (curScore > score.get()) {
        System.out.println("Found a better score: " + curScore);
        score.set(curScore);
        this.bestSequence = bestSequence;
      }
      return;
    }

    final char previousSign = grid[row][column];

    // leave it as it is
    if (!illegalRow(grid, row, column) && !illegalColumnn(grid, row, column) && !illegalDiagonal(grid, row, column)) {
      assignModel(grid, row, column + 1, bestSequence);
    }

    if (grid[row][column] == '.') {
      // assign x and +
      grid[row][column] = 'x';
      if (!illegalRow(grid, row, column) && !illegalColumnn(grid, row, column) && !illegalDiagonal(grid, row, column)) {
        final String newSeq = bestSequence + row + "," + column + "=x---";
        assignModel(grid, row, column + 1, newSeq);
      }
      grid[row][column] = '+';
      if (!illegalRow(grid, row, column) && !illegalColumnn(grid, row, column) && !illegalDiagonal(grid, row, column)) {
        final String newSeq = bestSequence + row + "," + column + "=+---";
        assignModel(grid, row, column + 1, newSeq);
      }
    }

    if (previousSign != 'o') {
      final String newSeq = bestSequence + row + "," + column + "=o---";
      // can always assing a o
      grid[row][column] = 'o';
      if (!illegalRow(grid, row, column) && !illegalColumnn(grid, row, column) && !illegalDiagonal(grid, row, column)) {
        assignModel(grid, row, column + 1, newSeq);
      }
    }
    // backtrack
    grid[row][column] = previousSign;
  }

  protected boolean isColumnRowAccessible(char[][] grid, int column, int row) {
    for (int i = 0; i < grid[0].length; i++) {
      if ((grid[row][i] == 'x') || (grid[row][i] == 'o')) {
        return false;
      }
    }
    for (int i = 0; i < grid.length; i++) {
      if ((grid[i][column] == 'x') || (grid[i][column] == 'o')) {
        return false;
      }
    }
    return true;
  }

  protected boolean isDiagonalAccessible(char[][] grid, int column, int row) {
    int i = row;
    int j = column;
    while ((i >= 0) && (j >= 0)) {
      if ((grid[i][j] == '+') || (grid[i][j] == 'o')) {
        return false;
      }
      i--;
      j--;
    }
    i = row + 1;
    j = column + 1;
    while ((i < grid.length) && (j < grid[0].length)) {
      if ((grid[i][j] == '+') || (grid[i][j] == 'o')) {
        return false;
      }
      i++;
      j++;
    }
    i = row - 1;
    j = column + 1;
    while ((i >= 0) && (j < grid[0].length)) {
      if ((grid[i][j] == '+') || (grid[i][j] == 'o')) {
        return false;
      }
      i--;
      j++;
    }
    i = row + 1;
    j = column - 1;
    while ((i < grid.length) && (j >= 0)) {
      if ((grid[i][j] == '+') || (grid[i][j] == 'o')) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  private int computeScore(char[][] grid, Integer col, Integer row) {
    int score = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if ((col != null) && (row != null) && (i == row) && (j == col)) {
          return score;
        }
        if ((grid[i][j] == 'x') || (grid[i][j] == '+')) {
          score += 1;
        }
        if ((grid[i][j] == 'o')) {
          score += 2;
        }
      }
    }
    return score;
  }

  private boolean illegalRow(char[][] grid, int row, int column) {
    int numModels = 0;
    int numPlus = 0;
    if (column < (grid[0].length - 1)) {
      return false;
    }
    for (int i = 0; i < grid[0].length; i++) {
      if (grid[row][i] != '.') {
        numModels++;
        if (grid[row][i] == '+') {
          numPlus++;
        }
      }
      if (numModels > (numPlus + 1)) {
        return true;
      }
    }
    return false;
  }

  private boolean illegalColumnn(char[][] grid, int row, int column) {
    if (row < (grid.length - 1)) {
      return false;
    }
    int numModels = 0;
    int numPlus = 0;
    for (int i = 0; i < grid.length; i++) {
      if (grid[i][column] != '.') {
        numModels++;
        if (grid[i][column] == '+') {
          numPlus++;
        }
      }
      if (numModels > (numPlus + 1)) {
        return true;
      }
    }
    return false;
  }

  private boolean illegalDiagonal(char[][] grid, int row, int column) {
    if (column == 0) {
      // left to right diagonal
      if (leftToRightDiagonalIllegal(grid, row, column)) {
        return true;
      }
    }
    if (column == (grid[0].length - 1)) {
      // right to left diagonal
      if (rightToLeftDiagonalIllegal(grid, row, column)) {
        return true;
      }
    }
    if (row == (grid.length - 1)) {
      // left to right and right to left diagonal
      return leftToRightDiagonalIllegal(grid, row, column) && rightToLeftDiagonalIllegal(grid, row, column);
    }
    return false;
  }

  private boolean leftToRightDiagonalIllegal(char[][] grid, int row, int column) {
    int numModels = 0;
    int numX = 0;
    while ((row >= 0) && (column < grid[0].length)) {
      if (grid[row][column] != '.') {
        numModels++;
        if (grid[row][column] == 'x') {
          numX++;
        }
      }
      if (numModels > (numX + 1)) {
        return true;
      }
      row -= 1;
      column += 1;
    }
    return false;
  }

  private boolean rightToLeftDiagonalIllegal(char[][] grid, int row, int column) {
    int numModels = 0;
    int numX = 0;
    while ((row >= 0) && (column >= 0)) {
      if (grid[row][column] != '.') {
        numModels++;
        if (grid[row][column] == 'x') {
          numX++;
        }
      }
      if (numModels > (numX + 1)) {
        return true;
      }
      row -= 1;
      column -= 1;
    }
    return false;
  }

  public static void main(String[] args) {
    final char[][] grid = new char[20][20];
    for (int i = 0; i < grid.length; i++) {
      Arrays.fill(grid[i], '.');
    }

    final ModelAssignmentBruteForce ma = new ModelAssignmentBruteForce();
    ma.assignModel(grid);
    System.out.println(ma.score);
    System.out.println(ma.bestSequence);
  }

}
