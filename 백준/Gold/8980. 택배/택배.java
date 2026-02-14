import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        Deliver[] orders = new Deliver[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            orders[i] = new Deliver(
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken())
            );
        }

        // 1. 도착 마을(to) 기준 오름차순 정렬
        Arrays.sort(orders, (o1, o2) -> {
            if (o1.to == o2.to) return Integer.compare(o1.from, o2.from);
            return Integer.compare(o1.to, o2.to);
        });

        // 2. 각 마을 구간별 남은 용량 관리
        int[] leftCapacity = new int[n + 1];
        Arrays.fill(leftCapacity, c);

        int totalDelivered = 0;

        for (Deliver order : orders) {
            // 3. 출발지부터 목적지 사이의 구간 중 가장 적게 남은 용량 확인
            int maxLoadable = Integer.MAX_VALUE;
            for (int i = order.from; i < order.to; i++) {
                maxLoadable = Math.min(maxLoadable, leftCapacity[i]);
            }

            // 4. 실제로 실을 수 있는 양 결정 (주문량 vs 구간 여유량)
            int actualLoad = Math.min(order.boxNum, maxLoadable);

            // 5. 구간 용량 차감 및 결과 합산
            for (int i = order.from; i < order.to; i++) {
                leftCapacity[i] -= actualLoad;
            }
            totalDelivered += actualLoad;
        }

        System.out.println(totalDelivered);
    }

    static class Deliver {
        int from, to, boxNum;
        Deliver(int from, int to, int boxNum) {
            this.from = from;
            this.to = to;
            this.boxNum = boxNum;
        }
    }
}