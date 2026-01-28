// https://open.kattis.com/problems/a1paper

import java.util.Scanner;

public class Luke {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // Long side of A2 paper in meters: 2^(-3/4)
    double bigger = Math.pow(2, (-(double) 3 / (double) 4));

    // Scale factor for moving to next smaller paper size: 2^(-5/4) / 2^(-3/4) = 2^(-1/2)
    // Applied when moving from A2 -> A3 -> A4, etc.
    double sizeDown = (Math.pow(2, (-(double) 5 / (double) 4))) / bigger;
    // System.out.println(sizeDown);
    int smallest = scan.nextInt();  // Smallest paper size Björn has (e.g., 4 means A4)
    int i = 1;  // Current iteration, tracks which paper size we're processing
    double needed = 1;  // How many A1-equivalent sheets we need at current stage
    double tape = 0;  // Accumulates total tape length needed in meters

    // Process each paper size from A2 down to the smallest Björn has.
    // Algorithm: work backwards from A1 (need 1), doubling at each step (need 2 A2s, 4 A3s, etc.)
    while ((i < smallest) && (needed > 0)) {
      needed *= 2;  // Double papers needed (2 A2s → 1 A1, 2 A3s → 1 A2, etc.)
      int have = scan.nextInt();  // Number of sheets we have for this size
      tape += needed * bigger / 2;  // Add tape: joining N sheets requires (N-1) tapes of length = long side
      bigger *= sizeDown;  // Scale down dimensions for next smaller paper size
      needed -= have;  // Subtract what we have from what we need
      i++;
      // System.out.println(needed);
      // System.out.println(bigger);
    }
    // Check if we successfully gathered enough paper to make A1
    if (needed > 0) {
      System.out.println("impossible");
    } else {
      System.out.println(tape);
    }
  }
}
