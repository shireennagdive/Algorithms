import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t, int n) {
        class VertexDist {
            int vertex;
            int dist;

            VertexDist(int vertex, int dist) {
                this.vertex = vertex;
                this.dist = dist;
            }
        }
        VertexDist[] vertexDists = new VertexDist[n];
        int v, dist, weight;
        for (int i = 0; i < n; i++) {
            vertexDists[i] = new VertexDist(i, Integer.MAX_VALUE);
        }

        PriorityQueue<VertexDist> queue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.dist)
        );

        vertexDists[s].dist = 0;
        queue.add(vertexDists[s]);
        while (!queue.isEmpty()) {
            VertexDist u = queue.poll();
            for (int i = 0; i < adj[u.vertex].size(); i++) {
                v = adj[u.vertex].get(i);
                dist = vertexDists[v].dist;
                weight = cost[u.vertex].get(i);
                if (dist > u.dist + weight) {
                    vertexDists[v].dist = u.dist + weight;
                    queue.add(vertexDists[v]);
                }
            }
        }
        return (vertexDists[t].dist == Integer.MAX_VALUE ? -1 : vertexDists[t].dist);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y, n));
    }
}

