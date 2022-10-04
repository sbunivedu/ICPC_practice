// runtime error

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main4 {
  private static int MAX = 1000000;
  private static int[] lengths = new int[MAX];
  
  public static void main(String[] args) throws FileNotFoundException{
    lengths[1] = 1;

    //Scanner scan = new Scanner(System.in);
    Scanner scan = new Scanner(new File("input1.txt"));
    while(scan.hasNext()){
      int i = scan.nextInt();
      int j = scan.nextInt();
      
      // go from lower bound to higher bound
      int low = i;
      int high = j;
      if(low > high){
        int tmp = low;
        low = high;
        high = tmp;
      }
      
      int max = 1;
      for(int k = low; k <= high; k++){
        int l = length(k);
        if(l > max){
          max = l; 
        }
      }
      System.out.println(i+" "+j+" "+max);
    }
  }

  private static int length(int n){
    int result;
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