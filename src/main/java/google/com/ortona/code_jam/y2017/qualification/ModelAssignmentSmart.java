package google.com.ortona.code_jam.y2017.qualification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

public class ModelAssignmentSmart extends ModelAssignmentBruteForce {

  @Override
  public String assignModel(char[][] grid) {
    // check first row
    int initialCandidate = -1;
    char prevValue = 'o';
    for (int i = 0; i < grid[0].length; i++) {
      if ((grid[0][i] == 'o') || (grid[0][i] == 'x')) {
        initialCandidate = i;
        prevValue = grid[0][i];
        break;
      }
    }
    final List<Pair<Integer, Integer>> bestOAllocation = Lists.newArrayList();
    if (initialCandidate != -1) {
      grid[0][initialCandidate] = 'o';
      placeRemainingZeros(grid, 1, 1, new AtomicInteger(1), bestOAllocation);
      // try not to upgrade
      if ((prevValue != 'o') && (bestOAllocation.size() < (grid.length - 1))) {
        grid[0][initialCandidate] = prevValue;
        placeRemainingZeros(grid, 1, 0, new AtomicInteger(bestOAllocation.size()), bestOAllocation);
      }

    } else {
      placeRemainingZeros(grid, 0, 0, new AtomicInteger(0), bestOAllocation);
    }
    int score = 0;
    final StringBuilder moves = new StringBuilder();
    for (final Pair<Integer, Integer> oneZero : bestOAllocation) {
      score += 2;
      if (grid[oneZero.getLeft()][oneZero.getRight()] != 'o') {
        moves.append(oneZero.getLeft() + " " + oneZero.getRight() + " o\n");
      }
      grid[oneZero.getLeft()][oneZero.getRight()] = 'o';
    }
    // place all others
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '.') {
          if (isDiagonalAccessible(grid, j, i)) {
            moves.append(i + " " + j + " +\n");
            grid[i][j] = '+';
            score += 1;
          } else if (isColumnRowAccessible(grid, j, i)) {
            grid[i][j] = 'x';
            score += 1;
            moves.append(i + " " + j + " x\n");
          }
        }
      }
    }
    System.out.println("Final score: " + score);
    return moves.toString();
  }

  private void placeRemainingZeros(char[][] grid, int curRow, int numZero, AtomicInteger bestScore,
      List<Pair<Integer, Integer>> bestAllocation) {
    if (((numZero + grid.length) - curRow) <= bestScore.get()) {
      // cannot improve score
      return;
    }
    if (curRow >= grid.length) {
      if (numZero > bestScore.get()) {
        bestScore.set(numZero);
        bestAllocation.clear();
        bestAllocation.addAll(getOAllocation(grid));
      }
      return;
    }
    final List<Integer> potPositions = getPotentialPositions(grid, curRow);
    for (final int onePos : potPositions) {
      // place a 'o'
      final char prevValue = grid[curRow][onePos];
      grid[curRow][onePos] = 'o';
      placeRemainingZeros(grid, curRow + 1, numZero + 1, bestScore, bestAllocation);
      grid[curRow][onePos] = prevValue;
    }
    // try not to place it
    placeRemainingZeros(grid, curRow + 1, numZero, bestScore, bestAllocation);

  }

  private List<Pair<Integer, Integer>> getOAllocation(char[][] grid) {
    final List<Pair<Integer, Integer>> oAllocation = new ArrayList<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'o') {
          oAllocation.add(Pair.of(i, j));
        }
      }
    }
    return oAllocation;
  }

  private List<Integer> getPotentialPositions(char[][] grid, int curRow) {
    final List<Integer> posPosition = Lists.newArrayList();
    for (int i = 0; i < grid[0].length; i++) {
      if (isColumnRowAccessible(grid, i, curRow) && isDiagonalAccessible(grid, i, curRow)) {
        posPosition.add(i);
      }
    }
    return posPosition;
  }

  public static void main(String[] args) {
    final char[][] grid = new char[100][100];
    for (int i = 0; i < grid.length; i++) {
      Arrays.fill(grid[i], '.');
    }

    final ModelAssignmentSmart ma = new ModelAssignmentSmart();
    System.out.println(ma.assignModel(grid));
  }

}
