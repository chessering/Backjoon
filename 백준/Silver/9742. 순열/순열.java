import java.util.*;
import java.io.*;

public class Main {
	
	static char[] word;
	static char[] arr;
	static boolean[] visited;
	static int ans, flag, n;
	
	static void permutation_count(int cnt) {
		if (cnt == word.length) {
			ans++;
			if (ans == n) {
				flag = 1;
				System.out.print(word);
				System.out.print(" " + n + " = ");
				System.out.println(arr);
				return;
			}
		}
		
		for (int i = 0; i < word.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[cnt] = word[i];
				permutation_count(cnt + 1);
				visited[i] = false;
			}
		}
		
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String input = "";
        
        while((input = br.readLine()) != null) {
        	 st = new StringTokenizer(input);
        	 if (!st.hasMoreTokens()) break;
        	 
             word = st.nextToken().toCharArray();
             n = Integer.parseInt(st.nextToken());
             visited = new boolean[word.length];
             
             arr = new char[word.length];
             
             ans = 0;
             flag = 0;
             
             permutation_count(0);
             
             if (flag == 0) {
 				System.out.print(word);
 				System.out.print(" " + n + " = ");
 				System.out.println("No permutation");
             }
             
        }
        
    }

}