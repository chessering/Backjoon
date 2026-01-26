import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            
            long[][][] dp = new long[n + 1][n + 1][n + 1];
            dp[1][1][1] = 1;
            
            
            for (int i = 2; i <= n; i++) {
            	for (int j = 1; j <= i; j++) {
                    for (int k = 1; j + k <= n + 1; k++) {
                        dp[i][j][k] = dp[i - 1][j][k - 1] + dp[i - 1][j - 1][k] + dp[i -1][j][k] * (i - 2);
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + dp[n][l][r]);
            
		}
	}
}