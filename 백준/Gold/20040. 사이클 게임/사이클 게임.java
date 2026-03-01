import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] parent;

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;
        if (x <= y) parent[y] = x;
        else parent[x] = y;
        return true;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        boolean found = false;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (found) continue;

            if (!union(a, b)) {
                found = true;
                System.out.println(i + 1);
            }
        }

        if (!found) System.out.println(0);
    }
}