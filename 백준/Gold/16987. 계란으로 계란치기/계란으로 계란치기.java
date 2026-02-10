import java.util.*;
import java.io.*;

public class Main {
	
	static int n, cnt;
	static int weight[];
	static int durability[];
	
	static void dfs(int idx) {
		
	    if (idx == n) {
	        int broken = 0;
	        for (int i = 0; i < n; i++) {
	            if (durability[i] <= 0) broken++;
	        }
	        cnt = Math.max(cnt, broken);
	        return;
	    }

	    // 현재 계란이 이미 깨진 경우
	    if (durability[idx] <= 0) {
	        dfs(idx + 1);
	        return;
	    }

	    boolean hit = false;

	    for (int i = 0; i < n; i++) {
	        if (i == idx || durability[i] <= 0) continue;

	        hit = true;

	        durability[idx] -= weight[i];
	        durability[i] -= weight[idx];

	        dfs(idx + 1);

	        durability[idx] += weight[i];
	        durability[i] += weight[idx];
	    }

	    // 칠 수 있는 계란이 하나도 없을 때
	    if (!hit) {
	        dfs(idx + 1);
	    }
	}


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        weight = new int[n];
        durability = new int[n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	durability[i] = Integer.parseInt(st.nextToken());
        	weight[i] = Integer.parseInt(st.nextToken());
        }
        
        cnt = 0;
        
        dfs(0);
        
        System.out.println(cnt);
        
    }

}