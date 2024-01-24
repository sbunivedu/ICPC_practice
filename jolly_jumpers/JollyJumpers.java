// 10038
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class JollyJumpers {
  public static void main(String[] args) throws FileNotFoundException {
    try{
    Scanner scan = new Scanner(System.in);
    //Scanner scan = new Scanner(new File("input3.txt"));
    
    while(scan.hasNextInt()) {   
      int x = scan.nextInt();
      //System.out.println("x: " + x);
      if(x==1) {
        System.out.println("Jolly");
      	scan.nextInt();
        //scan.nextLine();
      }   
      else{
        int[] arr = new int[x];

        for(int i = 0; i<x; i++)
          {
            int z = scan.nextInt();
            arr[i] = z;
          }
        //scan.nextLine();
        boolean[] dif = new boolean[x];
        for(int i = 0; i<x; i++){
          dif[i] = false;
        }
        int[] xool = new int[x];
        for(int i = 0; i<x-1; i++)
          {       
            xool[i] = Math.abs(arr[i] - arr[i+1]);
            if(xool[i]<x && xool[i]!=0)
            {
              dif[i] = true;
            }
          }
        boolean finished = false;
        for(int i = 0; i<x && !finished; i++)
          {
            for(int b = i+1; b < x-1 && !finished; b++){
              if(xool[i]==xool[b]){
                System.out.println("Not jolly");
                finished = true;
                break;
              }
            }
          }
        int temp = 0;
        for(int i = 0; i<x && !finished; i++){
          if(dif[i]==true) temp++;
        }
        if(temp == x-1 )
          System.out.println("Jolly");
        else if(!finished)
          System.out.println("Not jolly");
      }
    }
    }catch(Exception e){
      System.out.println(e);
      System.out.println("error");
    }
  }
  
  public static void print(String s){
    System.out.println(s);
  }
}

