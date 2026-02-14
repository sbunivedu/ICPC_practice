import java.util.Scanner;

/*
 * Luke.java
 *
 * Solves the "Aldursröðun" problem (Kattis: aldursrodun).
 * Given a list of ages (integers), find an ordering of the children
 * such that every adjacent pair has gcd > 1. If no such ordering exists,
 * print "Neibb".
 *
 * This implementation builds an adjacency matrix where two nodes are
 * connected if their ages share a common factor > 1, then searches for
 * a Hamiltonian path using iterative backtracking over indices.
 */
public class Luke {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    // Number of children / nodes
    int len = scan.nextInt();

    // ages of the children
    int[] children = new int[len];

    // order will hold indices into `children`; -1 means unfilled
    int[] order = new int[len];

    // used[i] == 1 means index i is already placed in the current partial ordering
    int[] used = new int[len];

    // Read input and initialize helper arrays
    for (int i = 0; i < len; i++) {
      children[i] = scan.nextInt();
      order[i] = -1; // mark position as empty
      used[i] = 0; // not used yet

      // Early exit optimization: an age of 1 cannot be adjacent to anyone
      // because gcd(1, x) == 1 for all x. So no valid ordering exists.
      if (children[i] == 1) {
        System.out.println("Neibb");
        System.exit(0);
      }
    }

    // Build adjacency matrix: adj[i][j] = 1 when gcd(children[i], children[j]) > 1
    int[][] adj = new int[len][len];
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

    // Iterative backtracking state:
    // done: whether we found a full ordering
    // counter: candidate index we're currently testing for placement
    // counter1: current depth / next free position in `order`
    boolean done = false;
    int counter = 0;
    int counter1 = 0;

    // Main backtracking loop: try to fill `order` from position 0..len-1
    while (!done) {
      /*
       * The following block implements choose/explore/un-choose iteratively.
       * It mirrors a recursive backtracking approach but uses explicit
       * indices and arrays to manage the stack/state.
       */
      if (used[counter] == 0) {
        // If candidate `counter` is unused, try to place it at current position
        if (counter1 == 0) {
          // placing the first element (no adjacency check needed)
          order[counter1] = counter;
          used[counter] = 1;
          counter1++;
          counter = 0; // reset candidate pointer for next position
        } else {
          // For subsequent positions, ensure adjacency with previous element
          if (adj[order[counter1 - 1]][counter] == 1) {
            order[counter1] = counter;
            used[counter] = 1;
            counter1++;
            counter = 0; // reset candidate pointer for next depth
          } else {
            // candidate not adjacent, try next candidate
            counter++;

            // If we've exhausted candidates for this position, backtrack
            while (counter == len) {
              counter1--; // back up one position
              if (counter1 < 0) {
                // tried all possibilities from the root — no solution
                System.out.println("Neibb");
                System.exit(0);
              }
              // restore state: mark previously chosen index as unused
              counter = order[counter1];
              used[counter] = 0;
              counter++; // move to next candidate after the restored one
            }
          }
        }
      } else {
        // candidate already used; skip it
        counter++;
        // If we've exhausted candidates, backtrack similarly
        while ((order[len - 1] == -1) && (counter == len)) {
          counter1--;
          if (counter1 < 0) {
            System.out.println("Neibb");
            System.exit(0);
          }
          counter = order[counter1];
          used[counter] = 0;
          counter++;
        }
      }

      // If last position is filled, we've found a complete ordering
      if (order[len - 1] != -1) {
        done = true;
      }
    }

    // Print ages according to the found index ordering
    for (int i = 0; i < len - 1; i++) {
      System.out.print(children[order[i]] + " ");
    }
    System.out.println(children[order[len - 1]]);
  }

  // Euclidean algorithm for GCD (iterative)
  public static int GCD(int a, int b) {
    do {
      int temp = b;
      b = a % b;
      a = temp;
    } while (b != 0);
    return a;
  }
}
