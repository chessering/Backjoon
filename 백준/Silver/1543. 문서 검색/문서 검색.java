import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();

        int cnt = 0;

        for (int i = 0; i + target.length() - 1 < str.length(); i++) {
            String sub = str.substring(i, i + target.length());
            boolean check = true;
            for (int j = 0; j < sub.length(); j++) {
                if (sub.charAt(j) != target.charAt(j)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                cnt++;
                i += (target.length() - 1);
            }
        }

        System.out.println(cnt);

    }
}
