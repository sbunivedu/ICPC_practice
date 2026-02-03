import java.util.Scanner;
import java.util.Stack;

public class thought {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int count = scan.nextInt();
    while(count-- > 0){
      int n = scan.nextInt();
      // * / + -
      String expression = "";
      String operators = "*/+-";
      boolean found = false;
      next:
      for(int i=0; i<4; i++){
        for(int j=0; j<4; j++){
          for(int k=0; k<4; k++){
            //compose all possible expressions
            expression = "4"+
              operators.charAt(i)+"4"+
              operators.charAt(j)+"4"+
              operators.charAt(k)+"4";
            //System.out.println(expression);
            //evaluate each expression
            String postfix = toPostfix(expression);
            int result = evaluatePostfix(postfix);

            //report the first one answer found
            if(result == n){
              found = true;
              String output = "";
              for(int m=0; m<expression.length(); m++){
                output += expression.charAt(m)+" ";
              }
              output += "= "+n;
              System.out.println(output);
              break next;
            }
          }
        }
      }
      if(!found){
        System.out.println("no solution");
      }
    }
  }

  static String toPostfix(String infix) {
    String postfix = "";
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < infix.length(); i++) {
      char c = infix.charAt(i);
      if (Character.isDigit(c)) {
        postfix += c;
      } else if (c == '+' || c == '-') {
        while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '+' || stack.peek() == '-')) {
          postfix += stack.pop();
        }
        stack.push(c);
      } else if (c == '*' || c == '/') {
        while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
          postfix += stack.pop();
        }
        stack.push(c);
      }
    }
    while (!stack.isEmpty()) {
      postfix += stack.pop();
    }
    return postfix;
  }
  static int evaluatePostfix(String postfix) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < postfix.length(); i++) {
      char c = postfix.charAt(i);
      if (Character.isDigit(c)) {
        stack.push(c - '0');
      } else {
        int b = stack.pop();
        int a = stack.pop();
        switch (c) {
          case '+':
            stack.push(a + b);
            break;
          case '-':
            stack.push(a - b);
            break;
          case '*':
            stack.push(a * b);
            break;
          case '/':
            stack.push(a / b);
            break;
        }
      }
    }
    return stack.pop();
  }
}
