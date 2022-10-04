// accepted on https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=36
import java.io.*;
import java.util.*;

class Main {
  static int cache_size = 1000000;
  static int[] cache = new int[cache_size];
//  static int max_n = 0;
//  static long max_length = 0;

  public static int cycle_length(int n){
 //   if(n>max_n){
 //     max_n = n;
 //   }
    int count;
    if(n < cache_size && cache[n] != 0){
      return cache[n];
    }
    if(n%2 == 0){
      count = cycle_length(n/2)+1;
    }else{
      count = cycle_length(3*n+1)+1;
    }
    if(n < cache_size){
      cache[n] = count;
    }
//    if(count > max_length){
//      max_length = count;
//    }
    return count;
  }

  public static void main(String[] args) throws FileNotFoundException{
    cache[1] = 1;

    Scanner scan = new Scanner(new File("input.txt"));
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
    //System.out.println("max_n="+max_n);
    //System.out.println("max_length="+max_length);
  }
}