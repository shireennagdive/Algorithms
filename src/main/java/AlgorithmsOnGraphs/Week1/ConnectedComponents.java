package AlgorithmsOnGraphs.Week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj, int n, boolean[] visited) {
        int result = 0;
        //write your code here
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                explore(i, adj, visited);
                result++;
            }
        }
        return result;
    }


    private static void explore(int v, ArrayList<Integer>[] adj, boolean[] visited) {
        visited[v] = true;
        adj[v].forEach(vertex -> {
            if (!visited[vertex]) {
                explore(vertex, adj, visited);
            }
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            visited[i] = false;
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj, n, visited));
    }
}

