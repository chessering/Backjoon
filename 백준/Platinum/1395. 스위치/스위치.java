import java.util.Scanner;

class Main {

    static int tree[];
    static int lazy[];
    static int light[];
    static int treeSize;

    static int getTreeSize(int arrSize) {
        int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
        return 1 << (1 + h);
    }

    static void init(int idx, int start, int end) {
        if (start == end) {
            tree[idx] = 0;
            return;
        }
        int mid = (start + end) / 2;
        init(idx * 2, start, mid);
        init(idx * 2 + 1, mid + 1, end);
        
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }

    static int sum(int idx, int start, int end, int left, int right) {
        update_lazy(idx, start, end);
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[idx];
        int mid = (start + end) / 2;

        return sum(idx * 2, start, mid, left, right) + sum(idx * 2 + 1, mid + 1, end, left, right);
    }

    static void update(int idx, int start, int end, int left, int right) {
        update_lazy(idx, start, end);
        if (right < start || end < left) return;
        if (left <= start && end <= right) {
            lazy[idx] ^= 1;
            update_lazy(idx, start, end);
            return;
        }
        int mid = (start + end) / 2;

        update(idx * 2, start, mid, left, right);
        update(idx * 2 + 1, mid + 1, end, left, right);
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }

    static void update_lazy(int idx, int start, int end) {
        if (lazy[idx] != 0) {
            tree[idx] = (end - start + 1) - tree[idx];
            if (start != end) {
                lazy[idx * 2] ^= 1;
                lazy[idx * 2 + 1] ^= 1;
            }
            lazy[idx] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        light = new int[n + 1];
        treeSize = getTreeSize(n);
        tree = new int[treeSize];
        lazy = new int[treeSize];
        init(1, 1, n);

        for (int i = 0; i < m; i++) {
            int query = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();

            //0이면 업데이트
            if (query == 0) {
                update(1, 1, n, left, right);

            } else { // 1이면 갯수반환
                System.out.println(sum(1, 1, n, left, right));
            }

        }

    }
}