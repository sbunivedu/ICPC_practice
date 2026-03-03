import java.util.*;

public class Main1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    if (!scanner.hasNext()) {
      return;
    }

    String nStr = scanner.next();
    long n = Long.parseLong(nStr);
    int L = nStr.length();

    // Find all unique digits used in N
    boolean[] used = new boolean[10];
    int uniqueCount = 0;
    for (int i = 0; i < L; i++) {
      int digit = nStr.charAt(i) - '0';
      if (!used[digit]) {
        used[digit] = true;
        uniqueCount++;
      }
    }

    // If N uses all 10 digits, we cannot form any alien integer
    if (uniqueCount == 10) {
      System.out.println("Impossible");
      return;
    }

    // Gather all valid 'alien' digits
    List<Integer> d = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      if (!used[i]) {
        d.add(i);
      }
    }

    int minD = d.get(0);
    int maxD = d.get(d.size() - 1);

    List<Long> candidates = new ArrayList<>();

    // 1. Candidate with L - 1 digits (The largest possible smaller-length number)
    if (L > 1) {
      if (maxD > 0) {
        candidates.add(Long.parseLong(repeatDigit(maxD, L - 1)));
      } else if (d.contains(0)) {
        candidates.add(0L);
      }

      // 123 d= 0, 4, 5, 6, 7, 8, 9
      // maxD = 9, minD = 0
      // L = 3
      // Candidate with L-1 digits: 99 (maxD repeated L-1 times) and 0 (if maxD is 0, we can only use 0 if it's in d)
    }

    // 2. Candidate with L + 1 digits (The smallest possible larger-length number)
    int minNz = -1;
    for (int x : d) {
      if (x > 0) {
        minNz = x;
        break;
      }
    }
    if (minNz != -1) {
      candidates.add(Long.parseLong(minNz + repeatDigit(minD, L)));
    }

    // 3. Candidates with exactly L digits
    int n1 = nStr.charAt(0) - '0';
    for (int x : d) {
      // A number with more than 1 digit cannot start with 0
      if (x == 0 && L > 1) {
        continue;
      }

      // If the starting digit is smaller than N's starting digit
      if (x < n1) {
        candidates.add(Long.parseLong(x + repeatDigit(maxD, L - 1)));
      }
      // If the starting digit is larger than N's starting digit
      else if (x > n1) {
        candidates.add(Long.parseLong(x + repeatDigit(minD, L - 1)));
      }
    }

    // Find the candidate(s) with the minimum absolute difference
    long bestDiff = Long.MAX_VALUE;
    // TreeSet automatically sorts and de-duplicates the candidates
    TreeSet<Long> bestCands = new TreeSet<>();

    for (long cand : candidates) {
      long diff = Math.abs(cand - n);
      if (diff < bestDiff) {
        bestDiff = diff;
        bestCands.clear();
        bestCands.add(cand);
      } else if (diff == bestDiff) {
        bestCands.add(cand);
      }
    }

    // Build the output string
    StringBuilder output = new StringBuilder();
    for (long cand : bestCands) {
      if (output.length() > 0) {
        output.append(" ");
      }
      output.append(cand);
    }

    System.out.println(output.toString());
    scanner.close();
  }

  // Helper method to repeat a digit a specific number of times
  private static String repeatDigit(int digit, int times) {
    StringBuilder sb = new StringBuilder(times);
    for (int i = 0; i < times; i++) {
      sb.append(digit);
    }
    return sb.toString();
  }
}