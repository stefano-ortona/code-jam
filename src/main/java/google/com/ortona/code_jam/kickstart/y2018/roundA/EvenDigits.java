package google.com.ortona.code_jam.kickstart.y2018.roundA;

public class EvenDigits {

  public long getEvenTrasformation(long input) {
    final int nonEvenDigit = getNonEvenDigit(input);
    if (nonEvenDigit == -1) {
      return 0;
    }
    final String remNumb = (input + "").substring(nonEvenDigit);
    if (remNumb.length() == 1) {
      return 1;
    }
    final int firstDigit = Integer.parseInt(remNumb.charAt(0) + "");

    final long plusNumb = firstDigit == 9 ? 2 * input : (firstDigit + 1) * (long) Math.pow(10, remNumb.length() - 1);
    final long minusNumb = (firstDigit - 1) * (long) Math.pow(10, remNumb.length() - 1);

    final long remNumberParse = Long.parseLong(remNumb);

    return Math.min(plusNumb - remNumberParse, remNumberParse - minusNumb);

  }

  private int getNonEvenDigit(long input) {
    final String numString = input + "";
    for (int i = 0; i < numString.length(); i++) {
      if ((Integer.parseInt(numString.charAt(i) + "") % 2) == 1) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    final long input = 11;
    final EvenDigits eD = new EvenDigits();
    System.out.println(eD.getEvenTrasformation(input));
  }

}
