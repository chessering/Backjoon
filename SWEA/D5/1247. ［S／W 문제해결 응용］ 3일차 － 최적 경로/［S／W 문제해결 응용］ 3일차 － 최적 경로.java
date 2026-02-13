import java.util.*;
import java.io.*;

class Solution
{
	
	static int[][] arr;
	static boolean[] visited;
	static int n;
	static int minDist;
	
	static void dfs(int curIdx, int cnt, int dist) {
		
		if (dist > minDist) return;
		
		if (cnt == n) {
			int finalDist = dist + getDist(curIdx, 0); // 마지막 거리에서 회사까지 가는 거리 추가
			minDist = Math.min(minDist, finalDist);
			return;
		}
		
		
		for (int i = 2; i <= n + 1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i, cnt + 1, dist + getDist(curIdx, i));//집에서 i번째 손님까지의 거리
				visited[i] = false;
			}
		}
		
	}
	
	static int getDist(int i, int j) {
		return Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);
	}
	
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            
            arr = new int[n + 2][2];
            visited = new boolean[n + 2];
            minDist = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine(), " ");
            
            //회사
            arr[0][1] = Integer.parseInt(st.nextToken());
            arr[0][0] = Integer.parseInt(st.nextToken());
            //집
            arr[1][1] = Integer.parseInt(st.nextToken());
            arr[1][0] = Integer.parseInt(st.nextToken());
            
            for (int i = 2; i < n + 2; i++) {
            	arr[i][1] = Integer.parseInt(st.nextToken()); // x
            	arr[i][0] = Integer.parseInt(st.nextToken()); // y
            }
            

            dfs(1, 0, 0); // 집에서 출발
            
            System.out.println("#" + test_case + " " + minDist);
            
		}
	}
}