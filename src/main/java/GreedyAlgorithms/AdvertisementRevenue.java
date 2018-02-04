package GreedyAlgorithms;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class AdvertisementRevenue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), i;
        BigInteger sum = BigInteger.valueOf(0),product;
        BigInteger a[] = new BigInteger[n];
        BigInteger b[] = new BigInteger[n];
        for (i = 0; i < n; i++) {
            a[i] = scanner.nextBigInteger();
        }
        for (i = 0; i < n; i++) {
            b[i] = scanner.nextBigInteger();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        for (i = 0; i < n; i++) {
            sum=sum.add(a[i].multiply(b[i]));
        }
        System.out.println(sum);
    }
}
