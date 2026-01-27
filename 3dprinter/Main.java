//https://open.kattis.com/problems/3dprinter
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    if(n==1){
      System.out.println(1);
      return;
    }
    int capacity = 1;
    for (int i = 1; i <= n; i++) {
      capacity *= 2;
      if (capacity >= n) {
        System.out.println(i + 1);
        break;
      }
    }
  }
}
