// https://open.kattis.com/problems/alphabetanimals

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Alphabet {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    String lastName = scan.nextLine();

    int n = Integer.parseInt(scan.nextLine());

    // map each first letter to the names that start with the letter
    TreeMap<Character, ArrayList<String>> startWith = new TreeMap<>();
    for(int i=0; i<n; i++){
      String name = scan.nextLine();
      char first = name.charAt(0);
      if(!startWith.containsKey(first)){
        startWith.put(first, new ArrayList<>());
      }
      startWith.get(first).add(name);
    }

    //System.out.println(startWith);
    //System.out.println(Arrays.toString(names));

    char lastChar = lastName.charAt(lastName.length()-1);
    if(!startWith.containsKey(lastChar)){
      System.out.println("?");
    }else{
      ArrayList<String> candidates = startWith.get(lastChar);
      for(String candidate : candidates){
        char candidateLastLetter = candidate.charAt(candidate.length()-1);
        //System.out.println("last="+l);
        if(!startWith.containsKey(candidateLastLetter)){
          System.out.println(candidate+"!");
          return;
        }else{
          ArrayList<String> nexts = startWith.get(candidateLastLetter);
          // current candidate is the only name that has the same first and last letter
          if(nexts.size()==1
            && nexts.get(0).equals(candidate)){
            System.out.println(candidate+"!"); // winning move
            return;
          }
        }
      }
      // output the first candidate
      System.out.println(candidates.get(0));
    }
  }
}
