package google.com.ortona.code_jam.y2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Cryptopangrams {
  private Set<Long> primeTill10K;
  private long curMaxPrime;
  
  public Cryptopangrams(){
    //initialize first 10K prime numbers
    computeFirstKPrimes(10000);
  }
  
  private void computeFirstKPrimes(long maxNumb){
    curMaxPrime=maxNumb;
    primeTill10K = new TreeSet<>();
    boolean[] primes=new boolean[((int) maxNumb)+1];
    Arrays.fill(primes,true);        // assume all integers are prime.
    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
    for (int i=2;i<primes.length;i++) {
        //if the number is prime, 
        //then go through all its multiples and make their values false.
        if(primes[i]) {
            for (int j=2;i*j<primes.length;j++) {
                primes[i*j]=false;
            }
        }
    }
    for(int i=1;i<=maxNumb;i++){
      if(primes[i]){
        primeTill10K.add(i+0L);
      }
    }
  }
  
  public String decyphre(long []cypher){
    //find smallest number
    int smalIndex = -1;
    long smallestNumber = Integer.MAX_VALUE;
    for(int i=1;i<cypher.length-1;i++){
      long curLong = cypher[i];
      if((cypher[i]!=cypher[i-1]||cypher[i]!=cypher[i+1])&&curLong<smallestNumber){
        smallestNumber=curLong;
        smalIndex=i;
      }
    }
    if(cypher[smalIndex]>curMaxPrime*curMaxPrime){
      computeFirstKPrimes(cypher[smalIndex]/2);
    }
    long []twoPrimes = findTwoPrimes(cypher[smalIndex]);
    long []inOrderPrimes = findInOrderPrime(twoPrimes,cypher[smalIndex-1],cypher[smalIndex+1]);
    long []allPrimes = new long[cypher.length+1];
    allPrimes[smalIndex]=inOrderPrimes[0];
    allPrimes[smalIndex+1]=inOrderPrimes[1];
    for(int i=smalIndex;i<cypher.length;i++){
      allPrimes[i+1]=cypher[i]/allPrimes[i];
    }
    //backwards
    for(int i=smalIndex-1;i>=0;i--){
      allPrimes[i]=cypher[i]/allPrimes[i+1];
    }
    Map<Long,Character> primeToChar = new TreeMap<>();
    for(long oneP:allPrimes){
      primeToChar.put(oneP, '-');
    }
    char curChar='A';
    for(Entry<Long, Character> entry:primeToChar.entrySet()){
      entry.setValue(curChar++);
    }
    StringBuilder solution = new StringBuilder();
    for(long oneP:allPrimes){
      solution.append(primeToChar.get(oneP));
    }
    return solution.toString();
  }
  
  private long[] findInOrderPrime(long []twoPrimes,Long prevProduct, Long nextProduct){
    long previous = twoPrimes[0];
    long next = twoPrimes[1];
    if(divisionStillPrime(prevProduct, next) && divisionStillPrime(nextProduct, previous)){
      previous = twoPrimes[1];
      next = twoPrimes[0];
    }
    return new long[]{previous,next};
  }
  
  public boolean divisionStillPrime(long product, long prime){
    if(product%prime!=0){
      return false;
    }
    long newNumber = product/prime;
    boolean isNotPrime = primeTill10K.stream().filter(p -> p<=newNumber/2).anyMatch(p->newNumber%p==0);
    return !isNotPrime;
  }
  
  public long[] findTwoPrimes(long product){
    for(long onePrime:primeTill10K){
      if(product%onePrime==0 && primeTill10K.contains(product/onePrime)){
        return new long[]{onePrime,product/onePrime};
      }
    }
    throw new RuntimeException("Could not find two primes!");
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    Cryptopangrams sol = new Cryptopangrams();
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      in.nextLine();
      String []listNumbers = in.nextLine().split(" ");
      long []numbers = new long[listNumbers.length];
      for(int j=0;j<listNumbers.length;j++){
        numbers[j]=Long.parseLong(listNumbers[j]);
      }
      System.out.println("Case #" + i + ": " + sol.decyphre(numbers));
    }
    in.close();
  }

}
