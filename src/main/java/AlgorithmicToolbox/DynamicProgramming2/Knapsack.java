package AlgorithmicToolbox.DynamicProgramming2;

import java.util.Scanner;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int i, j, n = w.length;
        int[][] k = new int[n + 1][W + 1];
        for (j = 0; j <= n; j++) {  //w
            for (i = 0; i <= W; i++) {
                if (j == 0 || i == 0) {
                    k[j][i] = 0;
                } else if (w[j - 1] <= i) {
                    k[j][i] = Integer.max(k[j - 1][i], w[j - 1] + k[j - 1][i - w[j - 1]]);
                } else {
                    k[j][i] = k[j - 1][i];
                }
            }
        }
        return k[n][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

