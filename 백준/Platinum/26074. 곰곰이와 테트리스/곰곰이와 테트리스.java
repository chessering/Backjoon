import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        if (N * M == 2) {
            System.out.println("ChongChong");
        } else {
            System.out.println("GomGom");
        }
        
        sc.close();
    }
}
