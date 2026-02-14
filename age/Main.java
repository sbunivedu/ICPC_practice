// http://open.kattis.com/problems/aldursrodun
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int[] ages = new int[n];
    for(int i = 0; i < n; i++) {
      ages[i] = scan.nextInt();
    }
    int[][] matrix = new int[n][n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        matrix[i][j] = gcd(ages[i], ages[j]);
      }
    }
    /*
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }*/
    //generate all sequences
    ArrayList<int[]> permutations = permute(ages);
    for(int[] perm : permutations) {
      //System.out.println(Arrays.toString(perm));
      boolean found = true;
      for(int i = 0; i < perm.length - 1; i++) {
        if(gcd(perm[i], perm[i+1]) <= 1) {
          found = false;
          break;
        }
      }
      if (found) {
        for(int i = 0; i < perm.length; i++) {
          System.out.print(perm[i]);
          if(i != perm.length - 1) {
            System.out.print(" ");
          }
        }
        return;
      }
    }
    System.out.println("Neibb");
  }

  static int gcd(int a, int b) {
    int small, large;
    if (a > b) {
      small = b;
      large = a;
    }else {
      small = a;
      large = b;
    }
    if(large % small == 0) {
      return small;
    }else{
      return gcd(small, large % small);
    }
  }

  public static ArrayList<int[]> permute(int[] nums) {
    ArrayList<int[]> result = new ArrayList<>();
    permute_r(nums, 0, result);
    return result;
  }

  private static void permute_r(int[] nums, int start, ArrayList<int[]> result) {
    // Base case: one complete permutation
    if (start == nums.length) {
      result.add(nums.clone()); // Add a copy of the current permutation to the result list
      return;
    }

    for (int i = start; i < nums.length; i++) {
      swap(nums, start, i); // choose
      permute_r(nums, start + 1, result); // explore
      swap(nums, start, i); // un-choose (backtrack)
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
