import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static boolean visited[];
    static int n, m;
    static int a, b;
    static int ans;

    static int traverse(int node, int cnt) {

        if (node == b) {
            return cnt;
        }
        
        for (int v : graph[node]) {
            if (!visited[v]) {
                visited[v] = true;
                int result = traverse(v, cnt + 1);
                if (result != -1) return result;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        visited[a] = true; 

        System.out.println(traverse(a, 0));

    }
}