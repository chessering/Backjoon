import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static long[][][] dp;

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        n = Integer.parseInt(st.nextToken());

        dp = new long[n + 1][10][1025];

        for (int i = 1; i < 10; i++) {
            int bit = 1 << i;
            dp[1][i][bit] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (dp[i - 1][j][k] == 0)
                        continue;

                    if (j > 0) {
                        int bit = k | (1 << (j - 1));
                        dp[i][j - 1][bit] += dp[i - 1][j][k];
                        dp[i][j - 1][bit] %= 1000000000;
                    }
                    if (j < 9) {
                        int bit = k | (1 << (j + 1));
                        dp[i][j + 1][bit] += dp[i - 1][j][k];
                        dp[i][j + 1][bit] %= 1000000000;
                    }
                }
            }
        }

        long sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += dp[n][i][1023];
            sum %= 1000000000;
        }

        System.out.println(sum);
    	
    }

}