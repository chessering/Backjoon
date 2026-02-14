import java.util.Scanner;

public class Main {
    static int[][] map;
    static int cnt = 0; // 타일 번호를 매길 카운터

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = 1 << K; // 2^K 계산
        
        // 문제의 좌표 (x, y)는 좌하단이 (1, 1)
        // 이를 배열 인덱스에 맞게 변환 (y는 행, x는 열)
        int x = sc.nextInt();
        int y = sc.nextInt();

        map = new int[N][N];
        // 빈칸(배수구)은 -1로 설정
        map[N - y][x - 1] = -1; 

        solve(N, 0, 0);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 해당 영역에 빈칸(혹은 이미 채워진 타일)이 있는지 체크
    static boolean check(int size, int r, int c) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

    static void solve(int size, int r, int c) {
        if (size == 1) return;

        cnt++; // 새로운 타일 번호
        int s = size / 2;

        // 1사분면 (좌상)
        if (check(s, r, c)) map[r + s - 1][c + s - 1] = cnt;
        // 2사분면 (우상)
        if (check(s, r, c + s)) map[r + s - 1][c + s] = cnt;
        // 3사분면 (좌하)
        if (check(s, r + s, c)) map[r + s][c + s - 1] = cnt;
        // 4사분면 (우하)
        if (check(s, r + s, c + s)) map[r + s][c + s] = cnt;

        // 재귀 호출
        solve(s, r, c);
        solve(s, r, c + s);
        solve(s, r + s, c);
        solve(s, r + s, c + s);
    }
}