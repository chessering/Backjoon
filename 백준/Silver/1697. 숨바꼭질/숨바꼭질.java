import java.io.*;
import java.util.*;

public class Main {

    static int time[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (m == 0) {
            System.out.println(n);
            return;
        }

        int max_range = Math.max(n, m);

        Queue<int[]> q = new ArrayDeque<>();
        time = new int[100001];
        Arrays.fill(time, Integer.MAX_VALUE);

        q.add(new int[] {n, 0});

        while(!q.isEmpty()) {
            int cur[] = q.poll();
            int num = cur[0];
            int cnt = cur[1];

            if (num == m) {
                System.out.println(cnt);
                break;
            }

            if (time[num] < cnt) continue;

            if (num > 0) {
                if (time[num - 1] > cnt + 1) {
                    time[num - 1] = cnt + 1;
                    q.add(new int[] {num - 1, cnt + 1});
                }

            }
            if (num < max_range) {
                if (time[num + 1] > cnt + 1) {
                    time[num + 1] = cnt + 1;
                    q.add(new int[] {num + 1, cnt + 1});
                }
            }
            if (num > 0 && num * 2 <= 100000) {
                if (time[num * 2] > cnt + 1) {
                    time[num * 2] = cnt + 1;
                    q.add(new int[] {num * 2, cnt + 1});
                }
            }

        }

    }

}