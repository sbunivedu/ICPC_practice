// https://open.kattis.com/problems/8queens
import java.util.Arrays;
import java.util.Scanner;

public class queens {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[8][8];
    for(int i = 0; i < 8; i++) {
      String line = scan.nextLine();
      for(int j = 0; j < 8; j++) {
        if(line.charAt(j) == '*') {
          board[i][j] = 1;
        }else{
          board[i][j] = 0;
        }
      }
    }
    int[][] queens = new int[8][2];
    int index = 0;
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++) {
        if(board[i][j] == 1) {
          queens[index][0] = i;
          queens[index][1] = j;
          index++;
        }
      }
    }
    for(int i = 0; i < queens.length-1; i++) {
      //System.out.println(Arrays.toString(queens[i]));
      for(int j = i + 1; j < queens.length; j++) {
        if(queens[i][0] == queens[j][0]   //same row
          || queens[i][1] == queens[j][1] //same col
          || Math.abs(queens[i][0] - queens[j][0]) // diangonal
             == Math.abs(queens[i][1] - queens[j][1])) {
          System.out.println("invalid");
          return;
        }
      }
    }
    System.out.println("valid");

    scan.close();
  }
}
