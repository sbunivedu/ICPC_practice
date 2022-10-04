// non-recursive/iterative solution
// passes given test cases
// runtime error on onlinejudge.org

import java.io.*;
import java.util.*;

class Main1 {
  static int max_n = 0;

  public static long cycle_length(int n){
    int count = 1;
    while(n != 1){
      if(n%2 == 0){
        n = n/2;
      }else{
        n = n*3 + 1;
      }
      count++;
    }
    return count;
  }

  public static void main(String[] args) throws FileNotFoundException{
    //Scanner scan = new Scanner(new File("input.txt"));
    Scanner scan = new Scanner(System.in);
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