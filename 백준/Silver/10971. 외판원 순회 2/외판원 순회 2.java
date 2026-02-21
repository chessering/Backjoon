import java.io.*;
import java.util.*;

public class Main {

    static int[][] w;
    static int[][] d;
    static final int INF = Integer.MAX_VALUE;
    static int n;

    static int tsp(int i, int a) {

        //d[i][a]를 미리 구했었다면 저장된 값 반환
        if (d[i][a] != -1) return d[i][a];

        int minCost = INF;

        for (int j = 1; j < n; j++) {

            //1번 도시부터 (n - 1)번 도시 중 a에 속한 도시만 고려
            if ((a & (1 << j)) == 0) continue;

            //prev = d[j][a - {j}]
            int prev = tsp(j, a & ~(1 << j));

            if (prev == INF || w[i][j] == 0) continue;

            minCost = Math.min(minCost, w[i][j] + prev);
        }
        return d[i][a] = minCost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //d[i][a] = 0번 도시에서 부분집합 a에 속한 도시를 거친 후 i번 도시로 오는 최소비용
        d = new int[n][1 << n];

        //-1로 초기화
        for (int i = 0; i < n; i++)
            Arrays.fill(d[i], -1);

        d[0][0] = 0;

        //초기값 설정
        //다른 도시에서 출발점(0)으로 가는 경우를 미리 계산
        for (int i = 1; i < n; i++) {
            d[i][0] = (w[i][0] == 0) ? INF : w[i][0];
        }

        System.out.println(tsp(0, (1 << n) - 2));
        
    }
}