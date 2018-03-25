package AlgorithmicToolbox.GreedyAlgorithms;

import java.util.Scanner;

public class MoneyChange {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int i = 0, val = 0;
        int coins[] = {10, 5, 1};
        while (m != 0) {
            val += m / coins[i];
            m = m % coins[i];
            i++;
        }
        System.out.println(val);
    }
}
