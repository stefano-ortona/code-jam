package google.com.ortona.code_jam.y2019.qualification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DatBae {
  Scanner in;

  public DatBae(){
    in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
  }
  
  public DatBae(Scanner in){
    this.in = in;
  }

  public String findBrokenServer(int length, int numBroken, int maxAttempts){
    Set<Integer> broken = new TreeSet<>();
    Interval initialInterval = new Interval(0, length);
    initialInterval.projectedStart=0;
    initialInterval.projectedEnd=length-numBroken;
    List<Interval> curIntervals = Arrays.asList(initialInterval);
    while(!curIntervals.isEmpty()){
      String req = buildRequest(curIntervals, length);
      String response = getResponse(req);
      List<Interval> nextIntervals = new ArrayList<>();
      for(Interval oneInt:curIntervals){
        Interval []split = analyze(oneInt, broken, req, response);
        if(split[0]!=null){
          nextIntervals.add(split[0]);
          if(split[1]!=null){
            nextIntervals.add(split[1]);
          }
        }
      }
      curIntervals=nextIntervals;
    }
    StringBuilder brokenServer = new StringBuilder();
    broken.forEach(b -> brokenServer.append(b+" "));
    return brokenServer.substring(0,brokenServer.length()-1);
  }


  
  private String buildRequest(List<Interval> intervals,int length){
    StringBuilder request = new StringBuilder();
    int previousStart=0;
    for(Interval interval:intervals){
      fillWithZero(request, previousStart, interval.start);
      fillInterval(request, interval);
      previousStart=interval.end;
    }
    //fill last bit
    fillWithZero(request, previousStart, length);    
    return request.toString();
  }
  
  private void fillInterval(StringBuilder builder,Interval interval){
    //fill zeros
    for(int i=interval.start;i<interval.start+(interval.end-interval.start)/2;i++){
      builder.append('0');
    }
    //fill ones
    for(int i=interval.start+(interval.end-interval.start)/2;i<interval.end;i++){
      builder.append('1');
    }
  }
  
  private void fillWithZero(StringBuilder builder,int start, int end){
    for(int i=start;i<end;i++){
      builder.append('0');
    }
  }

  private String getResponse(String request){
    System.out.println(request);
    return in.nextLine();
  }

  private Interval[] analyze(Interval current, Set<Integer> broken, String original, String response){
    if(current.projectedEnd-current.projectedStart==current.end-current.start){
      //interval is not broken
      return new Interval[]{};
    }
    //count zeroS and 1s
    Interval[] toReturn = new Interval[2];
    int zeroS=0;
    int oneS=0;
    for(int i=current.projectedStart;i<current.projectedEnd;i++){
      if(response.charAt(i)=='0'){
        zeroS++;
      } else {
        oneS++;
      }
    }
    if(zeroS==0){
      //all zeros are broken
      for(int i=current.start;i<current.start+(current.end-current.start)/2;i++){
        broken.add(i);
      }
    } else {
      if(zeroS<((current.end-current.start)/2)){
        //there are some broken zeros, create a new interval
        Interval newInt = new Interval(current.start,(current.end-current.start)/2+current.start);
        newInt.projectedStart=current.projectedStart;
        newInt.projectedEnd=current.projectedStart+zeroS;
        toReturn[0]=newInt;
      }
    }
    if(oneS==0){
      //all ones are broken
      for(int i=current.start+(current.end-current.start)/2;i<current.end;i++){
        broken.add(i);
      }
    } else {
      if(oneS<((current.end-current.start)-(current.end-current.start)/2)){
        //there are missing ones, create new interval
        Interval newInt = new Interval(current.start+(current.end-current.start)/2,current.end);
        newInt.projectedStart=current.projectedStart+zeroS;
        newInt.projectedEnd=newInt.projectedStart+oneS;  
        int index = toReturn[0]==null ? 0 : 1;
        toReturn[index]=newInt;

      }
    }
    return toReturn;
  }


  private class Interval{
    public Interval(int start, int end){
      this.start=start;
      this.end=end;
    }
    private int start;
    private int end;
    private int projectedStart;
    private int projectedEnd;
    public String toString(){
      return "("+start+","+end+")";
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    DatBae sol = new DatBae(in);
    in.nextLine();
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      int b = in.nextInt();
      int f = in.nextInt();
      in.nextLine();
      String response = sol.findBrokenServer(n, b, f);
      System.out.println(response);
      String line = in.nextLine();
      if(!line.contains("1")){
        throw new RuntimeException("Error!");
      }
    }
    in.close();
  }


}
