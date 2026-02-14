import java.util.*;

/*
 * LukeRecur.java
 *
 * Recursive version of the backtracking solution for
 * the Kattis problem "aldursrodun" (Aldursröðun).
 *
 * Builds an adjacency matrix where two indices are connected when
 * their ages have gcd > 1, then performs recursive backtracking
 * (depth-first search) to find a Hamiltonian path (an ordering of
 * all indices where each adjacent pair is connected).
 */
public class LukeRecur {
  static int len;
  static int[] children;
  static int[] order;     // holds index ordering
  static boolean[] used;  // whether an index is already in `order`
  static int[][] adj;     // adjacency matrix: 1 if gcd>1, else 0

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    len = scan.nextInt();
    children = new int[len];
    for (int i = 0; i < len; i++) {
      children[i] = scan.nextInt();
      // Early exit: 1 cannot be adjacent to any other number
      if (children[i] == 1) {
        System.out.println("Neibb");
        return;
      }
    }

    order = new int[len];
    Arrays.fill(order, -1);
    used = new boolean[len];

    // Build adjacency matrix: adj[i][j] == 1 if gcd(children[i], children[j]) > 1
    adj = new int[len][len];
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len; j++) {
        if ((i != j) && GCD(children[i], children[j]) != 1) {
          adj[i][j] = 1;
          adj[j][i] = 1;
        } else {
          adj[i][j] = 0;
          adj[j][i] = 0;
        }
      }
    }

    // Try every possible starting index as the first element
    for (int start = 0; start < len; start++) {
      order[0] = start;
      used[start] = true;
      // Recurse to fill positions 1..len-1
      if (backtrack(1)) {
        // Found a valid ordering; print ages in that order
        for (int i = 0; i < len - 1; i++) {
          System.out.print(children[order[i]] + " ");
        }
        System.out.println(children[order[len - 1]]);
        return;
      }
      // Undo start choice and try next start
      used[start] = false;
      order[0] = -1;
    }

    // No ordering found
    System.out.println("Neibb");
  }

  // Recursive backtracking: try to fill position `depth`.
  // Returns true when a full ordering is found (depth == len).
  static boolean backtrack(int depth) {
    if (depth == len) {
      return true; // all positions filled successfully
    }

    int prev = order[depth - 1];
    // Try every unused index that is adjacent to `prev`
    for (int i = 0; i < len; i++) {
      if (!used[i] && adj[prev][i] == 1) {
        order[depth] = i;    // choose
        used[i] = true;
        if (backtrack(depth + 1)) { // explore
          return true; // propagate success
        }
        // un-choose (backtrack)
        used[i] = false;
        order[depth] = -1;
      }
    }
    return false; // no valid continuation from this state
  }

  // Euclidean algorithm for GCD
  public static int GCD(int a, int b) {
    do {
      int temp = b;
      b = a % b;
      a = temp;
    } while (b != 0);
    return a;
  }
}
