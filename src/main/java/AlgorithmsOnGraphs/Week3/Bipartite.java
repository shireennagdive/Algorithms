import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    public static class Vertex {
        int vertex;
        int colour;

        Vertex(int vertex, int colour) {
            this.vertex = vertex;
            this.colour = colour;
        }
    }

    private static int bipartite(ArrayList<Integer>[] adj, int n) {
        //write your code here
        int flag = 1, v;
        Vertex u;
        Vertex vertex[] = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertex[i] = new Vertex(i, -1);
        }
        Queue<Vertex> queue;
        for (int i = 0; i < n && flag == 1; i++) {
            queue = new LinkedList<>();
            if (vertex[i].colour == -1) {
                queue.add(vertex[i]);
                vertex[i].colour = 0;
                while (flag == 1 && !queue.isEmpty()) {
                    u = queue.poll();
                    for (int j = 0; j < adj[u.vertex].size() && flag == 1; j++) {
                        v = adj[u.vertex].get(j);
                        if (vertex[v].colour == -1) {
                            vertex[v].colour = u.colour == 0 ? 1 : 0;
                            queue.add(vertex[v]);
                        } else {
                            if (vertex[v].colour != -1 && vertex[v].colour == vertex[u.vertex].colour) {
                                flag = 0;
                            }
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
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj, n));
    }
}

