import java.util.*;
import java.io.*;

public class Main {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        
        int[] arr = new int[k];
        
        int max_len = -1;
        
        for (int i = 0; i < k; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        	max_len = Math.max(max_len, arr[i]);
        }
        
        long ans = 0;
        //s와 e는 길이값
        long s = 1;
        long e = max_len;
        
        while(s <= e) {
        	long mid = (s + e) / 2; // 길이의 중간값
        	
        	long cnt = 0;
        	for (int i = 0; i < k; i++) cnt += (arr[i] / mid);
        	
        	if (cnt < n) e = mid - 1; //갯수가 최소 갯수보다 작음 -> 길이 줄이기
        	else if (cnt >= n) {
        		ans = mid;
        		s = mid + 1;
        	}
        	
        }
        
        System.out.println(ans);
        
    }

}