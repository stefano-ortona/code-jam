package google.com.ortona.code_jam.y2017.round1A;

import java.awt.Point;

public class CakePiece {

  private final Point upperLeft;
  private final Point downRight;

  public CakePiece(int row, int column) {
    this.upperLeft = new Point(row, column);
    this.downRight = new Point(row, column);
  }

  public void expand(char c, char[][] input) {

    // left
    int startColumn = (int) upperLeft.getY() - 1;
    int startRow = (int) upperLeft.getX();
    int endRow = (int) downRight.getX();
    while (true) {
      if (!isColumnOccupied(startRow, endRow, startColumn, input, null)) {
        isColumnOccupied(startRow, endRow, startColumn, input, c);
      } else {
        startColumn++;
        break;
      }
      startColumn--;
    }

    // up
    startRow--;
    int endColumn = (int) downRight.getY();
    while (true) {
      if (!isRowOccupied(startColumn, endColumn, startRow, input, null)) {
        isRowOccupied(startColumn, endColumn, startRow, input, c);
      } else {
        startRow++;
        break;
      }
      startRow--;
    }

    // right
    endColumn++;
    while (true) {
      if (!isColumnOccupied(startRow, endRow, endColumn, input, null)) {
        isColumnOccupied(startRow, endRow, endColumn, input, c);
      } else {
        endColumn--;
        break;
      }
      endColumn++;
    }

    // down
    endRow++;
    while (true) {
      if (!isRowOccupied(startColumn, endColumn, endRow, input, null)) {
        isRowOccupied(startColumn, endColumn, endRow, input, c);
      } else {
        endRow--;
        break;
      }
      endRow++;
    }

  }

  private boolean isRowOccupied(int startColumn, int endColumn, int row, char[][] input, Character fillChar) {
    if ((row < 0) || (row >= input.length)) {
      return true;
    }
    for (int col = startColumn; col <= endColumn; col++) {
      if (input[row][col] != '?') {
        if (fillChar != null) {
          throw new RuntimeException("Trying to fill a cell that was not empty!");
        }
        return true;
      }
      if (fillChar != null) {
        input[row][col] = fillChar;
      }
    }
    return false;
  }

  private boolean isColumnOccupied(int startRow, int endRow, int column, char[][] input, Character fillChar) {
    if ((column < 0) || (column >= input[0].length)) {
      return true;
    }
    for (int row = startRow; row <= endRow; row++) {
      if (input[row][column] != '?') {
        return true;
      }
      if (fillChar != null) {
        input[row][column] = fillChar;
      }
    }
    return false;
  }

}
