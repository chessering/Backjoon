import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<int[]>[] list;
    static int[] parent;
    static int ans;

    static int findParent(int x) {
        if (x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    static boolean union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a == b) return false;
        if (a < b) parent[b] = a;
        else parent[a] = b;
        return true;
    }

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) parent[i] = i;

        list = new ArrayList[3];
        for (int i = 0; i < 3; i++) list[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                list[j].add(new int[] {i, Integer.parseInt(st.nextToken())});
            }
        }

        for (int i = 0; i < 3; i++) {
            Collections.sort(list[i], (a, b) -> Integer.compare(a[1], b[1]));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 3; j++)
                pq.add(new int[] {list[j].get(i)[0], list[j].get(i + 1)[0], 
                        Math.abs(list[j].get(i)[1] - list[j].get(i + 1)[1])});
        }

        int maxDist = 0;

        while (!pq.isEmpty()) {
            int cur[] = pq.poll();
            int u = cur[0];
            int v = cur[1];
            int dist = cur[2];

            if (union(u, v)) {
                maxDist += dist;
            }
        }
        
        System.out.println(maxDist);
    }
}