// 10038
// from https://github.com/jcbages/uva-online-judge-solutions/blob/main/Volume%20100/10038%20-%20Jolly%20Jumpers.java

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main1 {
	public static void main(String... args) /*throws FileNotFoundException */{
		Scanner sc = new Scanner(System.in);
    //Scanner sc = new Scanner(new File("input3.txt"));
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			if (n == 1) {
				System.out.println("Jolly");
				continue;
			}

			boolean[] nums = new boolean[n - 1];
			for (int i = 0; i < n - 1; i++) {
				int x = Math.abs(arr[i] - arr[i + 1]) - 1;
				if (0 <= x && x < nums.length) nums[x] = true;
			}

			boolean valid = true;
			for (boolean b : nums) {
				valid = valid && b;
			}
			System.out.println(valid ? "Jolly" : "Not jolly");
		}
	}
}