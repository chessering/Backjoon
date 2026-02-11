import java.util.*;
import java.io.*;

public class Main {
	
	static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        
        Meeting[] arr = new Meeting[n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	arr[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        Arrays.sort(arr);
        
        int cur_t = arr[0].end;
        int cnt = 1;
        
        for (int i = 1; i < n; i++) {
        	if (cur_t <= arr[i].start) {
        		cnt++;
        		cur_t = arr[i].end;
        	}
        }
        
        System.out.println(cnt);

    }
    
    static class Meeting implements Comparable<Meeting>{
    	int start;
    	int end;
    	Meeting(int start, int end) {
    		this.start = start;
    		this.end = end;
    	}
    	
    	@Override
    	public int compareTo(Meeting m) {
    		if (this.end == m.end) return Integer.compare(this.start, m.start);
    		return Integer.compare(this.end, m.end);
    	}
    	
    }

}