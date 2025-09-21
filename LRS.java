import java.util.Scanner;

public class LRS {
    static class Cell {
        int length;
        char direction;

        Cell(int length, char direction) {
            this.length = length;
            this.direction = direction;
        }
    }

    public static void findLRS(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        Cell[][] dp = new Cell[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = new Cell(0, 'H');
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars[i - 1] == chars[j - 1] && i != j) {
                    dp[i][j].length = dp[i - 1][j - 1].length + 1;
                    dp[i][j].direction = 'D';
                } else {
                    dp[i][j].length = Math.max(dp[i][j - 1].length, dp[i - 1][j].length);
                    if (dp[i - 1][j].length >= dp[i][j - 1].length) {
                        dp[i][j].direction = 'U';
                    } else {
                        dp[i][j].direction = 'L';
                    }
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j].length + " " + dp[i][j].direction + " ");
            }
            System.out.println();
        }

        System.out.println("Length of LRS is: " + dp[n][n].length);
        System.out.println("Repeating Subsequence is:");
        printSubsequence(chars, dp, n, n);
    }

    static void printSubsequence(char[] chars, Cell[][] dp, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (dp[i][j].direction == 'D') {
            printSubsequence(chars, dp, i - 1, j - 1);
            System.out.print(chars[i - 1]);
        } else if (dp[i][j].direction == 'U') {
            printSubsequence(chars, dp, i - 1, j);
        } else {
            printSubsequence(chars, dp, i, j - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        findLRS(input);
    }
}
