import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static char[][] arr;
	static int[][] fireDist;
	static boolean[][] visited;
	static List<int[]> fire;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {-1, 0, 1, 0};
	static int sy, sx;
	static int ans;
	
	static void bfs() {
		
		Queue<int[]> fire_q = new ArrayDeque<>();
		
		for (int[] f : fire) {
			fire_q.add(new int[] {f[0], f[1]});
		}
		
		//불이 각 칸에 도달하는 최단시간
		while(!fire_q.isEmpty()) {
			int cur[] = fire_q.poll();
			int y = cur[0];
			int x = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if (ny < 0 || nx < 0 || ny >= n || nx >= m || arr[ny][nx] == '#') continue;
				
				if (fireDist[ny][nx] > fireDist[y][x] + 1) {
					fireDist[ny][nx] = fireDist[y][x] + 1;
					fire_q.add(new int[] {ny, nx});
				}
			}
		}
		
		Queue<int[]> escape = new ArrayDeque<>();
		escape.add(new int[] {sy, sx, 0});
		visited[sy][sx] = true;
		
		//지훈이가 도달하는 최단시간과 불 최단시간 비교
		while(!escape.isEmpty()) {
			int cur[] = escape.poll();
			int y = cur[0];
			int x = cur[1];
			int t = cur[2];
			
			if (y == 0 || x == 0 || y == n - 1 || x == m - 1) {
				ans = t + 1;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				int nxt_t = t + 1;
				
				if (ny < 0 || nx < 0 || ny >= n || nx >= m || arr[ny][nx] == '#' || visited[ny][nx]) continue;
				
				if (fireDist[ny][nx] > nxt_t) {
					visited[ny][nx] = true;
					escape.add(new int[] {ny, nx, nxt_t});
				}
			}
		}
		
	}

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        fireDist = new int[n][m];
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) Arrays.fill(fireDist[i], Integer.MAX_VALUE);
        
        sy = 0;
        sx = 0;
        fire = new ArrayList<>();
        
        for (int i = 0; i < n; i++) { 
        	String s = br.readLine();
        	arr[i] = s.toCharArray();
        	for (int j = 0; j < m; j++) {
        		if (arr[i][j] == 'J') {
        			sy = i;
        			sx = j;
        		} else if (arr[i][j] == 'F') {
        			fire.add(new int[] {i, j});
        			fireDist[i][j] = 0;
        		}
        	}
        }

        
        ans = -1;
        //시작점이 
        if (sy == 0 || sx == 0 || sy == n - 1 || sx == m - 1) {
        	System.out.println(1);
        	return;
        }
        
        else bfs();
        
        if (ans != -1) System.out.println(ans);
        else System.out.println("IMPOSSIBLE");
    }

}