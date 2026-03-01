import java.io.*;
import java.util.*;

class Solution {

    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static List<int[]> core;
    static int max_len, max_cnt;

    static boolean canConnect(int y, int x, int dir) {
        //왼쪽 아래쪽 오른쪽 위쪽 순
        int ny = y;
        int nx = x;

        while (true) { 
            ny += dy[dir];
            nx += dx[dir];
            
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) break;

            if (arr[ny][nx] == 1 || visited[ny][nx]) return false;
        }
        return true;
    }
    static void getwire(int y, int x, int dir, boolean install) {
        int ny = y;
        int nx = x;
        while (true) { 
            ny += dy[dir];
            nx += dx[dir];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) break;
            visited[ny][nx] = install;
        }
    }
    
    static int getLen(int y, int x, int dir) {
        //왼쪽 아래쪽 오른쪽 위쪽 순
        if (dir == 0) return x;
        else if (dir == 1) return n - y - 1;
        else if (dir == 2) return n - x - 1;
        else return y;
    }

    static void dfs(int idx, int cnt, int len) {

        if (max_cnt > cnt + (core.size() - idx)) return;

        if (idx == core.size()) {
            if (cnt > max_cnt) {
                max_cnt = cnt;
                max_len = len;
            } else if (cnt == max_cnt) {
                max_len = Math.min(max_len, len);
            }
            return;
        }

        for (int i = idx; i < core.size(); i++) {
            for (int d = 0; d < 4; d++) {
                if (canConnect(core.get(i)[0], core.get(i)[1], d)) {
                    getwire(core.get(i)[0], core.get(i)[1], d, true);
                    dfs(i + 1, cnt + 1, len + getLen(core.get(i)[0], core.get(i)[1], d));
                    getwire(core.get(i)[0], core.get(i)[1], d, false);
                }
            }
            dfs(i + 1, cnt, len);
        }

    }

	public static void main(String args[]) throws IOException	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            arr = new int[n][n];
            core = new ArrayList<>();
            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        if (i == 0 || j == 0 || i == n - 1 || j == n - 1) max_cnt++;
                        else core.add(new int[] {i, j});
                    }
                }
            }

            if (core.isEmpty()) {
                System.out.println("#" + test_case + " " + max_len);
            }

            max_cnt = 0;
            max_len = Integer.MAX_VALUE;

            dfs(0, 0, 0);
            System.out.println("#" + test_case + " " + max_len);

		}
	}
}