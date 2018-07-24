package AlgorithmsOnGraphs.Week2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj, int n, boolean[] visited, int[] prev) {
        int flag = 0;
        for (int i = 0; i < n && flag == 0; i++) {
            if (!visited[i]) {
                flag = explore(i, adj, visited, i, flag);
                visited = new boolean[n];
            }
        }
        return flag;
    }

    private static int explore(int v, ArrayList<Integer>[] adj, boolean[] visited, int V, int flag) {
        visited[v] = true;
        for (int i = 0; i < adj[v].size() && flag == 0; i++) {
            if (!adj[v].contains(V)) {
                if (!visited[adj[v].get(i)]) {
                    flag = explore(adj[v].get(i), adj, visited, V, flag);
                }
            } else {
                flag = 1;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj, n, visited, prev));
    }
}

