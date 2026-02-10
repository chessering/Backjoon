import java.util.*;
import java.io.*;

public class Main {
	
	static int n, c;
	static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        dp = new int[c + 100];
        Arrays.fill(dp,  Integer.MAX_VALUE);
        long ans = Integer.MAX_VALUE;
        dp[0] = 0;
        
        for (int i = 0 ; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int cost = Integer.parseInt(st.nextToken());
        	int people = Integer.parseInt(st.nextToken());
        	
        	for (int j = people; j < c + 100; j++) {
        		if (dp[j - people] != Integer.MAX_VALUE) {
        			dp[j] = Math.min(dp[j], cost + dp[j - people]);
        		}
        	}
        }
        
        for (int i = c; i < c + 100; i++) {
        	ans = Math.min(ans, dp[i]);
        }
        
        System.out.println(ans);
        
    }

}