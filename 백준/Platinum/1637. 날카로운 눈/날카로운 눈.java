import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;
    static int n;
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[n][3];

        int min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        //입력값 저장
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            min = Math.min(min, arr[i][0]);
            max = Math.max(max, arr[i][2]);
        }
        max++; //left == right == max -> fx(max)만 홀수일 때 방지

        long left = min;
        long right = max;
        StringBuilder sb = new StringBuilder();
        //이분 탐색 진행
        while(left < right){
            long mid = (left + right) / 2;
            long temp = fx(mid);
            //fx(n)이 홀수일 때
            if(temp % 2 == 1){
                right = mid;
            }else{	//fx(n)이 짝수일 때
                left = mid + 1;
            }
        }
        //홀수인 정수가 존재하지 않을 때
        if(left == max){
            sb.append("NOTHING");
        }else{	//홀수인 정수가 존재할 때
            //홀수인 정수의 개수 구하기 fx(n) - fx(n-1)
            long result = fx(left) - fx(left - 1);
            sb.append(left).append(" ").append(result);
        }

        System.out.println(sb.toString());
        br.close();
    }
    //fx(val) : val이하의 정수 개수 구하는 함수
    static long fx(long val){
        long cnt = 0;
        for(int i = 0; i < n; i++){
            //시작점이 val보다 크면 넘기기
            if(arr[i][0] > val){
                continue;
            }
            //점화식을 통한 개수 구하기
            cnt += (Math.min(arr[i][2], val) - arr[i][0])/ arr[i][1] + 1;
        }
        return cnt;
    }
}