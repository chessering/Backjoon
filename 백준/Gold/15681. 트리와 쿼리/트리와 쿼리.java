import java.io.*;
import java.util.*;

public class Main {

    static int n, r, q;
    static List<Integer>[] indirected_graph;
    static boolean[] visited;
    static int[] dp;

    static int dfs(int node) {

        if (dp[node] != 0) return dp[node];

        int count = 1;

        for (int i : indirected_graph[node]) {
            if (!visited[i]) {
                visited[i] = true;
                count += dfs(i);
            }
        }
        return dp[node] = count;
    }

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        indirected_graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) indirected_graph[i] = new ArrayList<>();
        dp = new int[n + 1];
        visited = new boolean[n + 1];
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            indirected_graph[u].add(v);
            indirected_graph[v].add(u);
        }

        visited[r] = true;
        dfs(r);

        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine().trim());

            System.out.println(dp[num]);
        }
    }
}