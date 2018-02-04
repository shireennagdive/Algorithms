package Welcome;

import java.math.BigInteger;
import java.util.Scanner;

public class LCM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int gcd = a> b ? gcd(a,b) : gcd(b,a);
        BigInteger lcm1 = BigInteger.valueOf(a/gcd).multiply(BigInteger.valueOf(b));
        System.out.println(lcm1);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
