import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

/*
ICPC 5237
https://icpcarchive.ecs.baylor.edu/external/52/5237.pdf
*/

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    while(n != 0){
      char[] curve = make_curve(n);
      //System.out.println(Arrays.toString(curve));

      int xMax = 0, yMax=0, xMin=0, yMin=0;
      int x=0, y=0;
      for(int i=0; i<curve.length-1; i++){
        String turn = ""+curve[i]+curve[i+1];
        if(turn.equals("ru")){
          x++;
        }else if(turn.equals("rd")){
          x++;
          y++;
        }else if(turn.equals("lu")){
          x--;
        }else if(turn.equals("ld")){
          x--;
          y++;
        }else if(turn.equals("ul")){
          x--;
          y--;
        }else if(turn.equals("ur")){
          x++;
          y--;
        }else if(turn.equals("dr")){
          x++;
        }else if(turn.equals("dl")){
          x--;
          //y++;
        }
        if(x>xMax){xMax=x;}
        if(x<xMin){xMin=x;}
        if(y>yMax){yMax=y;}
        if(y<yMin){yMin=y;}
      }
      /*
      System.out.println("xMin:"+xMin);
      System.out.println("xMax:"+xMax);
      System.out.println("yMin:"+yMin);
      System.out.println("yMax:"+yMax);
      */
      int cols = xMax-xMin+1;
      int rows = yMax-yMin+1;

      //System.out.println("cols:"+cols);
      //System.out.println("rows:"+rows);
      char[] grid = new char[cols*rows];
      for(int i=0; i<grid.length; i++){
        grid[i]=' ';
      }
      int col = 0-xMin;
      int row = 0-yMin;
      for(int i=0; i<curve.length-1; i++){
        String turn = ""+curve[i]+curve[i+1];
        if(turn.equals("ru")){
          grid[row*cols+col] = '_';
          col++;
          grid[row*cols+col] = '|';
        }else if(turn.equals("rd")){
          grid[row*cols+col] = '_';
          col++;
          row++;
          grid[row*cols+col] = '|';
        }else if(turn.equals("lu")){
          grid[row*cols+col] = '_';
          col--;
          grid[row*cols+col] = '|';
        }else if(turn.equals("ld")){
          grid[row*cols+col] = '_';
          col--;
          row++;
          grid[row*cols+col] = '|';
        }else if(turn.equals("ul")){
          grid[row*cols+col] = '|';
          col--;
          row--;
          grid[row*cols+col] = '_';
        }else if(turn.equals("ur")){
          grid[row*cols+col] = '|';
          col++;
          row--;
          grid[row*cols+col] = '_';
        }else if(turn.equals("dr")){
          grid[row*cols+col] = '|';
          col++;
          grid[row*cols+col] = '_';
        }else if(turn.equals("dl")){
          grid[row*cols+col] = '|';
          col--;
          //row++;
          grid[row*cols+col] = '_';
        }
      }
      printCurve(grid, rows, cols);
      n = scan.nextInt();
    }
    System.out.println("~");
  }

  public static char[] make_curve(int fold){
    if(fold==0){
      return new char[]{'r'};
    }else{
      char[] curve = make_curve(fold-1);
      char[] mirror_curve = new char[curve.length];
      for(int i=0; i<curve.length; i++){
        switch(curve[i]){
          case 'r':
            mirror_curve[curve.length-i-1]='u';
            break;
          case 'u':
            mirror_curve[curve.length-i-1]='l';
            break;
          case 'l':
            mirror_curve[curve.length-i-1]='d';
            break;
          case 'd':
            mirror_curve[curve.length-i-1]='r';
            break;
        }
      }
      char[] result = new char[curve.length*2];
      for(int i=0; i<curve.length; i++){
        result[i]=curve[i];
      }
      for(int i=0; i<curve.length; i++){
        result[curve.length+i]=mirror_curve[i];
      }
      return result;
    }
  }

  public static void printCurve(char[] grid, int rows, int cols){
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
        System.out.print(grid[i*cols+j]);
      }
      System.out.println();
    }
  }
}