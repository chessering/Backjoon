import java.util.*;
import java.io.*;

public class Main {
	
	static int[] arr;
	
	static boolean prev_permutation() {
		int i = arr.length - 1;
		while(i > 0 && arr[i - 1] <= arr[i]) i--;
		
		if (i <= 0) return false; // 가장 앞에 오는 순열
		
		int j = arr.length - 1;
		
		//arr[i-1] 보다 작은 가장 큰 j 찾기
		while(arr[i - 1] <= arr[j]) j--;
		
		int temp = arr[j];
		arr[j] = arr[i - 1];
		arr[i - 1] = temp;
		
		j = arr.length - 1;
		
		while(i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
			
		}
		return true;
		
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        if (prev_permutation()) {
        	for (int i : arr) sb.append(i).append(" ");
        } else sb.append(-1);
        
        System.out.println(sb);
        
    }

}