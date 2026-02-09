import java.io.*;
import java.util.*;

class Solution
{
	
	static int[][] map;
	static boolean[][] visited;
	static List<Point> core;
	static int n;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {-1, 0, 1, 0};
	static int sum, max_cnt ;
	
	
	static void dfs(int idx, int cnt, int len) {
		
		if (cnt + (core.size() - idx) < max_cnt) return;

		if (idx == core.size()) {
			if (max_cnt == cnt) sum = Math.min(sum, len);
			else if (max_cnt < cnt) {
				max_cnt = cnt;
				sum = len;
			}
			return;
		}
		
		//4방향 탐색
		for (int i = 0; i < 4; i++) {
			int ny = core.get(idx).y + dy[i];
			int nx = core.get(idx).x + dx[i];

			boolean okay = true;
			
			while(ny >= 0 && nx >= 0 && ny < n && nx < n) {
				if (map[ny][nx] == 1 || visited[ny][nx] == true) {
					okay = false;
					break;
				}
				visited[ny][nx] = true;
				ny += dy[i];
				nx += dx[i];
			}
			ny -= dy[i];
			nx -= dx[i];
			int line = Math.max(Math.abs(ny - core.get(idx).y), Math.abs(nx - core.get(idx).x));
			if (okay) dfs(idx + 1, cnt + 1, len + line);
			//방문체크 해제
			while(ny != core.get(idx).y || nx != core.get(idx).x) {
				visited[ny][nx] = false;
				ny -= dy[i];
				nx -= dx[i];
			}
		}
		dfs(idx + 1, cnt, len);
		
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
			map = new int[n][n];
			visited = new boolean[n][n];
			core = new ArrayList<Point>();
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
							max_cnt++;
						}
						core.add(new Point(i, j));
					}
				}
			}

			sum = Integer.MAX_VALUE; max_cnt = 0;
			
			
			dfs(0, 0, 0);
			
			System.out.println("#" + test_case + " " + sum);
			
		}
	}
	
	static class Point{
		int y;
		int x;
		
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}