import java.util.*;

public class Solution {
    // 상(0), 하(1), 좌(2), 우(3)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class State {
        int sr, sc, cr, cc, lastDir, time;

        State(int sr, int sc, int cr, int cc, int lastDir, int time) {
            this.sr = sr; this.sc = sc;
            this.cr = cr; this.cc = cc;
            this.lastDir = lastDir;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int TC = sc.nextInt();

        for (int t = 1; t <= TC; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int cr = sc.nextInt(); int cc = sc.nextInt();
            int sr = sc.nextInt(); int scPos = sc.nextInt();

            int result = bfs(N, M, cr, cc, sr, scPos);
            System.out.println("#" + t + " " + result);
        }
    }

    static int bfs(int N, int M, int cr, int cc, int sr, int sc) {
        // visited[신밧드R][신밧드C][사이클롭스R][사이클롭스C][직전 방향]
        boolean[][][][][] visited = new boolean[N + 1][M + 1][N + 1][M + 1][5];
        Queue<State> q = new LinkedList<>();

        // 초기 상태 (lastDir 4는 이전 행동 없음 의미)
        q.add(new State(sr, sc, cr, cc, 4, 0));
        visited[sr][sc][cr][cc][4] = true;

        while (!q.isEmpty()) {
            State curr = q.poll();

            // 탈출 판정: 신밧드가 (1,1)에 안전하게 도달한 상태라면 다음 1초에 탈출 성공
            if (curr.sr == 1 && curr.sc == 1) {
                return curr.time + 1;
            }

            for (int i = 0; i < 4; i++) {
                // 1. 신밧드 이동 (시도 방향 i)
                int nsr = curr.sr + dr[i];
                int nsc = curr.sc + dc[i];
                if (nsr < 1 || nsr > N || nsc < 1 || nsc > M) {
                    nsr = curr.sr; nsc = curr.sc; // 벽이면 제자리
                }

                // 2. 사이클롭스 이동 (신밧드의 직전 시도 lastDir 복사)
                int ncr = curr.cr;
                int ncc = curr.cc;
                if (curr.lastDir != 4) {
                    int tr = curr.cr + dr[curr.lastDir];
                    int tc = curr.cc + dc[curr.lastDir];
                    if (tr >= 1 && tr <= N && tc >= 1 && tc <= M) {
                        ncr = tr; ncc = tc;
                    }
                }

                // 3. 충돌 체크 (이동 후 겹침 & 이동 중 교차)
                if (nsr == ncr && nsc == ncc) continue;
                if (nsr == curr.cr && nsc == curr.cc && ncr == curr.sr && ncc == curr.sc) continue;

                // 4. 방문 체크 및 큐 삽입
                if (!visited[nsr][nsc][ncr][ncc][i]) {
                    visited[nsr][nsc][ncr][ncc][i] = true;
                    q.add(new State(nsr, nsc, ncr, ncc, i, curr.time + 1));
                }
            }
        }
        return -1; // 탈출 불가
    }
}