package AlgorithmsOnGraphs.Week5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ConnectingPoints {

    static int[] parent;
    static int[] rank;

    public static class Point {
        int x;
        int y;
        int position;

        Point(int x, int y, int position) {
            this.x = x;
            this.y = y;
            this.position = position;
        }
    }

    public static class Edge {
        Point a;
        Point b;
        Double weight;

        Edge(Point a, Point b, Double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            rank[rootY] += 1;
        }
    }

    private static double minimumDistance(int[] x, int[] y, int n, int[] parent) {
        double result = 0.;
        Point points[] = new Point[n];
        ArrayList<Edge> edges = new ArrayList<>();
        int positionA, positionB;
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(x[i], y[i], i);
            parent[i] = i;
        }

        edges = getDistances(points, edges);

        for (Edge edge : edges) {
            positionA = edge.a.position;
            positionB = edge.b.position;
            if (find(positionA) != find(positionB)) {
                result += edge.weight;
                union(positionA, positionB);
            }
        }

        return result;
    }

    private static ArrayList<Edge> getDistances(Point points[], ArrayList<Edge> edges) {
        int length = points.length;
        Double value;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                value = Math.sqrt(((points[i].x - points[j].x) * (points[i].x - points[j].x)) + ((points[i].y - points[j].y) * (points[i].y - points[j].y)));
                edges.add(new Edge(points[i], points[j], value));
            }
        }
        edges.sort(Comparator.comparingDouble(o -> o.weight));

        return edges;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y, n, parent));
    }
}

