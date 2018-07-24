import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Toposort {

    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj, int n, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, stack, order, visited, i);
            }
        }
        int i = 0;
        while (!stack.isEmpty()) {
            order.add(i, stack.pop());
            i++;
        }
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, Stack<Integer> stack, ArrayList<Integer> order, boolean[] visited, int v) {
        visited[v] = true;
        adj[v].forEach(vertex -> {
            if (!visited[vertex]) {
                dfs(adj, stack, order, visited, vertex);
            }
        });
        stack.push(v + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean[] visited = new boolean[n];
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
        ArrayList<Integer> order = toposort(adj, n, visited);
        for (int x : order) {
            System.out.print((x) + " ");
        }
    }
}

