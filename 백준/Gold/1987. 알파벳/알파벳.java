import java.util.*;
import java.io.*;

public class Main {

	static int[][] arr;
    static int r, c, ans;
    static boolean alphabet[];
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    
    static int dfs(int y, int x) {
        int maxDist = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && nx >= 0 && ny < r && nx < c && !alphabet[arr[ny][nx]]) {
                alphabet[arr[ny][nx]] = true;
                maxDist = Math.max(maxDist, dfs(ny, nx));
                alphabet[arr[ny][nx]] = false;
            }
        }
        return maxDist + 1;
    }

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	
    	arr = new int[r][c];
    	alphabet = new boolean[26];
    	
    	for (int i = 0; i < r; i++) {
    		String s = br.readLine();
    		for (int j = 0; j < c; j++) {
    			arr[i][j] = s.charAt(j) - 'A';
    		}
    	}
    	alphabet[arr[0][0]] = true;
    	
    	ans = 1;
    	
	    System.out.println(dfs(0, 0));
    }
}