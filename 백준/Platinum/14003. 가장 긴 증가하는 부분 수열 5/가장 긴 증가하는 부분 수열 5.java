import java.util.*;
import java.io.*;

public class Main {
	
	static int n, len;
	static int[] arr;
	static int[] dp;
	static int[] lis;
	
	static int binarySearch(int left, int right, int key) {
		int mid = 0;
		while(left < right) {
			mid = (left + right) / 2;
			
			if (lis[mid] < key) {
				left = mid + 1;
			} else right = mid;
		}
		return right;
	}


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dp = new int[n];
        lis = new int[n + 1]; //0~i의 arr 중 lis의 길이가 len인 부분수열들의 마지막 값 중 최솟값
        
        st = new StringTokenizer(br.readLine());
        
        int maxlen = 1;
        int lastIdx = 0;
        
        for (int i = 0; i < n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        lis[0] = -1_000_000_001;
        int len = 0;
        int idx = 0;
        
        for (int i = 0; i < n; i++) {
        	
        	if (arr[i] > lis[len]) {
        		dp[i] = ++len;
        		lis[len] = arr[i];
        	} else {
        		idx = binarySearch(0, len, arr[i]);
        		lis[idx] = arr[i];
        		dp[i] = idx;
        	}
        }
        
		sb.append(len+"\n");
		
		Stack<Integer> s = new Stack<>();
		for(int i=n-1; i>=0; i--) {
			if(dp[i] == len) {
				s.push(arr[i]);
				len--;
			}
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop()+" ");
		}
		System.out.println(sb.toString());
        
    }

}