import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static List<Node>[] graph;
    static List<Node>[] reverse_graph;
    static int[] dp;
    static int[] indegree;
    static boolean[] visited;
    static int start, end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        indegree = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<Node>();
        reverse_graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) reverse_graph[i] = new ArrayList<Node>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            reverse_graph[v].add(new Node(u, w));
            indegree[v]++;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.add(i);
        }
        dp[start] = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for (Node node : graph[cur]) {
                indegree[node.num]--;
                if (indegree[node.num] == 0) q.add(node.num);

                dp[node.num] = Math.max(dp[node.num], dp[cur] + node.weight);
            }
        }
        System.out.println(dp[end]);        
        
        Queue<Integer> rq = new ArrayDeque<>();
        rq.add(end);
        visited = new boolean[n + 1];
        visited[end] = true;

        int roadCount = 0;
        while (!rq.isEmpty()) {
            int cur = rq.poll();

            for (Node next : reverse_graph[cur]) {
                // 임계 경로에 포함되는 도로인지 확인
                if (dp[cur] == dp[next.num] + next.weight) {
                    roadCount++;
                    
                    if (!visited[next.num]) {
                        visited[next.num] = true;
                        rq.add(next.num);
                    }
                }
            }
        }

        System.out.println(roadCount);

    }

    static class Node {
        int num;
        int weight;
        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}