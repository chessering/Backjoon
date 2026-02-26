import java.util.*;
import java.io.*;

public class Main {

	static int n;


    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        n = Integer.parseInt(st.nextToken());
        
        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        long[] d = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st.nextToken());
            c[i] = Long.parseLong(st.nextToken());
            d[i] = Long.parseLong(st.nextToken());
        }

        long[] ab = new long[n * n];
        long[] cd = new long[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = a[i] + b[j];
                cd[idx] = c[i] + d[j];
                idx++;
            }
        }


        Arrays.sort(ab);
        Arrays.sort(cd);
        
        long ans = 0;
        int s = 0;
        int e = n * n - 1;
        
        while(s < n * n && e >= 0) {
        	long sum = ab[s] + cd[e];
        	
        	if (sum == 0) {
        		long curAB = ab[s];
        		long curCD = cd[e];
        		long cntAB = 0;
        		long cntCD = 0;
        		
        		while(s < n * n && ab[s] == curAB) {
        			s++;
        			cntAB++;
        		}
        		while(e >= 0 && cd[e] == curCD) {
        			e--;
        			cntCD++;
        		}
        		ans += cntAB * cntCD;
        		
        	} else if (sum > 0) e--;
        	else s++;
        	
        }
    	
        System.out.println(ans);
    }

}