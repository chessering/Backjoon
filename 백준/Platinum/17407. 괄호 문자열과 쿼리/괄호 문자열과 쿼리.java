import java.util.Scanner;

public class Main {

    static class Node {
        int sum;
        int minPrefix;
    }

    static Node tree[];
    static int n;
    static int treeSize;

    public static int getTreeSize(int arrSize) {
        int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
        return 1 << (h + 1);
    }

    static Node merge(Node left, Node right) {
        Node res = new Node();
        res.sum = left.sum + right.sum;
        res.minPrefix = Math.min(left.minPrefix, left.sum + right.minPrefix);
        return res;
    }

    //(이면 1, ) 이면 -1
    public static void init(char[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node();
            if (arr[start] == '(') {
                tree[node].sum = 1;
                tree[node].minPrefix = 1;
            } else {
                tree[node].sum = -1;
                tree[node].minPrefix = -1;
            }
            return;
        }

        int mid = (start + end) / 2;
        init(arr, node * 2, start, mid);
        init(arr, node * 2 + 1, mid + 1, end);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);

    }

    //값 업데이트
    static void update(int node, int start, int end, int idx) {
        //리프노드 도달
        if (start == end) {
            //sum, minPrefix 부호 체인지
            tree[node].sum *= -1;
            tree[node].minPrefix = tree[node].sum;
            return;
        }
        int mid = (start + end) / 2;
        //왼쪽으로 갈지, 오른쪽으로 갈지 결정
        if (idx <= mid) update(node * 2, start, mid, idx);
        else update(node * 2 + 1, mid + 1, end, idx);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        
    }
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.next().toCharArray();
        n = input.length;

        char[] strArr = new char[n + 1];
        for (int i = 1; i <= n; i++) {
            strArr[i] = input[i - 1];
        }
        
        treeSize = getTreeSize(strArr.length);
        tree = new Node[treeSize];

        init(strArr, 1, 1, strArr.length - 1);

        int m = sc.nextInt();
        int sum = 0;

        for (int i = 0; i < m; i++) {
            int query = sc.nextInt();
            update(1, 1, n, query);
            if (tree[1].sum == 0 && tree[1].minPrefix >= 0) sum++;
        }

        System.out.println(sum);

        sc.close();
    }

}