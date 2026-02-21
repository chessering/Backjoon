import java.io.*;
import java.util.*;

public class Main {

    static int castle[][];
    static int[] archer;
    static int n, m, d;
    static int min_row;
    static int max_kill;
    static final int INF = Integer.MAX_VALUE;

    //가장 가까운 거리의 몬스터의 위치
    static Monster getTarget(int y, int x, int[][] temp) {

        int op_x = -1;
        int op_y = -1;
        int min_dist = d + 1;

        //x는 오른쪽에서 왼쪽으로 탐색(같은 거리일 때는 왼쪽 우선)
        for (int j = Math.min(m - 1, x + d); j >= Math.max(0, x - d); j--) {
            for (int i = Math.max(0, y - d); i < y; i++) {
                //사정거리 밖이거나 몬스터 없으면 패스
                int dist = Math.abs(y - i) + Math.abs(x - j);
                if (temp[i][j] == 0 || dist > d) continue;
                //거리 최솟값 갱신
                if (dist <= min_dist) {
                    op_y = i;
                    op_x = j;
                    min_dist = dist;
                }
            }
        }     

        return new Monster(op_y, op_x, min_dist);
    }

    //디펜스 시뮬레이션 실행
    static void defense() {

        int kill = 0;
        //castle 복사본
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) temp[i] = castle[i].clone();
        
        //가장 위에 있는 적 위치만큼 실행
        for (int t = n - 1; t >= min_row; t--) {
            Monster[] target = new Monster[3];
            //궁수에서 가장 가까운 몬스터 찾기
            for (int i = 0; i < 3; i++) {
                int arrow_y = n;
                int arrow_x = archer[i];
                target[i] = getTarget(arrow_y, arrow_x, temp);
            }

            //몬스터 죽이기
            for (int i = 0; i < 3; i++) {
                //범위 내 몬스터 없는 경우
                if (target[i].x == -1) continue;
                //처음 쏜 경우만 kill + 1
                if (temp[target[i].y][target[i].x] != 0) kill++;
                temp[target[i].y][target[i].x] = 0;
            }

            //배열 아래로 한칸씩 내리기
            for (int i = n - 1; i > 0; i--) {
                temp[i] = temp[i - 1].clone();
            }
            temp[0] = new int[m];
        }

        max_kill = Math.max(max_kill, kill);

    }
    //궁수 셋의 위치 확정
    static void combination(int idx, int cnt) {
        if (cnt == 3) {
            defense();
            return;
        }

        for (int i = idx; i < m; i++) {
            archer[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        castle = new int[n][m];
        archer = new int[3];
        min_row = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                castle[i][j] = Integer.parseInt(st.nextToken());
                if (castle[i][j] == 1) {
                    min_row = Math.min(min_row, i);
                }
            }
        }

        //1. 궁수 셋을 배치하는 경우의 수 : mC3
        //2. 배치한 이후, 각 궁수별로 가장 가까운 적 선별
        max_kill = 0;
        combination(0, 0);
        System.out.println(max_kill);
    }

    static class Monster {
        int y;
        int x;
        int dist;
        Monster(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
}