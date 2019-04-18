package google.com.ortona.code_jam.y2019.round1A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Pylons {

  private List<Move> allPosMoves;

  public List<Move> computeMoves(int row, int column) {
    allPosMoves = new ArrayList<>();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        allPosMoves.add(new Move(i, j));
      }
    }
    final List<Move> moves = new LinkedList<>();
    Collections.shuffle(allPosMoves);
    for (final Move oneMove : allPosMoves) {
      // start from cur position
      moves.add(oneMove);
      final List<Move> outputMoves = nextMove(moves, row * column);
      if (outputMoves != null) {
        return outputMoves;
      }
      // backtrack
      moves.clear();
    }
    return null;
  }

  private List<Move> nextMove(List<Move> curMoves, int totMoves) {
    if (curMoves.size() == totMoves) {
      // finished
      return curMoves;
    }
    final List<Move> admissible = getAdmissibleMoves(curMoves);
    Collections.shuffle(admissible);
    // try to move in all possible cells
    for (final Move m : admissible) {
      // do the move
      curMoves.add(m);
      final List<Move> nextMoves = nextMove(curMoves, totMoves);
      if (nextMoves != null) {
        return nextMoves;
      }
      // backtrack
      curMoves.remove(curMoves.size() - 1);
    }
    return null;
  }

  private boolean isAdmissibleMove(int row, int column, int nextRow, int nextColumn) {
    return (row != nextRow) && (column != nextColumn) && ((row - column) != (nextRow - nextColumn))
        && ((row + column) != (nextRow + nextColumn));
  }

  private class Move {
    public Move(int x, int y) {
      this.x = x;
      this.y = y;
    }

    int x;
    int y;

    @Override
    public String toString() {
      return (x + 1) + " " + (y + 1);
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + x;
      result = (prime * result) + y;
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
      final Move other = (Move) obj;
      if (x != other.x) {
        return false;
      }
      if (y != other.y) {
        return false;
      }
      return true;
    }
  }

  public List<Move> getAdmissibleMoves(List<Move> currentMoves) {
    final Move lastMove = currentMoves.get(currentMoves.size() - 1);
    // remove unadmissible
    return allPosMoves.stream()
        .filter(m -> isAdmissibleMove(lastMove.x, lastMove.y, m.x, m.y) && !currentMoves.contains(m))
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    final Pylons sol = new Pylons();
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      final int row = in.nextInt();
      final int column = in.nextInt();
      in.nextLine();
      final List<Move> moves = sol.computeMoves(row, column);
      if (moves == null) {
        System.out.println("Case #" + i + ": IMPOSSIBLE");
      } else {
        System.out.println("Case #" + i + ": POSSIBLE");
        sol.printSolution(moves);
      }
    }
    in.close();
  }

  private void printSolution(List<Move> moves) {
    moves.forEach(m -> System.out.println(m));
  }

}
