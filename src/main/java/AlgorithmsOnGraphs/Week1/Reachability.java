package AlgorithmsOnGraphs.Week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    public boolean visited[];

    private static int reach(ArrayList<Integer>[] adj, int x, int y, boolean[] visited) {
        //write your code here
        explore(x, adj, visited);
        return visited[y] ? 1 : 0;
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
        boolean visited[] = new boolean[n];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y, visited));
    }
}

