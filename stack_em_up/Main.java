// Stack ’em Up
// PC/UVa IDs: 110205/10205, Popularity: B, Success rate: average Level: 1
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

class Main {
  static final int NCARDS = 52;      /* number of cards */
  static final int NSUITS = 4;       /* number of suits */
  static String values[] = {"2","3","4","5","6","7","8","9","10","Jack", "Queen","King", "Ace"};
  static String suits[] = {"Clubs", "Diamonds", "Hearts", "Spades"};

  public static void main(String[] args) throws FileNotFoundException{
    //Scanner scan = new Scanner(new File("input1.txt"));
    Scanner scan = new Scanner(System.in);
    int num_cases = scan.nextInt();
    scan.nextLine(); //throw away empty line
    for(int i=0; i<num_cases; i++){
      String[] cards = get_new_deck();
      //System.out.println("new deck:"+Arrays.toString(cards));
      ArrayList<int[]> shuffles = new ArrayList<int[]>();
      int num_shuffles = scan.nextInt();
      for(int j=0; j<num_shuffles; j++){
        int[] shuffle = new int[NCARDS];
        for(int k=0; k<NCARDS; k++){
          shuffle[k] = scan.nextInt()-1;
        }
        //System.out.println("shuffle "+j+": "+Arrays.toString(shuffle));
        shuffles.add(shuffle);
      }
      scan.nextLine(); //throw away linebreak
      while(true){
        if(!scan.hasNextLine()){
          break;
        }else{
          String next_line = scan.nextLine();
          if(next_line.length() > 0){
            int shuffle_index = Integer.parseInt(next_line)-1;
            int[] shuffle = shuffles.get(shuffle_index);
            String[] new_cards = new String[NCARDS];
            for(int j=0; j<NCARDS; j++){
              new_cards[j] = cards[shuffle[j]];
            }
            //System.out.println("after shuffle "+shuffle_index+":"+Arrays.toString(new_cards));
            cards = new_cards;
          }else{
            break;
          }
        }
      }
      // print result of shuffling
      for(int j=0; j<NCARDS; j++){
        System.out.println(cards[j]);
      }
      if(i < num_cases-1){
        System.out.println(); // empty line between cases
      }
    }
  }

  static String[] get_new_deck(){
    String[] cards = new String[NCARDS];
    for(int j=0; j<NCARDS; j++){
      cards[j] = values[j%(NCARDS/NSUITS)]+" of "+suits[j/(NCARDS/NSUITS)];
    }
    return cards;
  }
}