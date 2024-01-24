
// white pieces are represented by upper case letters
// black pieces ... lower case letters
import java.io.*;
import java.util.*;
import java.util.Arrays;

class Main {
  public static int white_king_row = 0;
  public static int white_king_col = 0;
  public static int black_king_row = 0;
  public static int black_king_col = 0;
  public char board[][] = new char[8][8];
  
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("input.txt"));
    //Scanner scan = new Scanner(System.in);

    while(true){
      boolean empty = true;
      for(int row=0; row<8; row++){
        String next_row = scan.nextLine();
        for(int col=0; col<8; col++){
          char next_piece = next_row.charAt(col); 
          board[row][col] = next_piece;
          if(next_piece != '.'){
            empty = false;
          }
        }
      }
      if (empty){
        return;
      }
      // find kings
      for(int row=0; row<8; row++){
        for(int col=0; col<8; col++){
          if(board[row][col]=='k'){
            black_king_row = row;
            black_king_col = col;
            System.out.println("black k("+row+","+col+")");
          }
          if(board[row][col]=='K'){
            white_king_row = row;
            white_king_col = col;
            System.out.println("white K("+row+","+col+")");
          }
        }
      }
      
      display(board);
      scan.nextLine(); // remove empty line
    }
  }

  public static void display(char[][] board){
    for(int row=0; row<8; row++){
      for(int col=0; col<8; col++){
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
  }

  public boolean checkWhitePown(int pown_row, int pown_col){
    //check upper left
    if(pown_row-1 == king_row && pown_col-1 == black_king_col){
      return true;
    }
    //check upper right
    if(pown_row-1 == king_row && pown_col+1 == black_king_col){
      return true;
    }
    return false;
  }

  public boolean checkBlackPown(int pown_row, int pown_col){
    //check lower left
    if(pown_row+1 == king_row && pown_col-1 == white_king_col){
      return true;
    }
    //check lower right
    if(pown_row+1 == king_row && pown_col+1 == white_king_col){
      return true;
    }
    return false;
  }

  public boolean checkWhiteRook(int rook_row, int rook_col){
    boolean result = false;
    // go up
    int row = rook_row;
    int col = rook_col;
    while(valid(row, col)){
      if(board[row][col] == 'k'){ // black king is in check
        return true; 
      }else if(board[row][col] != '.'){ // sth in the way
        break;
      }
      row++; 
    }
    // go down
    row = rook_row;
    col = rook_col;
    while(valid(row, col)){
      if(board[row][col] == 'k'){ // black king is in check
        return true; 
      }else if(board[row][col] != '.'){ // sth in the way
        break;
      }
      row--; 
    }
    // go left
    row = rook_row;
    col = rook_col;
    while(valid(row, col)){
      if(board[row][col] == 'k'){ // black king is in check
        return true; 
      }else if(board[row][col] != '.'){ // sth in the way
        break;
      }
      col--; 
    }
    // go right
    row = rook_row;
    col = rook_col;
    while(valid(row, col)){
      if(board[row][col] == 'k'){ // black king is in check
        return true; 
      }else if(board[row][col] != '.'){ // sth in the way
        break;
      }
      col++; 
    }
  }
  
  public boolean valid(int row, int col){
    return row >= 0 && row <= 7 &&
      col >= 0 && col <= 7;
  }
}