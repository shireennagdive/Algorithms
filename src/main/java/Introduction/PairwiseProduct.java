package Introduction;

import java.util.Scanner;

public class PairwiseProduct {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        long x;
        long a[] = new long[n]; //1 3 56 76 23 //1 1  1 1 1 // 1 0 0 0 0  // 0 0 0 0 0  0  //0 0 0 0 1 // 100000 90000 // 1 2 1 2
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
            if (a[i] >= max1) {
                max2 = max1;
                max1 = a[i];
            } else if (a[i] > max2) {
                max2 = a[i];
            }
        }
        x = max1 * max2;
        System.out.println(x);

    }
}
