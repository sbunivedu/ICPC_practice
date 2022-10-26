// PC/UVa IDs: 110102/10189
// accepted on https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=29&page=show_problem&problem=1130

import java.io.*;
import java.util.*;

class Main {
  private static int rows;
  private static int cols;
  private static char[][] field;

  public static void main(String[] args) throws FileNotFoundException {
    //Scanner scan = new Scanner(new File("input1.txt"));
    Scanner scan = new Scanner(System.in);
    int field_index = 1;

    while (scan.hasNext()) {
      rows = scan.nextInt();
      cols = scan.nextInt();
      if (rows == 0 && cols == 0) {
        break;
      }else if(field_index > 1){
        System.out.println(); // linebreak after each case, except for the last case
      }
      scan.nextLine();
      field = new char[rows][cols];
      char[][] output = new char[rows][cols];
      for (int row = 0; row < rows; row++) {
        String line = scan.nextLine();
        for (int col = 0; col < cols; col++) {
          char next = line.charAt(col);
          field[row][col] = next;
          output[row][col] = next;
        }
      }
      
      System.out.println("Field #"+field_index+":");
      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          if (field[row][col] != '*') {
            int count = mineCount(row - 1, col); // N
            count += mineCount(row + 1, col); // S
            count += mineCount(row, col - 1); // W
            count += mineCount(row, col + 1); // E
            count += mineCount(row - 1, col + 1); // NE
            count += mineCount(row + 1, col + 1); // SE
            count += mineCount(row - 1, col - 1); // NW
            count += mineCount(row + 1, col - 1); // SW
            System.out.print(count);
          } else {
            System.out.print("*");
          }
        }
        System.out.println();
      }
      field_index++;
    }   
  }  
  
  private static int mineCount(int row, int col){
    int result = 0;
    if(row>=0 && row<rows && col>=0 && col<cols){
      if(field[row][col] == '*'){
        result = 1;
      }
    }
    return result;
  } 
}            