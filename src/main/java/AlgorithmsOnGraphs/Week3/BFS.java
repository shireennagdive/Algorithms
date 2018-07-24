import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t, int n) {
        int[] dist = new int[n];
        int v;
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        dist[s] = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int i = 0; i < adj[u].size(); i++) {
                v = adj[u].get(i);
                if (dist[v] == Integer.MAX_VALUE) {
                    queue.add(v);
                    dist[v] = dist[u] + 1;
                }
            }
        }
        return (dist[t] == Integer.MAX_VALUE ? -1 : dist[t]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
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
        if (x == y) {
            System.out.println(0);
        } else {
            System.out.println(distance(adj, x, y, n));
        }
    }
}

