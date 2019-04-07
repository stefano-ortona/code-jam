package google.com.ortona.code_jam.y2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class YouCanGoOnYourOwnWay {
  
  public String findPath(int n, String path){
    char []newPath = new char[path.length()];
    Arrays.fill(newPath, 'E');
    for(int i=0;i<path.length();i++){
      if(path.charAt(i)=='E'){
        newPath[i]='S';
      } 
    }
    return new String(newPath);
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    YouCanGoOnYourOwnWay sol = new YouCanGoOnYourOwnWay();
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      in.nextLine();
      String number = in.nextLine();
      String curSol = sol.findPath(n, number);
      System.out.println("Case #" + i + ": " + curSol);
    }
    in.close();
  }
  
}
