import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[] arr;
	
	static int getccw(Point p1, Point p2, Point p3) {
		long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
		return Long.compare(0, result);
	}

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	Point a, b, c, d;
    	
    	a = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	b = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	st = new StringTokenizer(br.readLine());
    	c = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	d = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	
    	int ccw1 = getccw(a, b, c) * getccw(a, b, d);
    	int ccw2 = getccw(c, d, a) * getccw(c, d, b);
    	
    	
        // 둘 다 0인 경우
        if (ccw1 == 0 && ccw2 == 0) {
            if ((Math.max(a.x, b.x) >= Math.min(c.x, d.x) && Math.max(c.x, d.x) >= Math.min(a.x, b.x))
                    && (Math.max(a.y, b.y) >= Math.min(c.y, d.y) && Math.max(c.y, d.y) >= Math.min(a.y, b.y))) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else if (ccw1 <= 0 && ccw2 <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    	
    }
	
	static class Point {
		long y, x;
		Point(long y, long x) {
			this.y = y;
			this.x = x;
		}
	}
    
}