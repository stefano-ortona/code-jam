package google.com.ortona.code_jam.kickstart.y2015.round1A;

public class Haircut {

  public int getWaitingTime(int place, int[] servingTime) {
    long initialTime = getTimeWithFastestBarber(servingTime, place);
    long upper = initialTime;
    long lower = 0;
    while (true) {
      final long customServed1 = getCustomerServed(initialTime, servingTime, place);
      final long customServed2 = getCustomerServed(initialTime + 1, servingTime, place);
      if ((customServed1 < place) && (customServed2 >= place)) {
        // found the right timing initial+1
        return getBarberIndex(servingTime, initialTime, place, place - customServed1 - 1) + 1;
      } else {
        // check where you are
        if (customServed1 >= place) {
          final long newInitialTime = getNextTime(upper, lower, initialTime, true);
          upper = initialTime;
          initialTime = newInitialTime;
        } else {
          final long newInitialTime = getNextTime(upper, lower, initialTime, false);
          lower = initialTime;
          initialTime = newInitialTime;
        }
      }
    }
  }

  long getNextTime(long upper, long lower, long current, boolean decrease) {
    if (decrease) {
      return ((current - lower) / 2) + lower;
    } else {
      return ((upper - current) / 2) + current;
    }
  }

  int getBarberIndex(int[] servingTime, long instant, int place, long customerBeforeYou) {
    int alreadyServed = 0;
    for (int i = 0; i < servingTime.length; i++) {
      if ((instant % servingTime[i]) == 0) {
        if (alreadyServed == customerBeforeYou) {
          return i;
        } else {
          alreadyServed++;
        }
      }
    }
    throw new RuntimeException("impossible!");
  }

  long getCustomerServed(long instant, int[] servingTime, int position) {
    int customServed = 0;
    for (int i = 0; i < servingTime.length; i++) {
      if (customServed > (position + servingTime.length)) {
        return customServed;
      }
      customServed += instant / servingTime[i];
      if ((instant % servingTime[i]) != 0) {
        customServed++;
      }
    }
    return customServed;
  }

  long getTimeWithFastestBarber(int[] servingTime, int position) {
    long bestTime = (servingTime[0] * (position + 0L));
    if (bestTime <= 0) {
      bestTime = Long.MAX_VALUE - 1;
    }
    for (int i = 1; i < servingTime.length; i++) {
      final long newTime = servingTime[i] * (position + 0L);
      if ((newTime > 0) && (newTime < bestTime)) {
        bestTime = newTime;
      }
    }
    return bestTime;
  }

}
