import java.io.*;
import java.util.*;

public class Main {

    static char[][] arr;
    static boolean[][] visited;
    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void bfs(int s_y, int s_x, char s_color, boolean isnormal) {

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {s_y, s_x});
        visited[s_y][s_x] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx]) continue;

                if (isnormal) {
                    if (arr[ny][nx] == s_color) {
                        visited[ny][nx] = true;
                        q.add(new int[] {ny, nx});
                    }
                } else {
                    if (s_color != 'B') {
                        if (arr[ny][nx] == 'R' || arr[ny][nx] == 'G') {
                            visited[ny][nx] = true;
                            q.add(new int[] {ny, nx});
                        }
                    } else {
                        if (arr[ny][nx] == s_color) {
                            visited[ny][nx] = true;
                            q.add(new int[] {ny, nx});
                        }
                    }
                }


            }
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        //일반인
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, arr[i][j], true);
                    cnt++;
                }
            }
        }

        System.out.print(cnt);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        //적록색약
        cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, arr[i][j], false);
                    cnt++;
                }
            }
        }

        System.out.print(" " + cnt);

    }

}