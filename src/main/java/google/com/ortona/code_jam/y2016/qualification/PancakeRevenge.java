package google.com.ortona.code_jam.y2016.qualification;

public class PancakeRevenge {

  public int flipPancake(char[] pancake) {
    int moves = 0;
    while (!isHappy(pancake)) {
      moves++;
      flipFirtElements(pancake);
    }
    return moves;
  }

  public void flipFirtElements(char[] pancake) {
    final char initial = pancake[0];
    final char flip = initial == '+' ? '-' : '+';
    for (int i = 0; i < pancake.length; i++) {
      if (pancake[i] == initial) {
        pancake[i] = flip;
      } else {
        break;
      }
    }
  }

  boolean isHappy(char[] pancake) {
    for (int i = 0; i < pancake.length; i++) {
      if (pancake[i] != '+') {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    final char[] pancake = new char[] { '-', '-', '+', '-' };
    final PancakeRevenge pR = new PancakeRevenge();
    System.out.println(pR.flipPancake(pancake));

  }

}
