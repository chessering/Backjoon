import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[] arr;

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	arr = new int[n];
    	
		st = new StringTokenizer(br.readLine());    	
    	for (int i = 0; i < n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(arr);

    	int ans[] = new int[3];
    	long op = Long.MAX_VALUE;
    	
    	for (int i = 0; i < n - 2; i++) {
    		int left = i + 1;
    		int right = n - 1;
    		
    		while(left < right) {
    			long sum = (long)arr[i] + (long)arr[left] + (long)arr[right];
    			
    			if (Math.abs(sum) < op) {
    				op = Math.abs(sum);
    				ans[0] = arr[i];
    				ans[1] = arr[left];
    				ans[2] = arr[right];
    			}
    			
    			if (sum < 0) left++;
    			else if (sum > 0) right--;
    			else {
    				System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    				return;
    			}
    			
    		}
    		
    	}
    	
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    	
    }
    
}