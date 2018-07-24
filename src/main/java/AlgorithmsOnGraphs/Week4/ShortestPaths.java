import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest, int n) {
        int v, k, flag;
        long weight;
        distance[s] = 0;
        reachable[s] = 1;
        flag = 1;
        for (k = 0; k < n && flag == 1; k++) {
            flag = 0;
            for (int u = 0; u < n; u++) {
                for (int j = 0; j < adj[u].size(); j++) {
                    v = adj[u].get(j);
                    weight = cost[u].get(j);
                    if (distance[u] != Long.MAX_VALUE && distance[u] + weight < distance[v]) {
                        distance[v] = distance[u] + weight;
                        reachable[v] = 1;
                        flag = 1;
                    }
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        if (k == n && flag == 1) {
            flag = 0;
            for (int u = 0; u < n; u++) {
                for (int j = 0; j < adj[u].size(); j++) {
                    v = adj[u].get(j);
                    weight = cost[u].get(j);
                    if (distance[u] != Long.MAX_VALUE && distance[u] + weight < distance[v]) {
                        distance[v] = distance[u] + weight;
                        queue.add(v);
                        shortest[v] = 0;
                        flag = 1;
                    }
                }
            }
            if (flag == 1) {
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int i = 0; i < adj[u].size(); i++) {
                        v = adj[u].get(i);
                        shortest[v] = 0;
                    }
                }
            }
        }
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest, n);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

