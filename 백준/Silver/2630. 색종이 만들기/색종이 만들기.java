import java.util.*;
import java.io.*;

class Main {
	
	static int n;
	static int[][] map;
	static int b_cnt, w_cnt;
	//0 -> white, 1 -> blue
	
	static void solve(int sy, int sx, int size) {
		
		if (size == 1) {
			if (map[sy][sx] == 1) b_cnt++;
			else w_cnt++;
			return;
		}
		
		int w = 0, b = 0;
		
		for (int i = sy; i < sy + size; i++) {
			for (int j = sx; j < sx + size; j++) {
				if (map[i][j] == 1) b++;
				else w++;
			}
		}
		
		if (b == 0) w_cnt++; //하얀색으로만 칠해져 있음
		else if (w == 0) b_cnt++; // 파란색으로만 칠해져 있음
		//색깔이 2개면 재귀
		else {
			solve(sy, sx, size / 2);
			solve(sy, sx + size / 2, size / 2);
			solve(sy + size / 2, sx, size / 2);
			solve(sy + size / 2, sx + size / 2, size / 2);
		}
		
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	map = new int[n][n];
    	
    	for (int i = 0; i < n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < n; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	solve(0, 0, n);
    	
    	System.out.printf("%d%n%d", w_cnt, b_cnt);
    }
}
