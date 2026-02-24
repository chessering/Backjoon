import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int arr[][];
	static int presum[][];
	
	static int getpresum(int sy, int sx, int ey, int ex) {	
		return presum[ey][ex] - presum[ey][sx - 1] - presum[sy - 1][ex] + presum[sy - 1][sx - 1];
	}
	
	static int cal(int target_y, int target_x, int d1, int d2) {
		int[] sum_list = new int[5];
		//1구역 합
		int sum1 = 0;
		for (int i = target_y; i < target_y + d1; i++) {
			for (int j = target_x - d1; j < target_x; j++) {
				if (Math.abs(i - target_y) >= Math.abs(j - target_x)) continue;
				sum1 += arr[i][j];
			}
		}
		sum1 += (presum[target_y + d1 - 1][target_x] - 
				getpresum(target_y, target_x - d1, target_y + d1 - 1, target_x));
		//2구역 합
		int sum2 = 0;
		for (int i = target_y; i < target_y + d2; i++) {
			for (int j = target_x + 1; j <= target_x + d2; j++) {
				if (Math.abs(i - target_y) >= Math.abs(j - target_x)) continue;
				sum2 += arr[i][j];
			}
		}
		sum2 += (getpresum(1, target_x + 1, target_y + d2, n) 
				- getpresum(target_y, target_x + 1, target_y + d2, target_x + d2));
		//3구역 합
		int sum3 = 0;
		for (int i = target_y + d2 + 1; i <= target_y + d1 + d2; i++) {
			for (int j = target_x + d2 - d1 + 1; j <= target_x + d2; j++) {
				if (Math.abs(i - (target_y + d2)) <= Math.abs(j - (target_x + d2))) continue;
				sum3 += arr[i][j];
			}
		}
		sum3 += (getpresum(target_y + d2 + 1, target_x + d2 - d1, n, n) 
				- getpresum(target_y + d2 + 1, target_x + d2 - d1, target_y + d1 + d2, target_x + d2));
		//4구역 합
		int sum4 = 0;
		for (int i = target_y + d1 + 1; i <= target_y + d1 + d2; i++) {
			for (int j = target_x - d1; j < target_x + d2 - d1; j++) {
				if (Math.abs(i - (target_y + d1)) <= Math.abs(j - (target_x - d1))) continue;
				sum4 += arr[i][j];
			}
		}
		sum4 += (getpresum(target_y + d1, 1, n, target_x - d1 + d2 - 1) 
				- getpresum(target_y + d1, target_x - d1, target_y + d1 + d2, target_x - d1 + d2 - 1));
		
		sum_list[0] = sum1;
		sum_list[1] = sum2;
		sum_list[2] = sum3;
		sum_list[3] = sum4;
		sum_list[4] = presum[n][n] - sum1 - sum2 - sum3 - sum4;
	
		int max_p = -1;
		int min_p = 40001;
		
		for (int i = 0; i < 5; i++) {
			max_p = Math.max(max_p, sum_list[i]);
			min_p = Math.min(min_p, sum_list[i]);
		}
		return max_p - min_p;
		
	}
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	arr = new int[n + 1][n + 1];
    	presum = new int[n + 1][n + 1];
    	
    	for (int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 1; j <= n; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			presum[i][j] = arr[i][j] + presum[i][j - 1] + presum[i - 1][j] - presum[i - 1][j - 1];
    		}
    	}
    	
    	int ans = 40001;
    	
    	for (int i = 1; i <= n - 2; i++) {
    		for (int j = 2; j <= n - 1; j++) {
    			 for (int d1 = 1; d1 <= n; d1++) {
    				 for (int d2 = 1; d2 <= n; d2++) {
    					 if (i + d1 + d2 > n || j - d1 < 1 || j + d2 > n) continue;
    					 ans = Math.min(ans, cal(i, j, d1, d2));
    				 }
    			 }
    			
    		}
    	}
    	
    	System.out.println(ans);
    	
    }
    
}