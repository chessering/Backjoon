import java.util.*;
import java.io.*;

class Solution
{
	
	static int n;
	static int[] arr;
	static boolean[] visited;
	static int totWeight;
	static int[] fact;
	static int[] power;
	static int ans;
	

	static void dfs(int cnt, int left, int right, int mask) {
		if (cnt == n) {
			ans++;
			return;
		}
		
		if (left >= totWeight - left) {
			ans += power[n - cnt] * fact[n - cnt];
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if ((mask & (1 << i)) == 0) {
				dfs(cnt + 1, left + arr[i], right, mask | (1 << i));
				
				if (right + arr[i] <= left) {
					dfs(cnt + 1, left, right + arr[i], mask | (1 << i));
				}
			}
		}
		
	}
	
	public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		fact = new int[10];
		power = new int[10];
		fact[0] = 1;
		power[0] = 1;
		for (int i = 1; i <= 9; i++) {
			fact[i] = fact[i - 1] * i;
			power[i] = power[i - 1] * 2;
		}
		
		for(int test_case = 1; test_case <= T; test_case++)	{
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
					
			arr = new int[n];
			visited = new boolean[n];
			
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < n; i++) {
	            arr[i] = Integer.parseInt(st.nextToken());
	            totWeight += arr[i];
	        }
			
			ans = 0;
			
			dfs(0, 0, 0, 0);

            System.out.println("#" + test_case + " " + ans);

		}
	}
}