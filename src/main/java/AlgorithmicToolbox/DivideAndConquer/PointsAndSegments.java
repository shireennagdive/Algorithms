package AlgorithmicToolbox.DivideAndConquer;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        TreeSet<Segment> pointsX = new TreeSet<>(Comparator.comparingInt(o -> o.x));
        for (int i = 0; i < starts.length; i++) {
            pointsX.add(new Segment(starts[i], ends[i]));
        }
        for (int i = 0; i < points.length; i++) {
            TreeSet<Segment> pointsSortedByY = new TreeSet<>(Comparator.comparingInt(o -> o.y));
            Segment search = new Segment(points[i], points[i]);
            pointsSortedByY.addAll(pointsX.headSet(search, true));
            cnt[i] = pointsSortedByY.tailSet(search, true).size();
        }
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    private static class Segment {
        int x;
        int y;

        public Segment(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}