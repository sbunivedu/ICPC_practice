// non-recursive/iterative solution
// passes given test cases
// runtime error on onlinejudge.org

import java.io.*;
import java.util.*;

class Main1 {
  static int max_n = 0;

  public static void main(String[] args) throws FileNotFoundException{
    //Scanner scan = new Scanner(new File("input4.txt"));
    Scanner scan = new Scanner(System.in);
    while(scan.hasNext()){
      int first = scan.nextInt();
      int min = first;
      int second = scan.nextInt();
      int max = second;
      //System.out.println(first+" "+second);
      if(min > max){ // swap i and j as needed
        int tmp = min;
        min = max;
        max = tmp;
      }

      long longest = 1;
      for(int i=min; i<=max; i++){ // inclusive
        //System.out.println("i:"+i);
        int count = 1;
        long n = i; // will overflow int
        while(n != 1){
          if(n%2 == 0){ //n is even
            n = n/2;
          }else{ //n is odd 
            n = n*3 + 1;
          }
          //System.out.println("n:"+n);
          count++;
        }
        //System.out.println("length:"+count);
        if(count > longest){
          longest = count;
        }
      }
      System.out.println(first+" "+second+" "+longest);
    }
  }
}