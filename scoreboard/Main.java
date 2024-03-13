//Contest Scoreboard
//PC/UVa IDs: 110207/10258, Popularity: B, Success rate: average Level: 1

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
  static class Contestant{
    private int problem_solved = 0;
    private int penalty = 0;
    private int id = 0;
    
    public Contestant(int id, int solved, int penalty){
      this.id = id;
      problem_solved = solved;
      this.penalty = penalty;
    }

    public String toString(){
      return id+" "+problem_solved+" "+penalty;
    } 
  }
  
  public static void main(String[] args) throws FileNotFoundException{
    Scanner scan = new Scanner(new File("input1.txt"));
    int num_cases = Integer.parseInt(scan.nextLine());
    scan.nextLine(); // skips the empty line
    //System.out.println("# cases: "+num_cases);
    
    for (int i = 0; i < num_cases; i++){ // for each case
      boolean[] contestant_present = new boolean[101]; // whether a contestant is present
      for(int j = 0; j < 101; j++){
        contestant_present[j] = false;
      }
        
      int[][] scores = new int[101][10];
      for(int j = 0; j < 101; j++){
        for(int k=0; k < 10; k++){
          scores[j][k] = 0;
        }
      }
      
      String line = scan.nextLine(); // prime read
      while(true){
        //System.err.println("next line: "+line);
        String[] record = line.split(" ");
        int contestant = Integer.parseInt(record[0]);
        int problem = Integer.parseInt(record[1]);
        int time = Integer.parseInt(record[2]);
        String L = record[3];
        contestant_present[contestant] = true;
        if(L.equals("C") || L.equals("I")){
          if (scores[contestant][problem] <= 0){ // not solved
            if(L.equals("C")){ // correct
              scores[contestant][problem] = time + scores[contestant][problem]*(-1);
            }else{ // incorrect, negative time represents penalty
              scores[contestant][problem] -= 20;
            }
          }else{// already solved
          }
        }
        if(!scan.hasNextLine()){
          break; // end of this case
        }else{
          line = scan.nextLine();
          if(line.equals("")){
            break; // end of this case
          }
        }
      }
        
      // tally result
      ArrayList<Contestant> contestants = new ArrayList<Contestant>();
      for(int j = 0; j < 101; j++){
        if(contestant_present[j]){
          int solved = 0;
          int penalty = 0;
          for(int k = 0; k < 10; k++){
            if(scores[j][k] > 0){
              solved++;
              penalty += scores[j][k];
            }
          }
          contestants.add(new Contestant(j, solved, penalty));
        }
      }
      
      // sort contestants
      Collections.sort(contestants, new Comparator<Contestant>(){
        public int compare(Contestant c1, Contestant c2){
          if(c1.problem_solved == c2.problem_solved){
            return c1.penalty - c2.penalty;
          }else{
            return c2.problem_solved - c1.problem_solved;
          }
        }
      });
      // print result
      //System.out.println("Case #"+i);
      if(i != 0){// not first case
        System.out.println();
      }
      for(Contestant c : contestants){
        System.out.println(c);
      }
    }
  }
}