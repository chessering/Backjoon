import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	
	static int[] cost;
	static int[] month;
	static int[] month_plus;
	static int ans;
	
	static void dfs(int month_num, int sum) {
	    if (month_num >= 12) {
	        ans = Math.min(ans, sum);
	        return;
	    }

	    // 1일권
	    dfs(month_num + 1, sum + month[month_num] * cost[0]);

	    // 1달권
	    dfs(month_num + 1, sum + cost[1]);

	    // 3달권
	    dfs(month_num + 3, sum + cost[2]);
	}

	
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			cost = new int[4];
			month = new int[12];
			month_plus = new int[] {1, 1, 3};
			
			for (int i = 0; i < 4; i++) cost[i] = sc.nextInt();
			for (int i = 0; i < 12; i++) month[i] = sc.nextInt();
			
			ans = cost[3];
			
			dfs(0, 0);
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
}