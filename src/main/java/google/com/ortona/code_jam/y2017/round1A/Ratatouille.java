package google.com.ortona.code_jam.y2017.round1A;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Ratatouille {

  public int maximumNumberPackage(RatatouilleStatus status) {
    final Map<Integer, List<Package>> quantity2package = getPackagePerQuantity(status);
    final AtomicInteger bestScore = new AtomicInteger(0);
    getMaxSolution(quantity2package, status, 0, bestScore);
    return bestScore.get();
  }

  private void getMaxSolution(Map<Integer, List<Package>> solutions, RatatouilleStatus status, int curScore,
      AtomicInteger bestScore) {
    removeUnservablePackage(solutions, status);
    if (((curScore + solutions.size()) <= bestScore.get())
        || ((curScore + getRemainingPackages(solutions)) <= bestScore.get())) {
      return;
    }
    // remove unservable quantity
    if (solutions.isEmpty()) {
      if (curScore > bestScore.get()) {
        bestScore.set(curScore);
      }
      return;
    }
    final int nextQuantityPackage = solutions.keySet().iterator().next();
    // get next package
    final List<List<Package>> possibleValidPackages = getPossibleValidPackages(solutions.get(nextQuantityPackage),
        status.getIngredients());
    // create package with cur quantity
    for (final List<Package> onePack : possibleValidPackages) {
      // remove cur packages from solution
      final Map<Integer, List<Package>> newSol = removePackageFromSolution(solutions, onePack);
      // add 1 to the score with the cur package
      getMaxSolution(newSol, status, curScore + 1, bestScore);
    }
    // do not create a package with cur quantity
    final Map<Integer, List<Package>> newSol = removePackageFromSolution(solutions, Lists.newArrayList());
    newSol.remove(nextQuantityPackage);
    getMaxSolution(newSol, status, curScore, bestScore);
  }

  private int getRemainingPackages(Map<Integer, List<Package>> solutions) {
    final Set<Integer> allPackIds = Sets.newHashSet();
    solutions.values().forEach(l -> {
      l.forEach(pac -> allPackIds.add(pac.id));
    });
    return allPackIds.size();
  }

  private void removeUnservablePackage(Map<Integer, List<Package>> quantity2package, RatatouilleStatus status) {
    final Iterator<Integer> keyIteraotr = quantity2package.keySet().iterator();
    while (keyIteraotr.hasNext()) {
      final int q = keyIteraotr.next();
      if (!isValidRatatouillePackage(quantity2package.get(q), status.portionRequired.keySet())) {
        keyIteraotr.remove();
      }
    }
  }

  private List<List<Package>> getPossibleValidPackages(List<Package> packages, Set<Integer> ingredient) {
    List<List<Package>> allPosSol = Lists.newLinkedList();
    allPosSol.add(Lists.newArrayList());
    for (final int oneIngr : ingredient) {
      final List<List<Package>> newSol = Lists.newArrayList();
      final List<Package> allPackageIngr = getallPackageForIngredient(packages, oneIngr);
      for (final Package onePack : allPackageIngr) {
        for (final List<Package> prevSol : allPosSol) {
          final List<Package> newCurSol = allPackageIngr.size() == 1 ? prevSol : Lists.newArrayList(prevSol);
          newCurSol.add(onePack);
          newSol.add(newCurSol);
        }
      }
      if (allPackageIngr.size() > 1) {
        allPosSol = newSol;
      }
    }
    return allPosSol;
  }

  private List<Package> getallPackageForIngredient(List<Package> packages, Integer ingr) {
    return packages.stream().filter(p -> p.ingredient == ingr).collect(Collectors.toList());
  }

  private Map<Integer, List<Package>> removePackageFromSolution(Map<Integer, List<Package>> curSol,
      List<Package> onePack) {
    final Map<Integer, List<Package>> newSol = Maps.newHashMap();
    curSol.forEach((k, v) -> {
      final List<Package> newPack = Lists.newArrayList(v);
      newPack.removeAll(onePack);
      newSol.put(k, newPack);
    });
    return newSol;
  }

  private Map<Integer, List<Package>> getPackagePerQuantity(RatatouilleStatus status) {
    final Map<Integer, List<Package>> quantity2packages = Maps.newHashMap();
    final Iterator<Package> pIterator = status.availablePackages.iterator();
    while (pIterator.hasNext()) {
      final Package p = pIterator.next();
      final List<Integer> posQuantity = getPossibleQuantity(p, status);
      posQuantity.forEach(q -> {
        final List<Package> allPackages = quantity2packages.getOrDefault(q, Lists.newArrayList());
        allPackages.add(p);
        quantity2packages.put(q, allPackages);
      });
    }
    return quantity2packages;
  }

  private boolean isValidRatatouillePackage(List<Package> packages, Set<Integer> allIngr) {
    final Set<Integer> curIngr = Sets.newHashSet();
    packages.forEach(p -> {
      curIngr.add(p.ingredient);
    });
    return curIngr.equals(allIngr);
  }

  private List<Integer> getPossibleQuantity(Package p, RatatouilleStatus status) {
    final int oneIngrQuanity = status.getQuantityNeeded(p.ingredient);
    final int initial = (int) Math.round((p.quantity + 0.) / oneIngrQuanity);
    boolean findGoodQuan = true;
    int cur = initial;
    final List<Integer> output = Lists.newLinkedList();
    while (findGoodQuan) {
      if (isGoodCandidate(oneIngrQuanity * cur, p.quantity)) {
        output.add(cur);
      } else {
        findGoodQuan = false;
      }
      cur--;
    }
    findGoodQuan = true;
    cur = initial + 1;
    while (findGoodQuan) {
      if (isGoodCandidate(oneIngrQuanity * cur, p.quantity)) {
        output.add(cur);
      } else {
        findGoodQuan = false;
      }
      cur++;
    }
    return output;
  }

  private boolean isGoodCandidate(int desiredQuantity, int actualQuantity) {
    if (desiredQuantity == 0) {
      return false;
    }
    final double perc = (actualQuantity + 0.) / desiredQuantity;
    return (desiredQuantity > 0) && (perc >= 0.9) && (perc <= 1.1);
  }

  public static void main(String[] args) {
    // 1 8
    // 10
    // 10000 10678 9000 11000 10574 10694 10000 9134
    final List<Package> availPackage = Lists.newArrayList();
    availPackage.add(new Package(0, 0, 10000));
    availPackage.add(new Package(1, 0, 10678));
    availPackage.add(new Package(2, 0, 9000));
    availPackage.add(new Package(3, 0, 11000));
    availPackage.add(new Package(4, 0, 10574));
    availPackage.add(new Package(5, 0, 10694));
    availPackage.add(new Package(6, 0, 10000));
    availPackage.add(new Package(7, 0, 9134));

    final Map<Integer, Integer> portions = Maps.newHashMap();
    portions.put(0, 10);

    final RatatouilleStatus s = new RatatouilleStatus(availPackage, portions);

    final Ratatouille r = new Ratatouille();
    System.out.print(r.maximumNumberPackage(s));

  }

}
