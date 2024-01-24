// Uva 10038 - Jolly Jumpers
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws FileNotFoundException{
    //Scanner scan = new Scanner(new File("input3.txt"));
    Scanner scan = new Scanner(System.in);
    nextcase:
    while(scan.hasNext()){
      String[] nums = scan.nextLine().split(" ");
      //System.out.println(Arrays.toString(nums));
      int count = Integer.parseInt(nums[0]);
      if(count == 1){
        System.out.println("Jolly");
        continue nextcase;
      }
      boolean[] differences = new boolean[count];
      for(int i=1; i<count; i++){
        differences[i] = false;
      }
      //System.out.println(Arrays.toString(differences));
      
      // whether absolute differences from 1 to length -1 are present
      for(int i=1; i<count; i++){
        int diff = Math.abs(Integer.parseInt(nums[i])-
                            Integer.parseInt(nums[i+1]));
        //System.out.println(diff);
        if(diff<=count-1){
          differences[diff] = true;
        }else{
          System.out.println("Not jolly");
          continue nextcase;
        }
      }
      //System.out.println(Arrays.toString(differences));
      for(int i=1; i<=count-1; i++){
        if(differences[i] == false){
          System.out.println("Not jolly");
          continue nextcase;
        }
      }
      System.out.println("Jolly");
    }
    System.out.println();
  }
}