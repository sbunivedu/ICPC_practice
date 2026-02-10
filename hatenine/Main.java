import java.util.Scanner;

public class Main {
// Initialize result = 1 and base = x.
// While exponent > 0:
// If exponent is odd,
// multiply result by base.
// Square the base (\(base=base\cdot base\)).Divide exponent by 2 (integer division).
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int T = scan.nextInt();
    for(int i = 0; i < T; i++) {
      long d = scan.nextLong();

      // compute 9^(d-1) mod 1000000007
      long result = 1;
      long base = 9;
      long exponent = d - 1;
      //System.out.println("exponent: " + exponent);
      while(exponent > 0) {
        if(exponent % 2 == 1) {
          result *= base;
          result %= 1000000007;
        }
        base *= base;
        base %= 1000000007;
        exponent /= 2;
        //System.out.println("base: " + base);
        //System.out.println("exponent: " + exponent);
        //System.out.println("result: " + result);
      }
      result = (result * 8) % 1000000007;
      System.out.println(result);
    }
  }
}
