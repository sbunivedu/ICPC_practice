// https://open.kattis.com/problems/10kindsofpeople
import java.util.Scanner;

public class tenkindsofpeople {
    private static int r;
    private static int c;
    private static int[][] map;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        r = scan.nextInt();
        c = scan.nextInt();
        //System.err.println("r: " + r + " c: " + c);
        map = new int[r][c];
        scan.nextLine(); // consume the rest of the line
        for(int i = 0; i < r; i++) {
            String line = scan.nextLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j)-'0';
                //System.err.println("map[" + i + "][" + j + "] = " + map[i][j]);
            }
        }
        int num_queries = scan.nextInt();
        //System.err.println("Number of queries: " + num_queries);
        for (int i = 0; i < num_queries; i++) {
            int r1 = scan.nextInt();
            int c1 = scan.nextInt();
            int r2 = scan.nextInt();
            int c2 = scan.nextInt();
            //System.err.println("Query " + (i+1) + ": (" + r1 + ", " + c1 + ") to (" + r2 + ", " + c2 + ")");
            int result = solve(map, r1-1, c1-1, r2-1, c2-1);
            if(result == 0) {
                System.out.println("binary");
            } else if(result == 1) {
                System.out.println("decimal");
            } else {
                System.out.println("neither");
            }
        }
        scan.close();
    }

    public static int solve(int[][] map, int r1, int c1, int r2, int c2) {
        // r1, c1, r2, c2 are 0-indexed
        if(map[r1][c1] != map[r2][c2]) {
          return -1;
        }
        if(r1==r2 && c1==c2) {
          return map[r1][c1] == 0 ? 0 : 1;
        }
        int result = -1;
        // north
        if(r1-1>=0 && r1-1<r && map[r1-1][c1] == map[r1][c1]) {
            map[r1][c1] = -1; // mark as visited
            result = solve(map, r1-1, c1, r2, c2);
        }
        // south
        if(result == -1 && r1+1>=0 && r1+1<r && map[r1+1][c1] == map[r1][c1]) {
            map[r1][c1] = -1; // mark as visited
            result = solve(map, r1+1, c1, r2, c2);
        }
        // west
        if(result == -1 && c1-1>=0 && c1-1<c && map[r1][c1-1] == map[r1][c1]) {
            map[r1][c1] = -1; // mark as visited
            result = solve(map, r1, c1-1, r2, c2);
        }
        // east
        if(result == -1 && c1+1>=0 && c1+1<c && map[r1][c1+1] == map[r1][c1]) {
            map[r1][c1] = -1; // mark as visited
            result = solve(map, r1, c1+1, r2, c2);
        }
        return result;
    }
}
