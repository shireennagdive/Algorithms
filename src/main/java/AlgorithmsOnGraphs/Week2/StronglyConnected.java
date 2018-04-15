import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


//get postorder
//get reverse graph
//run in reverse order of postorder list and find SCC
public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, int n, boolean[] visited) {
        int result = 0, v;
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer>[] revAdjList = (ArrayList<Integer>[]) new ArrayList[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                explore(i, adj, visited, stack);
                stack.push(i);
            }
        }

        revAdjList = getReverseGraph(adj, n, revAdjList);

        visited = new boolean[n];
        while (!stack.isEmpty()) {
            v = stack.pop();
            if (!visited[v]) {
                explore2(v, revAdjList, visited);
                result++;
            }
        }
        return result;
    }

    private static void explore2(int v, ArrayList<Integer>[] adj, boolean[] visited) {
        visited[v] = true;
        adj[v].forEach(vertex -> {
            if (!visited[vertex]) {
                explore2(vertex, adj, visited);
            }
        });
    }


    private static void explore(int v, ArrayList<Integer>[] adj, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        adj[v].forEach(vertex -> {
            if (!visited[vertex]) {
                explore(vertex, adj, visited, stack);
                stack.push(vertex);
            }
        });
    }

    private static ArrayList<Integer>[] getReverseGraph(ArrayList<Integer>[] adj, int n, ArrayList<Integer>[] revAdjList) {
        for (int i = 0; i < n; i++) {
            revAdjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (Integer integer : adj[i]) {
                revAdjList[integer].add(i);
            }
        }
        return revAdjList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, n, visited));
    }
}

