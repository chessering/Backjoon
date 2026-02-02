import java.io.*;
import java.util.*;

public class Main {

    static char[][] board = new char[11][11];
    static boolean[][][][] visit = new boolean[11][11][11][11];
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int n, m;

    static class State {
        int ry, rx, by, bx, cnt;

        State(int ry, int rx, int by, int bx, int cnt) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.cnt = cnt;
        }
    }

    static int bfs(int ry, int rx, int by, int bx) {
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(ry, rx, by, bx, 0));
        visit[ry][rx][by][bx] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.cnt >= 10) continue;

            for (int i = 0; i < 4; i++) {
                int nry = cur.ry;
                int nrx = cur.rx;
                int nby = cur.by;
                int nbx = cur.bx;

                int rdist = 0, bdist = 0;

                // 빨간 구슬 이동
                while (board[nry + dy[i]][nrx + dx[i]] != '#' && board[nry][nrx] != 'O') {
                    nry += dy[i];
                    nrx += dx[i];
                    rdist++;
                }

                // 파란 구슬 이동
                while (board[nby + dy[i]][nbx + dx[i]] != '#' && board[nby][nbx] != 'O') {
                    nby += dy[i];
                    nbx += dx[i];
                    bdist++;
                }

                // 파란 구슬이 구멍에 빠지면 실패
                if (board[nby][nbx] == 'O') continue;

                // 빨간 구슬만 구멍에 빠지면 성공
                if (board[nry][nrx] == 'O') {
                    return cur.cnt + 1;
                }

                // 같은 위치에 있을 경우 이동 거리 비교
                if (nry == nby && nrx == nbx) {
                    if (rdist > bdist) {
                        nry -= dy[i];
                        nrx -= dx[i];
                    } else {
                        nby -= dy[i];
                        nbx -= dx[i];
                    }
                }

                if (visit[nry][nrx][nby][nbx]) continue;
                visit[nry][nrx][nby][nbx] = true;
                q.add(new State(nry, nrx, nby, nbx, cur.cnt + 1));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int Ry = 0, Rx = 0, By = 0, Bx = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    Ry = i;
                    Rx = j;
                } else if (board[i][j] == 'B') {
                    By = i;
                    Bx = j;
                }
            }
        }

        int result = bfs(Ry, Rx, By, Bx);
        System.out.println(result);
    }
}
