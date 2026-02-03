# Explanation of 8 Queens Solution

## Problem Overview
The 8 Queens problem involves validating whether 8 queens are placed on an 8x8 chessboard such that no two queens threaten each other. Queens attack along rows, columns, and diagonals. This solution reads a board configuration from input and checks for validity.

## Code Structure
The program uses a brute-force approach to check all pairs of queens for conflicts.

### Key Components
1. **Input Reading**: Reads 8 lines of 8 characters each, where '*' represents a queen.
2. **Board Representation**: Stores the board in a 2D integer array (1 for queen, 0 otherwise).
3. **Queen Position Collection**: Gathers positions of all queens into an array.
4. **Conflict Checking**: Iterates through all pairs of queens to check for attacks.
5. **Output**: Prints "valid" if no conflicts, "invalid" otherwise.

### Detailed Explanation

#### Input Handling
```java
Scanner scan = new Scanner(System.in);
int[][] board = new int[8][8];
for(int i = 0; i < 8; i++) {
  String line = scan.nextLine();
  for(int j = 0; j < 8; j++) {
    if(line.charAt(j) == '*') {
      board[i][j] = 1;
    } else {
      board[i][j] = 0;
    }
  }
}
```
- Uses Scanner to read from standard input.
- Assumes exactly 8 lines, each with 8 characters.
- Converts '*' to 1 (queen) and others to 0.

#### Collecting Queen Positions
```java
int[][] queens = new int[8][2];
int index = 0;
for(int i = 0; i < 8; i++) {
  for(int j = 0; j < 8; j++) {
    if(board[i][j] == 1) {
      queens[index][0] = i;  // row
      queens[index][1] = j;  // column
      index++;
    }
  }
}
```
- Creates an array to store queen positions (row, column).
- Iterates through the board to find and store queen locations.

#### Conflict Detection
```java
for(int i = 0; i < queens.length-1; i++) {
  for(int j = i + 1; j < queens.length; j++) {
    if(queens[i][0] == queens[j][0]   // same row
      || queens[i][1] == queens[j][1] // same column
      || Math.abs(queens[i][0] - queens[j][0]) == Math.abs(queens[i][1] - queens[j][1])) {  // diagonal
      System.out.println("invalid");
      return;
    }
  }
}
```
- Checks every pair of queens.
- Conditions for attack:
  - Same row: `queens[i][0] == queens[j][0]`
  - Same column: `queens[i][1] == queens[j][1]`
  - Same diagonal: `|row_diff| == |col_diff|`
- If any pair attacks, output "invalid" and exit.

#### Output and Cleanup
```java
System.out.println("valid");
scan.close();
```
- If no conflicts found, print "valid".
- Close the scanner.

## Time Complexity
- O(1): Fixed 8x8 board, constant operations.

## Assumptions
- Exactly 8 queens are present.
- Input is well-formed (8 lines of 8 characters).

## Potential Improvements
- Add input validation for robustness.
- **Use boolean arrays for rows/columns/diagonals for O(1) checks per queen**: Instead of checking every pair of queens (O(N^2) for N queens), use three boolean arrays to track occupied rows, columns, and diagonals. For rows and columns, use arrays of size 8. For diagonals, there are two types: main diagonals (where row + col is constant) and anti-diagonals (where row - col is constant). Use arrays of size 15 for each (since max row+col=14, min row-col=-7 to 7, shifted to 0-14). When placing each queen, check if the corresponding row, column, or diagonal is already occupied. If yes, it's invalid. This reduces the check to O(1) per queen, making the overall validation O(N) instead of O(N^2), though for N=8 it's negligible. Example code snippet:
  ```java
  boolean[] rows = new boolean[8];
  boolean[] cols = new boolean[8];
  boolean[] diag1 = new boolean[15]; // row + col
  boolean[] diag2 = new boolean[15]; // row - col + 7
  for(int[] queen : queens) {
    int r = queen[0], c = queen[1];
    if(rows[r] || cols[c] || diag1[r + c] || diag2[r - c + 7]) {
      System.out.println("invalid");
      return;
    }
    rows[r] = cols[c] = diag1[r + c] = diag2[r - c + 7] = true;
  }
  ```
- Handle cases with fewer/more than 8 queens.