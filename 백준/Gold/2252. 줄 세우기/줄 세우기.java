import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static List<Integer>[] graph;
	static boolean[] check;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n + 1];
		indegree = new int[n + 1];
		check = new boolean[n + 1];
		
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			indegree[v]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer> ans = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				check[i] = true;
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			check[cur] = true;
			ans.add(cur);
			
			for (int i = 0; i < graph[cur].size(); i++) {
				int nxt = graph[cur].get(i);
				indegree[nxt]--;
				
				if (indegree[nxt] == 0) q.add(nxt);
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if (!check[i]) ans.add(i);
		}
		
		for (int num : ans) System.out.print(num + " ");
		
		
	}
}
