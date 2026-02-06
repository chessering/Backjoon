import java.util.*;
import java.io.*;

public class Main {
	
	static int[] arr;
	static int n, s;
	static int cnt;
	
	static void solve(int idx, int sum) {
		
		if (idx == n) {
			if (sum == s) {
				cnt++;
			}
			return;
		}
		solve(idx + 1, sum + arr[idx]);
		
		solve(idx + 1, sum);
	}

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        s = sc.nextInt();
        cnt = 0;
        
        arr = new int[n];
        
        for (int i = 0; i < n; i++) {
        	arr[i] = sc.nextInt();
        }
        
        solve(0, 0);
        
        if (s == 0) cnt--;
        
        System.out.println(cnt);
        
    }

}