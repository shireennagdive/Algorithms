package GreedyAlgorithms;

import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.Math.floor;

public class MaximumPrizes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigInteger N = scan.nextBigInteger();
        int i;
        BigInteger prod = N.multiply(BigInteger.valueOf(8)).add(BigInteger.valueOf(9));
        double QVal = ((Math.sqrt((prod.doubleValue())) - 3) / 2);
        int n = (int) floor(QVal);

        if (n >= QVal) {
            n = (int) QVal - 1;
        }

        BigInteger r = (N.subtract(BigInteger.valueOf(n * (n + 1) / 2)));
        System.out.println(n + 1);
        for (i = 1; i < n + 2; i++) { //
            if (i == n + 1) {
                System.out.print(r);
            } else {
                System.out.print(i + " ");
            }
        }

    }
}

