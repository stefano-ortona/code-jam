package google.com.ortona.code_jam.y2017.round1A;

import java.util.List;

import com.google.common.collect.Lists;

public class DragonNode {

  public final int hD;
  public final int aD;
  public final int hK;
  public final int aK;

  public DragonNode(int a, int b, int c, int d) {
    this.hD = a;
    this.aD = b;
    this.hK = c;
    this.aK = d;
  }

  public List<DragonNode> getNeighbours() {
    final List<DragonNode> neigh = Lists.newArrayList();
    // does not have any neighbours if the dragon is dead
    if (this.hD <= 0) {
      return neigh;
    }
    // attack
    final DragonNode attack = new DragonNode(this.hD - this.aK, aD, hK - aD, aK);
    neigh.add(attack);

    // buff
    final DragonNode buff = new DragonNode(this.hD - this.aK, aD + PlayTheDragon.increaseAttack, hK, aK);
    neigh.add(buff);

    // cure
    final DragonNode cure = new DragonNode(PlayTheDragon.originalDragonHealth - this.aK, aD, hK, aK);
    neigh.add(cure);

    // de-buff
    final DragonNode debuff = new DragonNode(this.hD - this.aK, aD, hK, aK - PlayTheDragon.decreaseAttack);
    neigh.add(debuff);

    return neigh;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + aD;
    result = (prime * result) + aK;
    result = (prime * result) + hD;
    result = (prime * result) + hK;
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
    final DragonNode other = (DragonNode) obj;
    if (aD != other.aD) {
      return false;
    }
    if (aK != other.aK) {
      return false;
    }
    if (hD != other.hD) {
      return false;
    }
    if (hK != other.hK) {
      return false;
    }
    return true;
  }

  public boolean isVictoryNode() {
    return this.hK <= 0;
  }

  @Override
  public String toString() {
    return "<" + this.hD + "-" + this.aD + "-" + this.hK + "-" + this.aK + ">";
  }

  public boolean isBetterNode(DragonNode node) {
    if ((this.hD >= node.hD) && (this.aD >= node.aD) && (this.hK <= node.hK) && (this.aK <= node.aK)) {
      return true;
    }
    return false;
  }

}
