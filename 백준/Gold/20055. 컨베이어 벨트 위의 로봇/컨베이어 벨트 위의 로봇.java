import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] belt = new int[n * 2];
        boolean[] robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int level = 0;

        while (true) {
            level++;

            // 1단계: 벨트 회전
            int tmp = belt[n * 2 - 1];
            for (int i = n * 2 - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = tmp;

            for (int i = n - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;
            robot[n - 1] = false;

            // 2단계: 로봇 이동
            for (int i = n - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && belt[i] > 0) {
                    robot[i - 1] = false;
                    robot[i] = true;
                    belt[i]--;
                }
            }
            robot[n - 1] = false;

            // 3단계: 로봇 올리기
            if (!robot[0] && belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
            }

            // 4단계: 내구도 0 체크
            int cnt = 0;
            for (int i = 0; i < n * 2; i++) {
                if (belt[i] == 0) cnt++;
            }

            if (cnt >= k) break;
        }

        System.out.println(level);
    }
}
