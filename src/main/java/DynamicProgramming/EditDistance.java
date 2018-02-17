import java.util.*;

class EditDistance {
    public static int EditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] DP = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    DP[i][j] = j;
                } else if (j == 0) {
                    DP[i][j] = i;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    DP[i][j] = DP[i - 1][j - 1];
                } else {
                    DP[i][j] = 1 + Integer.min(
                            DP[i][j - 1],
                            Integer.min(DP[i - 1][j], DP[i - 1][j - 1]));
                }
            }
        }
        return DP[m][n];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
