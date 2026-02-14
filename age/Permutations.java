import java.util.*;

public class Permutations {

  public static void permute(int[] nums) {
    permute_r(nums, 0);
  }

  private static void permute_r(int[] nums, int start) {
    // Base case: one complete permutation
    if (start == nums.length) {
      System.out.println(Arrays.toString(nums));
      return;
    }

    for (int i = start; i < nums.length; i++) {
      swap(nums, start, i); // choose
      permute_r(nums, start + 1); // explore
      swap(nums, start, i); // un-choose (backtrack)
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public static void main(String[] args) {
    int[] nums = { 1, 2, 3 };
    permute(nums);
  }
}
