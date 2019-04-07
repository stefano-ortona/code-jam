package google.com.ortona.code_jam.y2017.round1A;

import java.util.List;
import java.util.TreeSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class PlayTheDragon {

  public static int increaseAttack;
  public static int decreaseAttack;
  public static int originalDragonHealth;

  public int defeatKnife(int hD, int aD, int hK, int aK, int B, int D) {
    increaseAttack = B;
    decreaseAttack = D;
    originalDragonHealth = hD;
    final DragonNode firstNode = new DragonNode(hD, aD, hK, aK);
    List<DragonNode> curNodes = Lists.newArrayList(firstNode);
    final TreeSet<DragonNode> visited = Sets.newTreeSet(new DragonNodeComparator());
    visited.add(firstNode);
    int iteration = 0;
    while (!curNodes.isEmpty()) {
      System.out.println("Iteration:..." + iteration);
      if (containsVictoryNode(curNodes)) {
        return iteration;
      }
      iteration++;
      curNodes = getNextNodes(curNodes, visited);
    }
    return -1;
  }

  private boolean containsVictoryNode(List<DragonNode> curNodes) {
    return curNodes.stream().filter(n -> n.isVictoryNode()).findFirst().isPresent();
  }

  public List<DragonNode> getNextNodes(List<DragonNode> curNodes, TreeSet<DragonNode> visitedNodes) {
    final List<DragonNode> nextNodes = Lists.newArrayList();
    for (final DragonNode oneNode : curNodes) {
      final List<DragonNode> neigh = oneNode.getNeighbours();
      for (final DragonNode oneNeigh : neigh) {
        if (worthVisiting(oneNeigh, visitedNodes)) {
          nextNodes.add(oneNeigh);
        }
      }
    }
    return nextNodes;
  }

  private boolean worthVisiting(DragonNode node, TreeSet<DragonNode> visited) {
    if (visited.contains(node)) {
      return false;
    }
    final boolean worth = !visited.first().isBetterNode(node);
    if (worth) {
      visited.add(node);
    }
    return worth;
    // final boolean isWorth = !visited.stream().filter(n -> n.isBetterNode(node)).findFirst().isPresent();
    // visited.add(node);
    // return isWorth;
  }

  public static void main(String[] args) {
    final String inputString = "96 1 94 47 0 1";
    // final String inputString = "3 1 3 2 1 0";
    final int[] input = new int[6];
    int i = 0;
    for (final String one : inputString.split(" ")) {
      input[i] = Integer.parseInt(one);
      i++;
    }
    final PlayTheDragon pD = new PlayTheDragon();
    System.out.println(pD.defeatKnife(input[0], input[1], input[2], input[3], input[4], input[5]));

  }

}
