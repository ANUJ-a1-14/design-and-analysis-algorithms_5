class LCS {
    int value;
    char dir;

    LCS() {
        this.value = 0;
        this.dir = ' ';
    }

    public static void lcs(String a, String b) {
        int m = a.length();
        int n = b.length();
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();

        LCS[][] cost = new LCS[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                cost[i][j] = new LCS();
                if (i == 0 || j == 0) {
                    cost[i][j].value = 0;
                    cost[i][j].dir = ' ';
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    cost[i][j].value = cost[i - 1][j - 1].value + 1;
                    cost[i][j].dir = 'D';
                } else {
                    if (cost[i - 1][j].value >= cost[i][j - 1].value) {
                        cost[i][j].value = cost[i - 1][j].value;
                        cost[i][j].dir = 'U';
                    } else {
                        cost[i][j].value = cost[i][j - 1].value;
                        cost[i][j].dir = 'S';
                    }
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(cost[i][j].value + " " + cost[i][j].dir + "   ");
            }
            System.out.println();
        }

        printLCS(cost, A, m, n);
    }

    private static void printLCS(LCS[][] cost, char[] A, int i, int j) {
    if (i == 0 || j == 0) {
        return; 
    }
    if (cost[i][j].dir == 'D') {
        printLCS(cost, A, i - 1, j - 1);
        System.out.print(A[i - 1]);
    } else if (cost[i][j].dir == 'U') {
        printLCS(cost, A, i - 1, j);
    } else {
        printLCS(cost, A, i, j - 1);
    }
}

}

public class Main {
    public static void main(String[] args) {
        LCS.lcs("EXPONENTIAL", "SEQUENTIAL");
    }
}
