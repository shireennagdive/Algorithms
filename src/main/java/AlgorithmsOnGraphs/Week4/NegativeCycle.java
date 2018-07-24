import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int n) {
        int[] dist = new int[n];
        int v, k, weight, flag = 0;
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n && flag == 0; i++) {
            dist[i] = 0;
            flag = 1;
            for (k = 0; k < n && flag == 1; k++) {
                flag = 0;
                for (int u = 0; u < n; u++) {
                    for (int j = 0; j < adj[u].size(); j++) {
                        v = adj[u].get(j);
                        weight = cost[u].get(j);
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                            dist[v] = dist[u] + weight;
                            flag = 1;
                        }
                    }
                }
            }
            if (k == n && flag == 1) {
                flag = 0;
                for (int u = 0; u < n && flag == 0; u++) {
                    for (int j = 0; j < adj[u].size() && flag == 0; j++) {
                        v = adj[u].get(j);
                        weight = cost[u].get(j);
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                            dist[v] = dist[u] + weight;
                            flag = 1;
                        }
                    }
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost, n));
    }
}

