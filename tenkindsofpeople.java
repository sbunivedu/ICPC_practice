// https://open.kattis.com/problems/10kindsofpeople
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class tenkindsofpeople {
    private static int rows;
    private static int cols;
    private static int[][] map;
    private static int[][] component;
    private static int[] componentValue;
    private static int componentCount;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        rows = scan.nextInt();
        cols = scan.nextInt();

        map = new int[rows][cols];
        component = new int[rows][cols];

        scan.nextLine(); // consume the rest of the line
        for(int i = 0; i < rows; i++) {
            String line = scan.nextLine();
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j) - '0';
                component[i][j] = -1;
            }
        }

        // Label all connected components using BFS
        componentValue = new int[rows * cols + 1];
        componentCount = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(component[i][j] == -1) {
                    bfsLabel(i, j, componentCount, map[i][j]);
                    componentCount++;
                }
            }
        }

        // Answer queries
        int num_queries = scan.nextInt();
        for (int i = 0; i < num_queries; i++) {
            int r1 = scan.nextInt() - 1;
            int c1 = scan.nextInt() - 1;
            int r2 = scan.nextInt() - 1;
            int c2 = scan.nextInt() - 1;

            // Check if both points are in the same connected component
            if(component[r1][c1] == component[r2][c2]) {
                int value = componentValue[component[r1][c1]];
                if(value == 0) {
                    System.out.println("binary");
                } else {
                    System.out.println("decimal");
                }
            } else {
                System.out.println("neither");
            }
        }
        scan.close();
    }

    private static void bfsLabel(int startR, int startC, int compId, int value) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startR, startC});
        component[startR][startC] = compId;
        componentValue[compId] = value;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // north, south, west, east

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for(int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                if(newR >= 0 && newR < rows && newC >= 0 && newC < cols &&
                   component[newR][newC] == -1 && map[newR][newC] == value) {
                    component[newR][newC] = compId;
                    queue.add(new int[]{newR, newC});
                }
            }
        }
    }
}
