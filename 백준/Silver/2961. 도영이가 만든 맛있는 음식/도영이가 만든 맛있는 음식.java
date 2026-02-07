import java.util.*;

class Main {

    static int bit[];
    static int sour[];
    static long sum;
    static int n;

    static void solve(int idx, int sourSum, int bitSum) {

        if (idx == n) {
            if (sourSum != 1) {
                sum = Math.min(sum, Math.abs(sourSum - bitSum));
            }
            return;
        }
        //선택함
        solve(idx + 1, sourSum * sour[idx], bitSum + bit[idx]);
        //선택 안함
        solve(idx + 1, sourSum, bitSum);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        bit = new int[n];
        sour = new int[n];

        for (int i = 0; i < n; i++) {
            sour[i] = sc.nextInt();
            bit[i] = sc.nextInt();
        }

        sum = Long.MAX_VALUE;

        solve(0, 1, 0);

        System.out.println(sum);
        
    }
}
