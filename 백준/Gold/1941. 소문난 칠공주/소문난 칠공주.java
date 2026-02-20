import java.util.*;
import java.io.*;

public class Main {

	static char[][] arr;
	static int[] selected;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int ans;
    
    static boolean isConnected() {
    	//전체 리스트 중 선택된 것
        boolean[] isSelected = new boolean[25];
        for (int i : selected) isSelected[i] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(selected[0]);
        //선택된 리스트 중 방문한 것
        boolean[] visited = new boolean[25];
        visited[selected[0]] = true;

        int connectCount = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / 5;
            int x = cur % 5;

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                int next = ny * 5 + nx;

                if (ny >= 0 && nx >= 0 && ny < 5 && nx < 5) {
                    // 선택된 칸이고, 아직 방문하지 않았다면
                    if (isSelected[next] && !visited[next]) {
                        visited[next] = true;
                        q.add(next);
                        connectCount++;
                    }
                }
            }
        }
        return connectCount == 7; // 7개가 모두 연결되어 있으면 true
    }
    
    //가능한 7개의 칸 뽑기
    static void findCombi(int idx, int cnt, int s_cnt) {
    	
    	if (cnt == 7) {
    		if (s_cnt >= 4) {
    			if (isConnected()) ans++;
    		}
    		return;
    	}
    	
    	for (int i = idx; i < 25; i++) {
    		selected[cnt] = i;
    		findCombi(i + 1, cnt + 1, s_cnt + (arr[i / 5][i % 5] == 'S' ? 1 : 0));
    	}
    	
    }

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	arr = new char[5][5];
    	selected = new int[7];
    	
    	for (int i = 0; i < 5; i++) {
    		String s = br.readLine();
    		for (int j = 0; j < 5; j++) {
    			arr[i][j] = s.charAt(j);
    		}
    	}
    	
    	ans = 0;
    	
    	findCombi(0, 0, 0);
    	
    	System.out.println(ans);

    }
}