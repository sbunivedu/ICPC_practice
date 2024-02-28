//Erdo ̈s Numbers PC/UVa IDs: 110206/10044, Popularity: B, Success rate: low Level: 2
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

class Main {
  public static String ERDOS = "Erdos, P.";
  
  public static void main(String[] args) throws FileNotFoundException{
    //Scanner scan = new Scanner(System.in);
    Scanner scan = new Scanner(new File("input1.txt"));
    int num_cases = scan.nextInt();
    //System.out.println("num_cases:"+num_cases);

    for(int i=0; i<num_cases; i++){
      int num_papers = scan.nextInt();
      int num_authors = scan.nextInt();
      //System.out.println("num_papers:"+num_papers);
      //System.out.println("num_authors:"+num_authors);
      scan.nextLine(); //throw away linebreak
      ArrayList<String> papers = new ArrayList<>();
      for(int j=0; j<num_papers; j++){
        String paper = scan.nextLine();
        //System.out.println("paper:"+paper);
        papers.add(paper);
      }
      String[] authors = new String[num_authors];
      for(int j=0; j<num_authors; j++){
        authors[j] = scan.nextLine();
      }
      Map<String, Set<String>> graph = new HashMap<>();
      for(String paper : papers){
        ArrayList<String> names = getNames(paper);
        for(String author : names){
          for(String other_author : names){
            if(!author.equals(other_author)){
              graph.putIfAbsent(author, new HashSet<String>());
              graph.get(author).add(other_author);
            }
          }
        }
      }
      //System.out.println(authorToAuthor);
      //System.out.println(getAnswer(authorToAuthor)); 
      Map<String, Integer> erdo_numbers = getAnswer(graph);
      System.out.println("Scenario "+(i+1));
      for(int j=0; j<authors.length; j++){
        if(!erdo_numbers.containsKey(authors[j])){
          System.out.println(authors[j]+" infinity");
        }else{
          System.out.println(authors[j]+" "+erdo_numbers.get(authors[j]));
        }
      }
    }
  }

  public static ArrayList<String> getNames(String input){
    ArrayList<String> names = new ArrayList<>(); 
    int index = input.indexOf(":");
    input = input.substring(0, index);
    String[] authors = input.split(", ");
    for(int i=0; i<authors.length; i+=2){
      String lastname = authors[i];
      String firstname = authors[i+1];
      //System.out.println("full name:"+lastname+", "+firstname); 
      names.add(lastname+", "+firstname);
    }
    return names;
  }

  public static Map<String, Integer> getAnswer(Map<String, Set<String>> graph){
    Set<String> authors = graph.keySet();
    Map<String, Integer> result = new HashMap<>();
    Deque<String> queue = new ArrayDeque<>();
    queue.addLast(ERDOS);
    result.put(ERDOS, 0);
    while(!queue.isEmpty()){
      // only enqueue authors who have been recorded in the result
      String author = queue.pop();
      int depth = result.get(author);
      for(String next_author : graph.get(author)){
        if(!result.containsKey(next_author)){
          result.put(next_author, depth+1);
          queue.addLast(next_author);
        }
      }
    }
    return result;
  }
}
