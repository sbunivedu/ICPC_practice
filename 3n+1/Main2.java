// recursive with no caching
// passes given test cases
// runtime error on onlinejudge.org

import java.io.*;
import java.util.*;

class Main2 {
  public static long cycle_length(int n){
    long count;
    if(n == 1){
      return 1;
    }else if(n%2 == 0){
      count = cycle_length(n/2)+1;
    }else{
      count = cycle_length(3*n+1)+1;
    }
    return count;
  }

  public static void main(String[] args) throws FileNotFoundException{
    Scanner scan = new Scanner(new File("input1.txt"));
    //Scanner scan = new Scanner(System.in);
    while(scan.hasNext()){
      int first = scan.nextInt();
      int min = first;
      int second = scan.nextInt();
      int max = second;
      //System.out.println(first+" "+second);
      if(min > max){
        int tmp = min;
        min = max;
        max = tmp;
      }

      long longest = 1;
      for(int i=min; i<=max; i++){
        long count = cycle_length(i); 
        if(count > longest){
          longest = count;
        }
      }
      System.out.println(first+" "+second+" "+longest);
    }
  }
}