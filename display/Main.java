// https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&category=0&problem=647&mosmsg=Submission+received+with+ID+28006961
// https://www.udebug.com/UVa/706

import java.io.*;
import java.util.*;
import java.util.Arrays;

class Main {
  private static int size;
  
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("input1.txt"));
//    System.out.println(Arrays.toString(digit2array(1)));
//    System.out.println(Arrays.toString(pattern2line('B', 6)));

    //Scanner scan = new Scanner(System.in);
    size = scan.nextInt();
    String num_string = scan.next();
    int number = Integer.parseInt(num_string);
    while(!(size==0 && number==0)){
      //System.out.println("size:"+size+" number:"+number);
      // count number of digits
      //System.out.println("num_string:"+num_string+"*");
      int num_digits = num_string.length();

      //System.out.println("num_digits="+num_digits);
  
      // create a digit array
      int[] digits = new int[num_digits];
      for(int i=num_digits-1; i>=0; i--){
        int remainder = number%10;
        number /= 10;
        digits[i]=remainder;
      }

      //System.out.println("digits:"+Arrays.toString(digits));
      
      // create display array
      int cols = (size+2)*num_digits+(num_digits-1); // one empty column between adjacent digits
      int rows = size*2 + 3;
      char[][] display_array = new char[rows][cols];

      // fill display array with spaces
      for(int row=0; row<rows; row++){
        for(int col=0; col<cols; col++){
          display_array[row][col] = ' ';
        }
      }

      for(int i=0; i<num_digits; i++){ //for each digit
        //System.out.println("digit:"+digits[i]);
        int width = size+2;
        int height = size*2 + 3;
        draw_digit(display_array, digits[i], width, height, i*(width+1));
      }

      // output display array
      for(int row=0; row<rows; row++){
        for(int col=0; col<cols; col++){
          System.out.print(display_array[row][col]);
        }
        System.out.println();
      }
      
      // print a blank line after each number
      System.out.println();
      
      size = scan.nextInt();
      num_string = scan.nextLine().trim();
      number = Integer.parseInt(num_string);  
    }
  }

  private static void draw_digit(char[][] display_array, int digit, int width, int height, int start_col){
    char[] line_patterns = digit2array(digit);
    int row = 0;
    int col = start_col;
    for(int i=0; i<5; i++){
      char[] line = pattern2line(line_patterns[i], width);
      int repeat = 1;
      if(i%2 == 1){
        repeat = size;
      }
      for(int j=0; j<repeat; j++){
        for(int k=0; k<line.length; k++){
          display_array[row][col] = line[k];
          col++;
        }
        row++;
        col=start_col;
      }
    }
  }
  
  private static char[] digit2array(int digit){
    // each char in the return array represents the pattern of a line
    // in the digit pattern
    // -: horizontal line
    // L: left vertical bar
    // R: right vertical bar
    // B: left and right vertical bar
    // space: empty line
    char[] result = null;
    switch(digit){
      case 0:
        result = new char[]{'-','B',' ','B','-'};
        break;
      case 1:
        result = new char[]{' ','R',' ','R',' '};
        break;
      case 2: 
        result = new char[]{'-','R','-','L','-'};
        break;
      case 3:
        result = new char[]{'-','R','-','R','-'};
        break;
      case 4:
        result = new char[]{' ','B','-','R',' '};
        break;
      case 5:
        result = new char[]{'-','L','-','R','-'};
        break;
      case 6:
        result = new char[]{'-','L','-','B','-'};
        break;
      case 7:
        result = new char[]{'-','R',' ','R',' '};
        break;
      case 8:
        result = new char[]{'-','B','-','B','-'};
        break;
      case 9:
        result = new char[]{'-','B','-','R','-'};
        break;
    }
    return result;
  }

  private static char[] pattern2line(char pattern, int length){
    char[] array = new char[length];
    for(int i=0; i<length; i++){
      array[i] = ' ';
    }
    switch(pattern){
      case '-':
        for(int i=1; i<length-1; i++){
          array[i] = '-';
        }
        break;
      case 'L':
        array[0] = '|';
        break;
      case 'R':
        array[length-1] = '|';
        break;
      case 'B':
        array[0] = '|';
        array[length-1] = '|';
        break;
    }
    return array;
  }
}