import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] arr;
	static int[][] result;
	
	static void rotate(int n) {
		int mid = n / 2;
		
		for (int i = 0; i < n; i++) {
			result[i][mid] = arr[i][i];
			result[i][n - 1 - i] = arr[i][mid];
			result[mid][n - 1 - i] = arr[i][n - 1 - i];
			result[i][i] = arr[mid][i];
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int dir = (d / 45 + 8) % 8;
            
            arr = new int[n][n];
            result = new int[n][n];
            
            for (int i = 0; i < n; i++) {
            	st = new StringTokenizer(br.readLine(), " ");
            	for (int j = 0; j < n; j++) {
            		arr[i][j] = Integer.parseInt(st.nextToken());
            		result[i][j] = arr[i][j];
            	}
            }
            
            for (int num = 0; num < dir; num++) {
                rotate(n);
                for (int i = 0; i < n; i++) arr[i] = result[i].clone();
            }
           
            for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) {
            		sb.append(result[i][j]).append(" ");
            	}
            	sb.append('\n');
            }
        }
        System.out.print(sb);
    }

}