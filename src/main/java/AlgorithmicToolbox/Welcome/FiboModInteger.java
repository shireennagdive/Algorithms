package AlgorithmicToolbox.Welcome;

import java.util.Scanner;

public class FiboModInteger {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        int m = scan.nextInt();
        if (n <= 1) {
            System.out.println(n);
        } else {
            long[] f = new long[(int) n + 1];
            f[0] = 0;
            f[1] = 1;
            for (int i = 2; i <= n; i++) {
                f[i] = (f[i - 1] + f[i - 2]) % m;
            }
            System.out.println(f[(int) n]);
        }
    }
}//2816213588 30524
//
//0 1 1 2 3 5 8 13 21 34 55 89 134
//mod 3//0 1 1 2 0 2 2 1 0 1 1 1
//add all // 0 1 1 2 0 2 2 1 0 1 1 1