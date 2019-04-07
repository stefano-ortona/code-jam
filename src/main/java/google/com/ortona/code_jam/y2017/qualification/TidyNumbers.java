package google.com.ortona.code_jam.y2017.qualification;

public class TidyNumbers {
  public long getLatestTidy(long number) {
    if (number <= 0) {
      return 0;
    }
    long[] numArray = fromNumToArray(number);
    Integer wrongIndex = getWrongIndex(numArray);
    while (wrongIndex != null) {
      numArray = modifyNumber(numArray, wrongIndex);
      wrongIndex = getWrongIndex(numArray);
    }
    final long nextTidy = fromArrayToNum(numArray);
    if (nextTidy > number) {
      throw new RuntimeException("ERROR!");
    }
    return nextTidy;
  }

  private Integer getWrongIndex(long[] numArray) {
    for (int i = 0; i < (numArray.length - 1); i++) {
      if (numArray[i] > numArray[i + 1]) {
        return i;
      }
    }
    return null;
  }

  private long[] modifyNumber(long[] numArray, int indexToChange) {
    numArray[indexToChange] -= 1;
    for (int i = indexToChange + 1; i < numArray.length; i++) {
      numArray[i] = 9;
    }
    return numArray;
  }

  private long[] fromNumToArray(long n) {
    final String stringNum = n + "";
    final long[] array = new long[stringNum.length()];
    for (int i = 0; i < stringNum.length(); i++) {
      array[i] = Integer.parseInt(stringNum.charAt(i) + "");
    }
    return array;

  }

  private long fromArrayToNum(long[] array) {
    final StringBuilder stringNum = new StringBuilder();
    // remove leading zero
    int index = 0;
    while (array[index] == 0) {
      index++;
    }
    for (index += 0; index < array.length; index++) {
      stringNum.append(array[index]);
    }
    return Long.parseLong(stringNum.toString());
  }

}
