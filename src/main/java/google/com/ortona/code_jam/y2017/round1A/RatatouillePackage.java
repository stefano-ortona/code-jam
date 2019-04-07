package google.com.ortona.code_jam.y2017.round1A;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RatatouillePackage {

  private Set<Integer> ingredients = Sets.newHashSet();
  int portions;
  List<Package> packages = Lists.newArrayList();

  public RatatouillePackage(Package p, RatatouilleStatus status) {
    this.packages.add(p);
    this.portions = (int) Math.round((p.quantity + 0.) / status.getQuantityNeeded(p.ingredient));
  }

  public RatatouillePackage() {
  }

  public double addPackage(Package p, RatatouilleStatus status) {
    if (this.ingredients.contains(p.ingredient)) {
      return 2;
    }
    final double perc = (p.quantity + 0.) / (status.getQuantityNeeded(p.ingredient) * portions);
    return perc;
  }

  public void addPackage(Package p) {
    this.ingredients.add(p.ingredient);
    this.packages.add(p);
  }

  @Override
  public RatatouillePackage clone() {
    final RatatouillePackage p = new RatatouillePackage();
    p.ingredients = Sets.newHashSet(this.ingredients);
    p.portions = this.portions;
    p.packages = Lists.newArrayList(this.packages);
    return p;
  }

}
