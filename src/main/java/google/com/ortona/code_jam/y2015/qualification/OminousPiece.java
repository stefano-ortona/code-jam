package google.com.ortona.code_jam.y2015.qualification;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class OminousPiece {
  Set<Pair<Integer, Integer>> cellOccupied;
  private int lastRow;
  private int lastColumn;

  public OminousPiece(int lastRow, int lastColumn) {
    this.cellOccupied = Sets.newHashSet();
    this.cellOccupied.add(Pair.of(lastRow, lastColumn));
    this.lastColumn = lastColumn;
    this.lastRow = lastRow;
  }

  @Override
  public OminousPiece clone() {
    final OminousPiece p = new OminousPiece(this.lastRow, this.lastColumn);
    p.cellOccupied = Sets.newHashSet(this.cellOccupied);
    return p;
  }

  public List<OminousPiece> expand(boolean[][] status) {
    final List<OminousPiece> allPieces = Lists.newArrayList();
    // up
    final List<Pair<Integer, Integer>> allPairs = Lists.newArrayList();
    if ((lastRow > 0) && (status[lastRow - 1][lastColumn] == false)) {
      allPairs.add(Pair.of(lastRow - 1, lastColumn));
    }
    // down
    if (((lastRow + 1) < status.length) && (status[lastRow + 1][lastColumn] == false)) {
      allPairs.add(Pair.of(lastRow + 1, lastColumn));
    }
    // left
    if ((lastColumn > 0) && (status[lastRow][lastColumn - 1] == false)) {
      allPairs.add(Pair.of(lastRow, lastColumn - 1));
    }
    // right
    if (((lastColumn + 1) < status[0].length) && (status[lastRow][lastColumn + 1] == false)) {
      allPairs.add(Pair.of(lastRow, lastColumn + 1));
    }

    for (final Pair<Integer, Integer> onePair : allPairs) {
      if (!this.cellOccupied.contains(onePair)) {
        final OminousPiece newPiece = this.clone();
        newPiece.cellOccupied.add(onePair);
        newPiece.lastRow = onePair.getLeft();
        newPiece.lastColumn = onePair.getRight();
        allPieces.add(newPiece);
      }
    }
    if (this.cellOccupied.size() == 3) {
      allPieces.addAll(isSpecialCase(status));
    }
    return allPieces;
  }

  private List<OminousPiece> isSpecialCase(boolean[][] status) {
    final List<OminousPiece> additionalPieces = Lists.newArrayList();
    if (this.cellOccupied.size() != 3) {
      return additionalPieces;
    }
    boolean sameRow = true;
    boolean sameColumn = true;
    final int row = cellOccupied.iterator().next().getLeft();
    final int column = cellOccupied.iterator().next().getRight();
    for (final Pair<Integer, Integer> onePair : cellOccupied) {
      if (onePair.getLeft() != row) {
        sameRow = false;
      }
      if (onePair.getRight() != column) {
        sameColumn = false;
      }
    }
    if (sameRow) {
      final int midValue = getMidValue(false);
      if ((row > 0) && (status[row - 1][midValue] == false)) {
        final OminousPiece newPiece = clone();
        newPiece.cellOccupied.add(Pair.of(row - 1, midValue));
        newPiece.lastRow = row - 1;
        newPiece.lastColumn = midValue;
        additionalPieces.add(newPiece);
      }
      if (((row + 1) < status.length) && (status[row + 1][midValue] == false)) {
        final OminousPiece newPiece = clone();
        newPiece.cellOccupied.add(Pair.of(row + 1, midValue));
        newPiece.lastRow = row + 1;
        newPiece.lastColumn = midValue;
        additionalPieces.add(newPiece);

      }
    }
    if (sameColumn) {
      final int midValue = getMidValue(true);
      if ((column > 0) && (status[midValue][column - 1] == false)) {
        final OminousPiece newPiece = clone();
        newPiece.cellOccupied.add(Pair.of(midValue, column - 1));
        newPiece.lastRow = midValue;
        newPiece.lastColumn = column - 1;
        additionalPieces.add(newPiece);

      }
      if (((column + 1) < status[0].length) && (status[midValue][column + 1] == false)) {
        final OminousPiece newPiece = clone();
        newPiece.cellOccupied.add(Pair.of(midValue, column + 1));
        newPiece.lastRow = midValue;
        newPiece.lastColumn = column + 1;
        additionalPieces.add(newPiece);

      }
    }
    return additionalPieces;
  }

  private int getMidValue(boolean row) {
    final TreeSet<Integer> allValues = new TreeSet<Integer>();
    for (final Pair<Integer, Integer> oneP : cellOccupied) {
      if (row) {
        allValues.add(oneP.getLeft());
      } else {
        allValues.add(oneP.getRight());
      }
    }
    allValues.pollFirst();
    return allValues.pollFirst();
  }

  @Override
  public String toString() {
    final StringBuilder sB = new StringBuilder();
    cellOccupied.forEach(c -> sB.append(c.getLeft() + "," + c.getRight() + "-"));
    return sB.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((cellOccupied == null) ? 0 : cellOccupied.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final OminousPiece other = (OminousPiece) obj;
    if (cellOccupied == null) {
      if (other.cellOccupied != null) {
        return false;
      }
    } else if (!cellOccupied.equals(other.cellOccupied)) {
      return false;
    }
    return true;
  }

}
