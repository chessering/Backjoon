import java.io.*;
import java.util.*;

class Solution {

    static int n;
    static int[][] cafe;
    //방향 : 좌하단 -> 우하단 -> 우상단 -> 좌상단 순서
    static int[] dy = {1, 1, -1, -1};
    static int[] dx = {-1, 1, 1, -1};
    static HashSet<Integer> set;
    static int sy, sx;
    static int ans;

    static void dfs(int dir, int y, int x) {

        int limit = (set.size() == 1) ? dir : Math.min(dir + 1, 3);

        // 1. 현재 방향(dir)부터 다음 방향(dir + 1)까지만 고려
        for (int d = dir; d <= limit; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            //기저 조건
            if (ny == sy && nx == sx) {
                ans = Math.max(ans, set.size());
                return;
            }

            if (ny >= 0 && nx >= 0 && ny < n && nx < n) {
                //기존에 없었으면 add 반환값 true
                if (set.add(cafe[ny][nx])) {
                    dfs(d, ny, nx);
                    set.remove(cafe[ny][nx]);
                }
            }
        }

    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            cafe = new int[n][n];
            
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cafe[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            set = new HashSet<>();
            ans = -1;

            //y가 n - 2 이상이면 아래로 탐색 불가
            for (int i = 0; i < n - 2; i++) {
                //x가 양 가장자리면 대각선 탐색 불가
                for (int j = 1; j < n - 1; j++) {
                    set.add(cafe[i][j]);
                    sy = i;
                    sx = j;
                    dfs(0, i, j);
                    set.clear();
                }
            }

            System.out.println("#" + test_case + " " + ans);

        }
    }
}