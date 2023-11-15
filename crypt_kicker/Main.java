// source: https://www.martystepp.com/acm/problems/843-crypt-kicker/Solution.java
// solution to ACM UVA Online Judge problem 843 - Crypt Kicker
// https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=30&page=show_problem&problem=784
// to run: from a terminal, type
// java Main < input.txt

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main{
  static final int MAX_WORD_LENGTH = 16;
  static final int MAX_NUM_LETTERS = 26;
  static final char STAR = '*';
  static List<String>[] indexed_dict_words; //length indexed array of lists of words of the same length

  public static void main(String[] args) throws IOException{
    //Scanner scan = new Scanner(new File("input.txt"));
    Scanner scan = new Scanner(System.in);
    int num_dict_word = Integer.parseInt(scan.nextLine());
    String[] dict_words = new String[num_dict_word];

    for(int i=0; i<num_dict_word; i++){
      dict_words[i] = scan.nextLine();
    }

    //System.out.println(Arrays.toString(dict_words));

    indexed_dict_words = (List<String>[]) new ArrayList[MAX_WORD_LENGTH];
    for(int i=0; i<num_dict_word; i++){
      int length = dict_words[i].length();
      if(indexed_dict_words[length] == null){
        indexed_dict_words[length] = new ArrayList<String>();
      }
      indexed_dict_words[length].add(dict_words[i]);
    }

    //System.out.println(Arrays.toString(indexed_dict_words));

    String[] encrypted_words = null;
    while(scan.hasNextLine()){
      encrypted_words = scan.nextLine().split(" ");
      //System.out.println(Arrays.toString(encrypted_words));

      System.out.println(decrypt(encrypted_words));
    }
  }

  private static String decrypt(String[] encrypted_words){
    char[] mappings = new char[MAX_NUM_LETTERS];
    for(int i=0; i<MAX_NUM_LETTERS; i++){
      mappings[i] = STAR;
    }

    char[] result_mapping = backtrack(0, mappings, encrypted_words);
    if(result_mapping == null){
      result_mapping = mappings;
    }
    //System.out.println(Arrays.toString(result_mapping));
    //System.out.println(Arrays.toString(mappings));

    // generate plaintex string
    return decrypt_words(encrypted_words, result_mapping);
  }

  private static String decrypt_words(String[] encrypted_words, char[] mappings){
    String result = "";
    for(String word : encrypted_words){
      for(int i=0; i<word.length(); i++){
        result += mappings[word.charAt(i)-'a'];
      }
      result += " ";
    }
    return result.substring(0, result.length()-1); // remove trailing space
  }

  private static char[] backtrack(int i, char[] mappings, String[] encrypted_words){
    if(i == encrypted_words.length){
      return mappings; // a mapping if found to decrypt all encrypted words
    }
    String encrypted_word = encrypted_words[i];
    int length = encrypted_word.length();
    for(String plaintext_word : indexed_dict_words[length]){
      char[] local_mappings = Arrays.copyOf(mappings, mappings.length);
      if(try_mapping(encrypted_word, plaintext_word, local_mappings)){
        char[] result = backtrack(i+1, local_mappings, encrypted_words);
        if(result != null){
          return result;
        }
      }
    }
    return null;
  }

  private static boolean try_mapping(String encrypted_word, String plaintext_word, char[] mappings){
    // return false for "all"->"the"
    char[] encrypted_chars = encrypted_word.toCharArray();
    char[] plaintex_chars = plaintext_word.toCharArray();
    for(int i=0; i<encrypted_chars.length; i++){
      char encrypted_char = encrypted_chars[i];
      if(mappings[encrypted_char-'a'] == STAR){// no mapping yet
        mappings[encrypted_char-'a'] = plaintex_chars[i];
      }else if(mappings[encrypted_char-'a'] != plaintex_chars[i]){
        return false;
      }
    }
    return injective(mappings);
  }

  private static boolean injective(char[] mappings){
    // return false for "the" -> "all"
    boolean[] mapped = new boolean[MAX_NUM_LETTERS];
    for(int i=0; i<mapped.length; i++){
      mapped[i] = false;
    }
    for(char letter : mappings){
      if(letter != STAR){
        if(mapped[letter-'a'] == true){
          return false;
        }else{
          mapped[letter-'a'] = true;
        }
      }
    }
    return true;
  }
}