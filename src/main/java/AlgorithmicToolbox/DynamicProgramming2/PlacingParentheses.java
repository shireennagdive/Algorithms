package AlgorithmicToolbox.DynamicProgramming2;

import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {

        int i = 0, L, k, val = 0, j = 0, len = exp.length(), numLen = len / 2 + 1;
        long tempMax = 0, tempMin = 0;
        int num[] = new int[numLen];
        char op[] = new char[numLen - 1];
        while (i < len) {
            char c = exp.charAt(i);
            if (i % 2 != 0) {
                op[val] = c;
                val++;
            } else {
                num[j] = c - '0';
                j++;
            }
            i++;
        }
        long min[][] = new long[numLen][numLen];
        long max[][] = new long[numLen][numLen];
        for (i = 0; i < min.length; i++) {
            for (j = i; j < min.length; j++) {
                if (i == j) {
                    min[i][j] = num[i];
                    max[i][j] = num[i];
                } else {
                    max[i][j] = Long.MIN_VALUE;
                    min[i][j] = Long.MAX_VALUE;
                }
            }
        }
        long tMax;
        for (L = 2; L <= len; L++) {
            for (i = 0; i < numLen - L + 1; i++) {
                j = i + L - 1;
                for (k = i; k < j; k++) {
                    if (op[k] == '+') {
                        tempMin = min[i][k] + min[k + 1][j];
                        tempMax = max[i][k] + max[k + 1][j];
                    } else if (op[k] == '-') {
                        tempMin = min[i][k] - max[k + 1][j];
                        tempMax = max[i][k] - min[k + 1][j];
                    } else if (op[k] == '*') {
                        tMax = min[i][k] * min[k + 1][j];
                        tempMin = Math.min(max[i][k] * min[k + 1][j],
                                Math.min(min[i][k] * max[k + 1][j],
                                        tMax));
                        tempMax = Math.max(max[i][k] * max[k + 1][j], tMax);
                    }
                    if (tempMin < min[i][j]) {
                        min[i][j] = tempMin;
                    }
                    if (tempMax > max[i][j]) {
                        max[i][j] = tempMax;
                    }
                }

            }
        }
        return max[0][len / 2];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

