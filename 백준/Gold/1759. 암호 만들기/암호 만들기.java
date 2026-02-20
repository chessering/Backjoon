import java.util.*;
import java.io.*;

public class Main {

	static int[] arr;
    static int l, c, ans;
    static boolean alphabet[];
    static int[] vowel = {0, 4, 8, 14, 20};
    static List<Integer> list;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    
    static void dfs(int idx, int cnt) {
        if (cnt == l) {
        	int vowel_cnt = 0;
        	for (int num : list) {
        		for (int v : vowel) {
        			if (num == v) vowel_cnt++;
        		}
        	}
        	if (vowel_cnt >= 1 && vowel_cnt <= l - 2) {
        		for (int num : list) System.out.print((char)(num + 'a'));
            	System.out.println();
        	}
        	return;
        }
        
        for (int i = idx; i < c; i++) {
        	if (alphabet[i]) continue;
        	alphabet[i] = true;
        	list.add(arr[i]);
        	dfs(i + 1, cnt + 1);
        	list.remove(list.size() - 1);
        	alphabet[i] = false;
        }
    	
    }

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	l = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	
    	arr = new int[c];
    	alphabet = new boolean[c];
    	list = new ArrayList<>();
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < c; i++) {
    		arr[i] = st.nextToken().charAt(0) - 'a';
    	}
    	
    	Arrays.sort(arr);
    	dfs(0, 0);

    }
}