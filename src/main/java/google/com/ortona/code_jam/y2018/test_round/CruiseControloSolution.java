package google.com.ortona.code_jam.y2018.test_round;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;

public class CruiseControloSolution {

  public double getBestSpeed(List<HorsePosition> allPos, double finalDest) {
    final AtomicDouble maxTime = new AtomicDouble(-1);
    allPos.forEach(oneP -> {
      // get hour
      final double curHor = (finalDest - oneP.initialPos) / oneP.speed;
      maxTime.set(Math.max(curHor, maxTime.get()));
    });
    return finalDest / maxTime.get();
  }

  class HorsePosition {
    double speed;
    double initialPos;
  }

  public static void main(String[] args) {
    final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    final CruiseControloSolution cS = new CruiseControloSolution();
    final int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      final int finalDest = in.nextInt();
      // read number of horses
      final int numHorses = in.nextInt();
      final List<HorsePosition> allPos = Lists.newArrayList();
      for (int h = 1; h <= numHorses; h++) {
        final HorsePosition hP = cS.new HorsePosition();
        hP.initialPos = in.nextInt();
        hP.speed = in.nextInt();
        allPos.add(hP);
      }
      final double curSolSpeed = cS.getBestSpeed(allPos, finalDest);
      System.out.println("Case #" + i + ": " + curSolSpeed);
    }
    in.close();
  }

}
