
import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int money) {
        //write your code here
        int minCount[] = new int[money + 1], m, temp, j;
        int coins[] = {1, 3, 4};
        for (m = 1; m <= money; m++) {
            minCount[m] = Integer.MAX_VALUE;
            for (j = 0; j < 3; j++) {
                if (m >= coins[j]) {
                    temp = minCount[m - coins[j]] + 1;
                    if (temp < minCount[m]) {
                        minCount[m] = temp;
                    }
                }
            }
        }
        return minCount[money];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

