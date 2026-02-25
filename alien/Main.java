import java.util.HashSet;
import java.util.Scanner;

public class Main {
  static boolean isAlien(long num, HashSet<Character> digits) {
    for (char c : ("" + num).toCharArray()) {
      if (digits.contains(c)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    long n = scan.nextLong();
    HashSet<Character> digits = new HashSet<>();
    String str = "" + n;
    for (char c : str.toCharArray()) {
      digits.add(c);
    }

    // impossible case: if n contains all 10 digits, no alien exists
    if (digits.size() == 10) {
      System.out.println("Impossible");
      return;
    }

    long low = n - 1;
    long high = n + 1;

    // Find closest alien numbers
    while (low >= 0 || high >= 0) {
      boolean foundLow = false;
      boolean foundHigh = false;

      if (low >= 0 && isAlien(low, digits)) {
        foundLow = true;
      }

      if (high >= 0 && isAlien(high, digits)) {
        foundHigh = true;
      }

      if (foundLow && foundHigh) {
        System.out.println(low + " " + high);
        return;
      } else if (foundLow) {
        System.out.println(low);
        return;
      } else if (foundHigh) {
        System.out.println(high);
        return;
      }

      low--;
      high++;
    }

    System.out.println("Impossible");
  }
}
