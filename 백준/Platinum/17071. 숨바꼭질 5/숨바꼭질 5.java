import java.util.*;
import java.io.*;

public class Main {

    static int[][] arr;
    static int[][] visited;
    static List<Integer> list;
    static int start, end;
    static int ans;
    
    static int bfs(int s) {
    	
    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {s, 0});
    	visited[0][s] = 0;
    	
    	//수빈이의 탐색 먼저 돌리기
    	while(!q.isEmpty()) {
    		int cur[] = q.poll();
    		int pos = cur[0];
    		int cur_t = cur[1];
    		
    		if (pos > 0) {
    			if (visited[(cur_t + 1) % 2][pos - 1] == -1) {
    				visited[(cur_t + 1) % 2][pos - 1] = cur_t + 1;
    				q.add(new int[] {pos - 1, cur_t + 1});
    			}
    		}
    		if (pos < 500000) {
    			if (visited[(cur_t + 1) % 2][pos + 1] == -1) {
    				visited[(cur_t + 1) % 2][pos + 1] = cur_t + 1;
    				q.add(new int[] {pos + 1, cur_t + 1});
    			}
    		}
    		if (pos * 2 <= 500000) {
    			if (visited[(cur_t + 1) % 2][pos * 2] == -1) {
    				visited[(cur_t + 1) % 2][pos * 2] = cur_t + 1;
    				q.add(new int[] {pos * 2, cur_t + 1});
    			}
    		}
    	}
    	
    	//동생 탐색
    	int pos = end;
    	//pos : 현 위치, i : 동생의 시간
    	for (int i = 1; ; i++) {
    		pos += i;
    		if (pos > 500000) break;
    		
    		if (visited[i % 2][pos] != -1) {
    			//동생이 같거나 더 늦게옴
    			if (visited[i % 2][pos] <= i) {
    				return i;
    			}
    		}

    	}
    	
    	return -1;
    }

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	start = Integer.parseInt(st.nextToken());
    	end = Integer.parseInt(st.nextToken());
    	
    	//[홀짝][위치] = 최소 시간
    	visited = new int[2][500001];
    	for (int i = 0; i < 2; i++) Arrays.fill(visited[i], -1);
    	
    	ans = (start == end) ? 0 : bfs(start);

    	System.out.println(ans);
    	
    }
}