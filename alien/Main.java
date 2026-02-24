import java.util.HashSet;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    long n = scan.nextLong();
    HashSet<Character> digits = new HashSet<>();
    String str = ""+n;
    for (char c : str.toCharArray()) {
      digits.add(c);
    }
    // impossible case
    if(str.length() >= 10 && digits.contains('0') && digits.contains('1') && digits.contains('2') && digits.contains('3') && digits.contains('4') && digits.contains('5') && digits.contains('6') && digits.contains('7') && digits.contains('8') && digits.contains('9')){
      System.out.println("Impossible");
      return;
    }

    long high = n;
    long low = n;
    int offset = 1;
    String result = "";
    while(true){
      high += offset;
      low -= offset;
      System.out.println("high: " + high + " low: " + low);
      // check low
      boolean found_low = true;
      if (low >=0) {
        for (char c : (""+low).toCharArray()) {
          if(digits.contains(c)){
            found_low = false;
            break;
          }
        }
        if(found_low){
          result += low;
        }
      }

      boolean found_high = true;
      for (char c : (""+high).toCharArray()) {
        if(digits.contains(c)){
          found_high = false;
          break;
        }
      }
      if(found_low){
        result += " ";
      }
      if(found_high){
        result += high;
      }
      //offset++;
      if(result.length() > 0){
        System.out.println(result);
        break;
      }
    }
  }
}
