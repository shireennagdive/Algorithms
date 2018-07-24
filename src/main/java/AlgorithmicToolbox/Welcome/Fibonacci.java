package AlgorithmicToolbox.Welcome;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n <= 1) {
            System.out.println(n);
        } else {
            int[] f = new int[n + 1];
            f[0] = 0;
            f[1] = 1;
            for (int i = 2; i <= n; i++) {
                f[i] = f[i - 1] + f[i - 2];
            }
            System.out.println(f[n]);
        }
    }
}
//0 1 1 2 3 5 8 13 21 34 55