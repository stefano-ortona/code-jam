package google.com.ortona.code_jam.y2014.qualification;

public class Minesweeper {

  public char[][] solvePuzzle(int row, int column, int numMines) {
    final int freeSpots = (row * column) - numMines;
    // build a rectangle with free spots
    Rectangle start = buildRectanlge(freeSpots, row, column);
    int curSize = (start.lowX + 1) * (start.lowY + 1);
    if ((freeSpots == 1) || ((start != null) && (curSize == freeSpots)
        && (((start.lowX > 1) && (start.lowY > 1)) || (column == 1) || (row == 1)))) {

    } else {
      if (freeSpots <= 3) {
        return null;
      }
      if (((freeSpots % 3) == 0) || (freeSpots <= 10)) {
        start = buildRectanlge(freeSpots, row, column);
      } else {
        if (((freeSpots - 4) % 3) == 0) {
          start = buildRectanlge(freeSpots - 4, row, column);
        } else {
          start = buildRectanlge(freeSpots - 5, row, column);
        }
      }
    }
    if (start == null) {
      return null;
    }
    curSize = (start.lowX + 1) * (start.lowY + 1);
    final char[][] board = new char[row][column];
    // fill initial rectangle board
    for (int i = 0; i <= start.lowX; i++) {
      for (int j = 0; j <= start.lowY; j++) {
        board[i][j] = '.';
      }
    }
    while (curSize != freeSpots) {
      final int newExp = expandBoard(board, freeSpots - curSize);
      if (newExp == -1) {
        return null;
        // throw new RuntimeException("DID NOT MANAGE!");
      }
      curSize += newExp;
    }
    // fill all remaining pieces with bombs
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        if (board[i][j] != '.') {
          board[i][j] = '*';
        }
      }
    }
    board[0][0] = 'c';
    return board;
  }

  private int expandBoard(char[][] board, int remainingPieces) {
    if (remainingPieces > 5) {
      remainingPieces = 3;
    }
    // find a piece in the board that can be expanded of remaining pieces
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        final int sorr = computeSorrounding(i, j, board, false);
        if (sorr == remainingPieces) {
          return computeSorrounding(i, j, board, true);
        }
      }
    }
    if ((remainingPieces == 4)) {
      return expandBoard(board, 2);
    }
    if (remainingPieces == 5) {
      return expandBoard(board, 3);
    }
    return -1;
  }

  private int computeSorrounding(int row, int col, char[][] board, boolean fill) {
    if (board[row][col] != '.') {
      return -1;
    }
    int sorr = 0;
    for (int i = Math.max(row - 1, 0); (i < board.length) && (i <= (row + 1)); i++) {
      for (int j = Math.max(col - 1, 0); (j < board[0].length) && (j <= (col + 1)); j++) {
        if (board[i][j] != '.') {
          sorr++;
          if (fill) {
            board[i][j] = '.';
          }
        }
      }
    }
    return sorr;
  }

  private Rectangle buildRectanlge(int freeSpots, int row, int column) {
    int curX = 0;
    int curY = 0;
    int curSize = 1;
    boolean expanded = true;
    while (expanded) {
      expanded = false;
      if (((curSize + curY + 1) <= freeSpots) && (curX < (row - 1))) {
        // expand right
        curX += 1;
        curSize += curY + 1;
        expanded = true;
      }
      if (((curSize + curX + 1) <= freeSpots) && (curY < (column - 1))) {
        curY += 1;
        curSize += curX + 1;
        expanded = true;
      }
      // expand dow
    }
    Rectangle rec = null;
    rec = new Rectangle();
    rec.upX = 0;
    rec.upY = 0;
    rec.lowX = curX;
    rec.lowY = curY;
    return rec;
  }

  public static void main(String[] args) {
    final int[] input = new int[] { 3, 4, 11 };
    final Minesweeper mS = new Minesweeper();

    System.out.println(mS.solvePuzzle(input[0], input[1], input[2]));
  }

}
