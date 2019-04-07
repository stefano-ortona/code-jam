package google.com.ortona.code_jam.y2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class ForegoneSolution {
  
  public String[] remove4(String input){
    char []first = input.toCharArray();
    char []second=null;
    int secondIndex=0;
    for(int i=0;i<first.length;i++){
      if(first[i]=='4'){
        if(second==null){
          second=new char[first.length-i];
          Arrays.fill(second, '0');
        }
        second[secondIndex]='2';
        first[i]='2';
      }
      if(second!=null){
        secondIndex++;
      }
    }
    return new String[]{new String(first),new String(second)};
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    ForegoneSolution sol = new ForegoneSolution();
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      String number = in.nextLine();
      String []curSol = sol.remove4(number);
      System.out.println("Case #" + i + ": " + curSol[0] + " " + curSol[1]);
    }
    in.close();
  }

}
