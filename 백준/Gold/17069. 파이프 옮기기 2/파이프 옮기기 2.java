import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] arr;
    static long[][][] dp;



    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //방향 : 0(가로) 1(세로) 2(대각)
        dp = new long[n][n][3];
        dp[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (arr[i][j] == 1) continue;
                //가로 방향에서 왔을 때
                if (j >= 1) {
                    dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
                }
                //세로 방향에서 왔을 때
                if (i >= 1) {
                    dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
                }
                //가로 방향에서 왔을 때
                if (i >= 1 && j >= 1) {
                    if (arr[i][j - 1] == 0 && arr[i - 1][j] == 0)
                        dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }

            }
        }
        

        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);

    }
}