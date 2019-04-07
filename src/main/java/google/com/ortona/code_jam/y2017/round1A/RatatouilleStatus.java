package google.com.ortona.code_jam.y2017.round1A;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RatatouilleStatus {

  final List<Package> availablePackages;
  final Map<Integer, Integer> portionRequired;

  public RatatouilleStatus(List<Package> p, Map<Integer, Integer> quantityNeeded) {
    this.availablePackages = p;
    this.portionRequired = quantityNeeded;
  }

  public int getQuantityNeeded(final int ingredient) {
    return this.portionRequired.get(ingredient);
  }

  public Package getSmallestPackage() {
    return this.availablePackages.isEmpty() ? null : this.availablePackages.get(0);
  }

  public Set<Integer> getIngredients() {
    return this.portionRequired.keySet();
  }

}
