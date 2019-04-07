package google.com.ortona.code_jam.y2015.qualification;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class OminousOmino {

  public boolean isNotWinnable(int pieceSize, int row, int column) {
    if (isEasyCase(row, column, pieceSize)) {
      return true;
    }
    if (pieceSize == 1) {
      return false;
    }

    // place initial square
    final boolean[][] status = new boolean[row][column];
    final Set<OminousPiece> initialPieces = Sets.newHashSet();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        initialPieces.addAll(expandStatus(i, j, status, pieceSize));
      }
    }
    for (final OminousPiece onePiece : initialPieces) {
      final boolean[][] newStatus = placePieceToStatus(status, onePiece);
      if (!canWinTheGame(newStatus, onePiece)) {
        return true;
      }
    }
    return false;
  }

  private boolean isEasyCase(final int row, final int column, final int pieceSize) {
    if (pieceSize > (row * column)) {
      return true;
    }
    if (pieceSize == (row * column)) {
      return true;
    }
    if (pieceSize == 1) {
      return false;
    }
    if ((row == 1) || (column == 1)) {
      // can always place to leave just a single piece out
      return true;
    }
    if ((((row * column) - pieceSize) % pieceSize) != 0) {
      return true;
    }
    if (pieceSize >= 3) {
      // can always block a single cell with 3 pieces
      return true;
    }
    return false;
  }

  private Set<OminousPiece> expandStatus(int row, int column, boolean[][] curStatus, int size) {
    final Set<OminousPiece> allPieces = new HashSet<OminousPiece>();
    if (curStatus[row][column]) {
      return allPieces;
    }
    List<OminousPiece> curPieces = Lists.newArrayList(new OminousPiece(row, column));
    for (int i = 0; i < (size - 1); i++) {
      // expand in four directions
      final List<OminousPiece> newCurPieces = Lists.newArrayList();
      for (final OminousPiece onePiece : curPieces) {
        newCurPieces.addAll(onePiece.expand(curStatus));
      }
      curPieces = newCurPieces;
    }
    curPieces.forEach(p -> {
      if (p.cellOccupied.size() != size) {
        throw new RuntimeException("I created a piece that does not respect size constraints!");
      }
    });
    allPieces.addAll(curPieces);
    return allPieces;
  }

  private boolean[][] placePieceToStatus(boolean[][] status, OminousPiece piece) {
    final boolean out[][] = new boolean[status.length][status[0].length];
    for (int i = 0; i < status.length; i++) {
      for (int j = 0; j < status[0].length; j++) {
        out[i][j] = status[i][j];
      }
    }
    for (final Pair<Integer, Integer> oneSquare : piece.cellOccupied) {
      out[oneSquare.getLeft()][oneSquare.getRight()] = true;
    }
    return out;
  }

  private boolean canWinTheGame(boolean[][] status, OminousPiece originalPiece) {
    return canWinTheGame(status, originalPiece, 0, 0);
  }

  private boolean canWinTheGame(boolean[][] status, OminousPiece originalPiece, int curRow, int curColumn) {
    if (curColumn >= status[0].length) {
      curColumn = 0;
      curRow++;
    }
    if (curRow >= status.length) {
      // todo: check it has used at least one piece
      return allCovered(status);
    }
    final Set<OminousPiece> getCurPieces = expandStatus(curRow, curColumn, status, originalPiece.cellOccupied.size());
    for (final OminousPiece oneP : getCurPieces) {
      final boolean newStatus[][] = placePieceToStatus(status, oneP);
      final boolean canWinCurGame = canWinTheGame(newStatus, originalPiece, curRow, curColumn + 1);
      if (canWinCurGame) {
        return true;
      }
    }
    // try not to place a piece
    final boolean canWinGame = canWinTheGame(status, originalPiece, curRow, curColumn + 1);
    if (canWinGame) {
      return true;
    }
    return false;
  }

  private boolean allCovered(boolean status[][]) {
    for (int i = 0; i < status.length; i++) {
      for (int j = 0; j < status[0].length; j++) {
        if (status[i][j] == false) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    final OminousOmino oO = new OminousOmino();
    System.out.println(oO.isNotWinnable(2, 2, 3));

  }
}
