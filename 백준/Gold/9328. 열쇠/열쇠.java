import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static char[][] building;
	static boolean[][] visited;
	static List<int[]>[] doors;
	static List<int[]> start;
	static boolean[] key;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {-1, 0, 1, 0};
	static int doc;
	
	static void bfs() {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		for (int[] s : start) {
			q.add(s);
		}
		
		while(!q.isEmpty()) {
			
			int cur[] = q.poll();
			int y = cur[0];
			int x = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if (ny < 0 || nx < 0 || ny >= n || nx >= m || building[ny][nx] == '*' || visited[ny][nx]) continue;
				
				//문서 획득
				if (building[ny][nx] == '$') {
					doc++;
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
				//키 획득
				if (building[ny][nx] >= 'a' && building[ny][nx] <= 'z') {
					int keyIdx = building[ny][nx] - 'a';
					//키를 처음 얻은 경우 -> 막힌 문들 다시 탐색
					if (!key[keyIdx]) {
						key[keyIdx] = true;
						for (int[] pos : doors[keyIdx]) {
							if (!visited[pos[0]][pos[1]]) {
								visited[pos[0]][pos[1]] = true;
								q.add(pos);
							}
						}
					}
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
				//문일 때
				if (building[ny][nx] >= 'A' && building[ny][nx] <= 'Z') {
					int doorIdx = building[ny][nx] - 'A';
					
					//key가 있어 오픈 가능한 경우
					if (key[doorIdx]) {
						visited[ny][nx] = true;
						q.add(new int[] {ny, nx});
					} else {
						doors[doorIdx].add(new int[] {ny, nx});
					}
				}
				else if (building[ny][nx] == '.') {
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
				
			}
		}
		
	}

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        int T = Integer.parseInt(st.nextToken());
        
        for (int test_case = 1; test_case <= T; test_case++) {
        	st = new StringTokenizer(br.readLine());
        	n = Integer.parseInt(st.nextToken());
        	m = Integer.parseInt(st.nextToken());
        	
        	building = new char[n][m];
        	key = new boolean[26];
        	visited = new boolean[n][m];
        	doors = new ArrayList[26];
        	start = new ArrayList<>();
        	
        	for (int i = 0; i < 26; i++) doors[i] = new ArrayList<>();
        	
        	for (int i = 0; i < n; i++) {
        		String s = br.readLine();
        		building[i] = s.toCharArray();
        	}
        	
        	String k = br.readLine();
        	if (!k.equals("0")) {
        		for (int i = 0; i < k.length(); i++) {
        			key[k.charAt(i) - 'a'] = true;
        		}
        	}
        	
        	doc = 0;
        	
        	//테두리 탐색 -> 진입 가능한 곳 체크
        	for (int i = 0; i < n; i++) {
        		for (int j = 0; j < m; j++) {
        			if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
        				if (building[i][j] >= 'A' && building[i][j] <= 'Z') {
        					//테두리가 문인 경우
        					int dIdx = building[i][j] - 'A';
        					if (key[dIdx]) {
        						visited[i][j] = true;
        						start.add(new int[] {i, j});
        					} else {
        						doors[dIdx].add(new int[] {i, j});
        					}
        				}
        				
        				else if (building[i][j] != '*'){
        					if (building[i][j] >= 'a' && building[i][j] <= 'z') key[building[i][j] - 'a'] = true;
        					if (building[i][j] == '$') doc++;
        					visited[i][j] = true;
        					start.add(new int[] {i, j});
        				}
        			}
        		}
        	}
        	bfs();
        	
        	System.out.println(doc);
        }
        
        
    }

}