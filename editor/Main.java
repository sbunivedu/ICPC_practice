// https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1208
// http://www.udebug.com/UVa/10267

import java.io.*;
import java.util.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("input1.txt"));
    //Scanner scan = new Scanner(System.in);
    char[][] image = null;
    int rows = 0;
    int cols = 0;
    String[] parts = scan.nextLine().split("\\s+");
    String command = parts[0];
    while (!command.equals("X")) {
      //System.out.println(Arrays.toString(parts));
      if (command.equals("I")) { // create image
        // I M N
        // create M (cols) x N (rows) image array
        cols = Integer.parseInt(parts[1]);
        rows = Integer.parseInt(parts[2]);
        //System.out.println("rows:"+rows);
        //System.out.println("cols:"+cols);
        image = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
          for (int col = 0; col < cols; col++) {
            image[row][col] = 'O';
          }
        }
        // printArray(image);
      } else if (command.equals("C")) { // clear image
        for (int row = 0; row < rows; row++) {
          for (int col = 0; col < cols; col++) {
            image[row][col] = 'O'; // white
          }
        }
      } else if (command.equals("L")) { // color pixel
        // L X Y C
        int x = Integer.parseInt(parts[1])-1;
        int y = Integer.parseInt(parts[2])-1;
        char color = parts[3].charAt(0);
        image[y][x] = color;
        //printArray(image);
      } else if (command.equals("V")) { // draw vertical line
        // V X Y1 Y2 C
        int x = Integer.parseInt(parts[1])-1;
        int y1 = Integer.parseInt(parts[2])-1;
        int y2 = Integer.parseInt(parts[3])-1;
        char color = parts[4].charAt(0);
        for (int y = Math.min(y1,y2); y <= Math.max(y1,y2); y++) {
          image[y][x] = color;
        }
      } else if (command.equals("H")) { // draw horizontal line
        // H X1 X2 Y C
        int x1 = Integer.parseInt(parts[1])-1;
        int x2 = Integer.parseInt(parts[2])-1;
        int y = Integer.parseInt(parts[3])-1;
        char color = parts[4].charAt(0);
        for (int x = Math.min(x1,x2); x <= Math.max(x1,x2); x++) {
          image[y][x] = color;
        }
      } else if (command.equals("K")) { // draw rectangle
        // K X1 Y1 X2 Y2 C
        int x1 = Integer.parseInt(parts[1])-1;
        int y1 = Integer.parseInt(parts[2])-1;
        int x2 = Integer.parseInt(parts[3])-1;
        int y2 = Integer.parseInt(parts[4])-1;
        char color = parts[5].charAt(0);
        for (int y = Math.min(y1,y2); y <= Math.max(y1,y2); y++) {
          for (int x = Math.min(x1,x2); x <= Math.max(x1,x2); x++) {
            image[y][x] = color;
          }
        }
      } else if (command.equals("F")) { // fill a region
        // F X Y C
        int x = Integer.parseInt(parts[1])-1;
        int y = Integer.parseInt(parts[2])-1;
        char new_color = parts[3].charAt(0);
        char old_color = image[y][x];
        colorPixel(image, rows, cols, y, x, old_color, new_color);
      } else if (command.equals("S")) { // print image file
        String file_name = parts[1];
        System.out.println(file_name);
        printArray(image);
      }
      parts = scan.nextLine().split("\\s+");
      command = parts[0];
    }
  }

  private static void colorPixel(char[][] image, int rows, int cols, int row, int col, char oldColor, char newColor) {
    // with bounds?
    if (row >= 0 && row < rows && col >= 0 && col < cols) {
      if (image[row][col] != oldColor || oldColor == newColor) {
        return;
      }else{
        image[row][col] = newColor;
        // north neighbor
        colorPixel(image, rows, cols, row - 1, col, oldColor, newColor);
        // south enighbor
        colorPixel(image, rows, cols, row + 1, col, oldColor, newColor);
        // west neighbor
        colorPixel(image, rows, cols, row, col - 1, oldColor, newColor);
        // east neighbor
        colorPixel(image, rows, cols, row, col + 1, oldColor, newColor);
      }
    }
  }

  private static void printArray(char[][] image){
    int rows = image.length;
    int cols = image[0].length;
    for(int row=0; row<rows; row++){
      for(int col=0; col<cols; col++){
        System.out.print(image[row][col]);
      }
      System.out.println();
    }
  }
}
