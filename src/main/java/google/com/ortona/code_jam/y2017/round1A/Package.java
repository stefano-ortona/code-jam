package google.com.ortona.code_jam.y2017.round1A;

public class Package {
  int ingredient;
  int quantity;
  int id;

  public Package(int id, int ingr, int quan) {
    this.id = id;
    this.ingredient = ingr;
    this.quantity = quan;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + id;
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
    final Package other = (Package) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return id + ",ingr=" + ingredient + ",quan=" + quantity;
  }

}
