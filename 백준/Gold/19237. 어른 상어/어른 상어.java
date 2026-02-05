import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[][] arr; // 현재 상어 위치
    static int[][] smellOwner; // 냄새 주인
    static int[][] smellAmount; // 냄새 남은 시간
    static int[] shark_curDir;
    static int[][][] shark_priorityDir;
    
    static int[] dy = {-1, 1, 0, 0}; // 위, 아래, 왼쪽, 오른쪽 (0, 1, 2, 3)
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        smellOwner = new int[n][n];
        smellAmount = new int[n][n];
        shark_curDir = new int[m + 1];
        shark_priorityDir = new int[m + 1][4][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    smellOwner[i][j] = arr[i][j];
                    smellAmount[i][j] = k;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            shark_curDir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int d = 1; d <= m; d++) {
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    shark_priorityDir[d][i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        System.out.println(solve());
    }

    static int solve() {
        for (int time = 0; time < 1000; time++) {
            int[][] nextArr = new int[n][n]; // 이동 후 상어 위치를 임시 저장
            
            // 1. 모든 상어 이동 방향 결정 및 이동
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != 0) {
                        moveShark(i, j, nextArr);
                    }
                }
            }

            // 2. 냄새 시간 감소
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (smellAmount[i][j] > 0) {
                        smellAmount[i][j]--;
                        if (smellAmount[i][j] == 0) smellOwner[i][j] = 0;
                    }
                }
            }

            // 3. 이동한 상어 자리에 새로운 냄새 생성
            arr = nextArr;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != 0) {
                        smellOwner[i][j] = arr[i][j];
                        smellAmount[i][j] = k;
                    }
                }
            }

            // 4. 1번 상어만 남았는지 체크
            if (isOnlyOneLeft()) return time + 1;
        }
        return -1;
    }

    static void moveShark(int y, int x, int[][] nextArr) {
        int num = arr[y][x];
        int curD = shark_curDir[num];
        
        // 1단계: 빈 칸 찾기
        for (int i = 0; i < 4; i++) {
            int nextD = shark_priorityDir[num][curD][i];
            int ny = y + dy[nextD];
            int nx = x + dx[nextD];

            if (ny >= 0 && nx >= 0 && ny < n && nx < n && smellAmount[ny][nx] == 0) {
                updateNextPos(num, ny, nx, nextD, nextArr);
                return;
            }
        }

        // 2단계: 빈 칸 없으면 자기 냄새 칸 찾기
        for (int i = 0; i < 4; i++) {
            int nextD = shark_priorityDir[num][curD][i];
            int ny = y + dy[nextD];
            int nx = x + dx[nextD];

            if (ny >= 0 && nx >= 0 && ny < n && nx < n && smellOwner[ny][nx] == num) {
                updateNextPos(num, ny, nx, nextD, nextArr);
                return;
            }
        }
    }

    static void updateNextPos(int num, int ny, int nx, int nextD, int[][] nextArr) {
        // 이미 다른 상어가 와 있다면 (번호가 작은 상어만 남음)
        if (nextArr[ny][nx] != 0) {
            nextArr[ny][nx] = Math.min(nextArr[ny][nx], num);
        } else {
            nextArr[ny][nx] = num;
        }
        shark_curDir[num] = nextD;
    }

    static boolean isOnlyOneLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] > 1) return false;
            }
        }
        return true;
    }
}