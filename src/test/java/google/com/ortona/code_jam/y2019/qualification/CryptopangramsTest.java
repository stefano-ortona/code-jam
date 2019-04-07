package google.com.ortona.code_jam.y2019.qualification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class CryptopangramsTest {
  private static Set<Long> primeTill10K=null;

  @BeforeClass
  public static void computeFirstKPrimes(){
    primeTill10K = new TreeSet<>();
    boolean[] primes=new boolean[((int) 10000)+1];
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
    for(int i=1;i<=10000;i++){
      if(primes[i]){
        primeTill10K.add(i+0L);
      }
    }
  }


  @Test
  public void test(){
    while(true){
      String input = generateRandomString();
      System.out.println(input);
      Cryptopangrams cr = new Cryptopangrams();
      Assert.assertEquals(input,cr.decyphre(encrypt(input)));
    }
  }
  
  @Test
  public void singleTest(){
    String input = "CACAUKCTGGBJGMTYYDLAKJPSCFJCENCKCJWFXGCHORZCTIATKYQZTYQNUTOKAV";
    Cryptopangrams cr = new Cryptopangrams();
    Assert.assertEquals(input,cr.decyphre(encrypt(input)));
  }

  private String generateRandomString(){
    Set<Character> used = new HashSet<>();
    Random r = new Random();
    char firstChar='A';
    StringBuilder s = new StringBuilder();
    while(used.size()<26){
      char next = (char) (firstChar+r.nextInt(26));
      s.append(next);
      used.add(next);
    }
    return s.toString();
  }


  private long[] encrypt(String input){
    Map<Character,Long> primesToUSe = chooseRandomPrime();
    long []encrypted = new long[input.length()-1];
    char previousChar = input.charAt(0);
    for(int i=1;i<input.length();i++){
      encrypted[i-1]=primesToUSe.get(previousChar)*primesToUSe.get(input.charAt(i));
      previousChar=input.charAt(i);
    }
    return encrypted;
  }

  private Map<Character,Long> chooseRandomPrime(){
    List<Long> allPrimes = new ArrayList<>();
    allPrimes.addAll(primeTill10K);
    Set<Long> chosenPrime = new TreeSet<>();
    Random r = new Random();
    while(chosenPrime.size()<26){
      int nextindex = r.nextInt(allPrimes.size());
      long nextPrime = allPrimes.get(nextindex);
      chosenPrime.add(nextPrime);
    }
    char curChar = 'A';
    Map<Character,Long> char2prime = new HashMap<>();
    for(Long oneP:chosenPrime){
      char2prime.put(curChar++, oneP);
    }
    return char2prime;
  }

}
