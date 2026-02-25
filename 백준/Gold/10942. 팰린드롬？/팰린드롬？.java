import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

        dp = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i <= n - 1; i++) {
            if (arr[i] == arr[i + 1]) dp[i][i + 1] = true;
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1; // 구간의 끝 인덱스
                // 양 끝이 같고, 그 사이(i+1 ~ j-1)가 팰린드롬이면 true
                if (arr[i] == arr[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (dp[start][end]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb);

    }

}