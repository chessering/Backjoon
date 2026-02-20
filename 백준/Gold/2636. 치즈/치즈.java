import java.util.*;
import java.io.*;

public class Main {

	static int[][] arr;
	static boolean[][] check;
    static int n, m;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int ans, time;
    
    static boolean bfs() {
    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {0, 0});
    	check[0][0] = true;
    	int cnt = 0;
    	time++;
    	
    	while(!q.isEmpty()) {
    		int cur[] = q.poll();
    		int y = cur[0];
    		int x = cur[1];
    		
    		for (int i = 0; i < 4; i++) {
    			int ny = y + dy[i];
    			int nx = x + dx[i];
    			
    			if (ny < 0 || nx < 0 || ny >= n || nx >= m || check[ny][nx]) continue;
    			check[ny][nx] = true;
    			
    			if (arr[ny][nx] == 0) q.add(new int[] {ny, nx});
    			else {
    				arr[ny][nx] = 0;
    				cnt++;
    			}
    		}
    	}
    	
    	if (cnt == 0) {
    		System.out.println(time - 1);
    		System.out.println(ans);
    		return true;
    	} else {
    		ans = cnt;
    		return false;
    	}
    	
    	
    }

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n][m];
    	check = new boolean[n][m];
    	
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < m; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	time = 0;
    	while(true) {
    		if (bfs()) break;
    		for (int i = 0; i < n; i++) Arrays.fill(check[i], false);
    	}

    }
}