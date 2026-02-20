import java.util.*;
import java.io.*;

public class Main {

    static List<Integer>[] graph;
    static int[] indegree;
    static int n, m;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	graph = new ArrayList[n + 1];
    	for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
    	indegree = new int[n + 1];
    	
    	for (int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int num = Integer.parseInt(st.nextToken());
    		int first = Integer.parseInt(st.nextToken());
    		for (int j = 0; j < num - 1; j++) {
    			int node = Integer.parseInt(st.nextToken());
    			graph[first].add(node);
    			indegree[node]++;
    			first = node;
    		}
    	}
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	List<Integer> list = new ArrayList<>();
    	
    	for (int i = 1; i <= n; i++) {
    		if (indegree[i] == 0) {
    			q.add(i);
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		if (indegree[cur] == 0) {
    			list.add(cur);
    		}
    		
    		for (int node : graph[cur]) {
    			indegree[node]--;
    			if (indegree[node] == 0) q.add(node);
    		}
    	}
    	
    	for (int i = 1; i <= n; i++) {
    		if (indegree[i] > 0) {
    			System.out.println(0);
    			return;
    		}
    	}
    	
    	for (int num : list) System.out.println(num);

    }
}