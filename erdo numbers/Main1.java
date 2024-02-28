// solution from pc.pdf

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayDeque; 
import java.util.ArrayList; 
import java.util.Deque;
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;
import java.io.File;
import java.util.Scanner;

class Main1{
  private static Scanner reader = null;
  private static final Pattern namePattern = Pattern
    .compile("[\\w^.,]+\\s*,\\s*(\\w\\.)+\\s*[,:]");
  private static final String ERDOS = "Erdos, P.";

  private static void add(Map<String, Set<String>> graph,
                          List<String> names) {
    for(int i = 0; i < names.size(); ++i) {
      String currName = names.get(i);
      if (!graph.containsKey(currName)) {
        graph.put(currName, new HashSet<String>());
      }
      names.forEach(name -> {
        if (!currName.equalsIgnoreCase(name)) { 
          graph.get(currName).add(name);
        } 
      });
    }
  }
  private static List<String> getNames(String input) {
    List<String> names = new ArrayList<>(); 
    Matcher m = namePattern.matcher(input); 
    while (m.find()) {
      names.add(input.substring(m.start(), m.end() - 1).trim());
    }
    return names;
  }
  
  private static Map<String, Integer> getAnswer( 
    Map<String, Set<String>> graph) { 
    Deque<String> queue = new ArrayDeque<>();
    Set<String> seen = new HashSet<String>(); 
    Map<String, Integer> result = new HashMap<>(); 
    queue.push(ERDOS);
    result.put(ERDOS, Integer.valueOf(0));
    while (!queue.isEmpty()) {
      String name = queue.pop();
      int depth = result.get(name);
      for (String x : graph.get(name)) {
        if (!seen.contains(x)) { 
          seen.add(x);
          queue.addLast(x);
          result.put(x, Integer.valueOf(depth + 1)); 
        }
      }
    }
    return r;
  }
  
  public static void main(String[] args) throws IOException { 
    reader = new Scanner(new File( "input.txt"));
    int num = Integer.parseInt(reader.nextLine().trim());
    for (int i = 0; i < num; ++i) {
      List<Integer> pn = stream(reader.nextLine().trim().split(" ")) 
        .filter(x -> !x.equals("")).map(Integer::parseInt).collect(toList());
      Map<String, Set<String>> graph = new HashMap<>(); 
      int p = pn.get(0);
      for (int j = 0; j < p; ++j) {
        List<String> names = getNames(reader.nextLine().trim());
        System.out.println(names);
        add(graph, names); 
      }
      Map<String, Integer> r = getAnswer(graph); 
      System.out.println("Scenario " + (i + 1)); 
      int n = pn.get(1);
      for (int j = 0; j < n; ++j) {
        String name = reader.nextLine().trim(); 
        System.out.println(name + " " +
                           (r.containsKey(name) ? r.get(name) : "infinity"));
      }
    }
  }
}
