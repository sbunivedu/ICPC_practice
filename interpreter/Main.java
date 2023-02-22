// accepted at https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=29&page=show_problem&problem=974
// https://www.udebug.com/UVa/10033
import java.io.*;
import java.util.*;
import java.util.Arrays;

class Main {  
  public static void main(String[] args) throws FileNotFoundException {
    //Scanner scan = new Scanner(new File("input1.txt"));
    Scanner scan = new Scanner(System.in);
  
    int num_cases = Integer.parseInt(scan.nextLine());
    // skip the blank line
    scan.nextLine();

    for(int i=0; i<num_cases; i++){
      // load instructions
      String[] words = new String[1000];
      for(int j=0; j<1000; j++){
        words[j] = "000";
      }
      int index = 0;
      String next = scan.nextLine().trim(); // read 1st line of each case
      words[index++] = next;
      
      while(scan.hasNext()){
        //System.out.println("next word:"+next);
        next = scan.nextLine().trim(); 
        if(next.length() == 0){
          break;
        }
        words[index++] = next;
      }
      //System.out.println(Arrays.toString(words));
      int[] registers = new int[10];

      //execute
      int count = 0;
      int addr = 0;
      int d,s,a,n;
      boolean halt = false;
      
      while(!halt){
        //decode
        String word = words[addr];
        char command = word.charAt(0);
        //System.out.println("executing:"+word);
        int arg1 = (int)word.charAt(1)-'0';
        int arg2 = (int)word.charAt(2)-'0';
        count++;
        addr++;
        
        switch(command){
          case '1': 
            // halt
            System.out.println(count);
            if(i != num_cases-1){
              //not last case
              System.out.println();
            }
            halt = true;
            break;
          case '2':
            // 2dn: set register d to n (between 0 and 9)
            d = arg1;
            n = arg2;
            registers[d] = n;
            break;
          case '3':
            //3dn: add n to register d
            d = arg1;
            n = arg2;
            registers[d] = (registers[d]+n)%1000;
            break;
          case '4':
            //4dn: multiply register d by n
            d = arg1;
            n = arg2;
            registers[d] = (registers[d]*n)%1000;
            break;
          case '5':
            //5ds: set register d to the value of register s 
            d = arg1;
            s = arg2;
            registers[d] = registers[s];
            break;
          case '6':
            //6ds: add the value of register s to register d
            d = arg1;
            s = arg2;
            registers[d] = (registers[d]+registers[s])%1000;
            break;
          case '7':
            //7ds: multiply register d by the value of register s
            d = arg1;
            s = arg2;
            registers[d] = (registers[d]*registers[s])%1000;
            break;
          case '8':
            //8da: set register d to the value in RAM whose address is in register a
            d = arg1;
            a = arg2;
            registers[d] = Integer.parseInt(words[registers[a]]);
            break;
          case '9':
            //9sa: set the value in RAM whose address is in register a to that of register s
            s = arg1;
            a = arg2;
            int hundreds = registers[s]/100;
            int tens = (registers[s]-hundreds*100)/10;
            int ones = registers[s]-hundreds*100-tens;
            words[registers[a]] = ""+hundreds+tens+ones;
            break;
          case '0':
            //0ds: goto the location in register d unless register s contains 0
            d = arg1;
            s = arg2;
            if(registers[s] != 0){
              addr = registers[d];
            }
        } 
        //System.out.println(Arrays.toString(registers));
      }
    }
  }
}