// runtime error

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main3 {
  private static int MAX = 1000000;
  private static long[] lengths = new long[MAX];
  
  public static void main(String[] args) throws FileNotFoundException{
    lengths[1] = 1;

    Scanner scan = new Scanner(System.in);
    //Scanner scan = new Scanner(new File("input1.txt"));
    while(scan.hasNextLine()){
      String line = scan.nextLine();
      String[] nums = line.split(" ");
      int i = Integer.parseInt(nums[0]);
      int j = Integer.parseInt(nums[1]);
      long max = 1;
      
      // go from lower bound to higher bound
      int low = i;
      int high = j;
      if(low > high){
        int tmp = low;
        low = high;
        high = tmp;
      }
      
      for(int k = low; k <= high; k++){
        long l = length(k);
        if(l > max){
          max = l; 
        }
      }
      System.out.println(i+" "+j+" "+max);
    }
  }

  private static long length(int n){
    long result;
    if(n < MAX && lengths[n] != 0){
      result = lengths[n];
    }else if(n % 2 == 1){
      result = length(n*3+1) + 1;
    }else{
      result = length(n/2) + 1;
    }

    if(n < MAX){
      lengths[n] = result;
    }
    //System.out.println("lengths["+n+"]="+result);
    return result;
  }
}