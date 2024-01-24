/* poker hands: from programming challenges */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Arrays;

class Main {
  static final int NCARDS = 52;      /* number of cards */
  static final int NSUITS = 4;       /* number of suits */
  static char values[] = "23456789TJQKA".toCharArray();
  static char suits[] = "CDHS".toCharArray();

  // 2C -> 0 3C -> 1 AC -> 12
  // 2D -> 13

  public static void main(String[] args) throws FileNotFoundException{
    //invariant_test();
    int num_moves = 0;
    Scanner scan = new Scanner(new File("input.txt"));
    while(scan.hasNext()){
      String line = scan.nextLine();
      int[] black = new int[5];
      int[] white = new int[5];
      String[] cards = line.split(" ");
      for(int i=0; i<5; i++){
        black[i] = rank_card(cards[i].charAt(0),cards[i].charAt(1));
      }
      for(int i=0; i<5; i++){
        white[i] = rank_card(cards[i+5].charAt(0),cards[i+5].charAt(1));
      }
      System.out.println("black:"+Arrays.toString(black));
      System.out.println("white:"+Arrays.toString(white));

      System.out.println(consecutive_values(new int[]{0, 1, 2, 3, 4}));
      System.out.println(consecutive_values(new int[]{0, 1, 2, 3, 13}));

      // Straight Flush. Five cards of the same suit with consecutive values. Ranked by the highest card in the hand.
      int black_rank = straight_flush(black);
      int white_rank = straight_flush(white);
      if(black_rank!=0 && white_rank==0){
        System.out.println("Black wins.");
        continue;
      }else if(black_rank==0 && white_rank!=0){
        System.out.println("White wins.");
        continue;
      }else if(black_rank!=0 && white_rank!=0){
        if(black_rank==white_rank){
          System.out.println("Tie.");
          continue;
        }else if(black_rank>white_rank){
          System.out.println("Black wins.");
          continue;
        }else{
          System.out.println("White wins.");
          continue;
        }
      }

      // Four of a Kind. Four cards with the same value. Ranked by the value of the four cards.
      black_rank = four_of_a_kind(black);
      white_rank = four_of_a_kind(white);
      if(black_rank!=-1 && white_rank!=-1){
        if(black_rank==white_rank){
          System.out.println("Tie.");
          continue;
        }else if(black_rank>white_rank){
          System.out.println("Black wins.");
          continue;
        }else{
          System.out.println("White wins.");
          continue;
        }
      }else if(black_rank==0 && white_rank!=0){
        System.out.println("White wins.");
        continue;
      }else if(black_rank!=0 && white_rank==0){
        System.out.println("Black wins.");
        continue;
      }
    }
  }

  static int four_of_a_kind(int[] hand){
    // return -1 if not
    // return n = value of the four cards of the same kind (value)
    int[] values = new int[13];
    for(int i=0; i<13; i++){
      values[i] = 0;
    }
    for(int i=0; i<5; i++){
      values[value(hand[i])]++;
    }

    for(int i=0; i<13; i++){
      if(values[i] >= 4){
        return i;
      }
    }
    return 0;
  }

  static int straight_flush(int[] hand){
    // return 0 not straight flush
    // return n = highest value in hand
    if(same_suit(hand)&&consecutive_values(hand)){
      Arrays.sort(hand);
      return value(hand[4]);
    }else{
      return 0;
    }
  }

  static boolean same_suit(int[] hand){
    char suit = suit(hand[0]);
    boolean result = true;
    for(int i=1; i<5; i++){
      if(suit != suit(hand[i])){
        result = false;
        break;
      }
    }
    return result;
  }

  static boolean consecutive_values(int[] hand){
    int[] values = new int[5];
    for(int i=0; i<5; i++){
      values[i] = value(hand[i]);
    }
    Arrays.sort(values);
    boolean result = true;
    int current = values[0];
    for(int i=1; i<5; i++){
      int next = values[i];
      if(current+1 != next){
        result = false;
        break;
      }else{
        current = next;
      }
    }
    return result;
  }

  static int rank_card(char value, char suit){
    for(int i = 0; i < NCARDS/NSUITS; i++){
      if(value == values[i]){
        for(int j = 0; j < NSUITS; j++){
          if(suit == suits[j]){
            return i+j*(NCARDS/NSUITS);
          }
        }
      }
    }
    return -1;
  }

  static char suit(int card){
    return suits[card/(NCARDS/NSUITS)];
  }

  static char value(int card){
    return values[card%(NCARDS/NSUITS)];
  }

  static void invariant_test(){
    for(int i=0; i<NCARDS; i++){
      if(i != rank_card(value(i), suit(i))){
        System.err.println("Something is wrong!");
      }
    }
  }
}