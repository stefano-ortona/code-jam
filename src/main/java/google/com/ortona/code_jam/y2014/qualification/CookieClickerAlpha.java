package google.com.ortona.code_jam.y2014.qualification;

public class CookieClickerAlpha {
  private double farmCost;
  private double farmProduction;
  private double cookieTarget;

  public double getMinimumSolvingTime(double farmCost, double farmProduction, double cookieTarget) {
    this.farmCost = farmCost;
    this.farmProduction = farmProduction;
    this.cookieTarget = cookieTarget;
    return getBestTime();
  }

  private double getBestTime() {
    double curTime = 0;
    double curProduction = 2.;
    double minScore = cookieTarget / 2.;
    while (curTime < minScore) {
      // collect cookies until you have them all
      final double curScore = (this.cookieTarget / curProduction) + curTime;
      if (curScore < minScore) {
        minScore = curScore;
      }
      // try to build another farm
      curTime += farmCost / curProduction;
      curProduction += farmProduction;
    }
    return minScore;
  }

}
