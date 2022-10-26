// 10137
// accepted on onlinejudge.org

import java.io.*;
import java.util.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    //Scanner scan = new Scanner(new File("input4.txt"));
    Scanner scan = new Scanner(System.in);
    int num_students;
    while ((num_students = scan.nextInt()) != 0) {
      // remove line break
      scan.nextLine();
      
      int total_cents = 0;
      int[] costs = new int[num_students];
      for (int i = 0; i < num_students; i++) {
        String line = scan.nextLine();
        //System.out.println("line:"+line);
        String[] parts = line.trim().split("\\.");
        //System.out.println("parts:"+Arrays.toString(parts));
        costs[i] = Integer.parseInt(parts[0])*100+Integer.parseInt(parts[1]);
        total_cents += costs[i];
      }
      int average = total_cents / num_students;
      int remainder = total_cents % num_students;
      //System.out.println("average:" + average);
      //System.out.println("remainder:" + remainder);

      // sort the cost array
      Arrays.sort(costs);
      //System.out.println("costs:"+Arrays.toString(costs));

      int[] shares = new int[num_students];
      for (int i = 0; i < num_students; i++) {
        shares[i] = average;
      }

      // distribute the remainder
      for (int i = num_students-1; i >= 0; i--) {
        if(remainder != 0){
          shares[i] += 1;
          remainder--;
        }
      }
      //System.out.println("shares:"+Arrays.toString(shares));
      
      int total_change = 0;
      for (int i = 0; i < num_students; i++) {
        if(costs[i] > shares[i]){
          total_change += costs[i] - shares[i];
        }
      }
      //System.out.println("total change:"+total_change);

      // format output
      int dollars = total_change / 100;
      int cents = total_change % 100;
      System.out.println("$" + dollars + "." + (cents > 9 ? cents : ("0" + cents))); // show double digit for fraction
                                                                                     // dollar
    }
  }
}