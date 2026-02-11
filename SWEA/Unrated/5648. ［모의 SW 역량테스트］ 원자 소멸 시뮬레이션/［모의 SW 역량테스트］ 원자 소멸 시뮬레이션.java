import java.io.*;
import java.util.*;

class Solution {

    static int n;
    static Atom[] atom_list;
    static int sum;

    // dir: 상 하 좌 우

    static class Event {
        int time;
        int a, b;

        Event(int time, int a, int b) {
            this.time = time;
            this.a = a;
            this.b = b;
        }
    }

    //충돌 시간(거리) 반환
    static int collisionTime(int i, int j) {
        Atom a = atom_list[i];
        Atom b = atom_list[j];

        // 1. 같은 행 -> 좌 우인지 검사
        if (a.y == b.y) {
            if ((a.dir == 3 && b.dir == 2) && a.x < b.x)
                return (b.x - a.x) / 2;
            if ((a.dir == 2 && b.dir == 3) && b.x < a.x)
                return (a.x - b.x) / 2;
        }

        // 2. 같은 열 -> 상 하인지 검사
        if (a.x == b.x) {
            if ((a.dir == 0 && b.dir == 1) && a.y < b.y)
                return (b.y - a.y) / 2;
            if ((a.dir == 1 && b.dir == 0) && b.y < a.y)
                return (a.y - b.y) / 2;
        }

        // 3. 대각선이 같을 때
        if (Math.abs(a.x - b.x) == Math.abs(a.y - b.y)) {
            int dx = b.x - a.x;
            int dy = b.y - a.y;
            
            //a 방향이 위이고 b가 a보다 높이 있을 때
            if (a.dir == 0 && dy > 0) {
            	//방향이 오른쪽 -> b.x < a.x, 방향이 왼쪽 -> b.x > a.x
            	if ((b.dir == 3 && dx < 0) || (b.dir == 2 && dx > 0)) return dy;
            }
            
            //a 방향이 아래이고 b가 a보다 낮은 높이일 때
            if (a.dir == 1 && dy < 0) {
            	if ((b.dir == 3 && dx < 0) || (b.dir == 2 && dx > 0)) return -dy;
            }
            
            //a 방향이 왼쪽, b.x < a.x
            if (a.dir == 2 && dx < 0) {
            	if ((b.dir == 0 && dy < 0) || (b.dir == 1 && dy > 0)) return -dx;
            }
            //a 방향이 오른쪽, b.x > a.x
            if (a.dir == 3 && dx > 0) {
            	if ((b.dir == 0 && dy < 0) || (b.dir == 1 && dy > 0)) return dx;
            }

        }

        return -1;
    }

    static void solve() {
        List<Event> events = new ArrayList<>();
        //충돌하는 시간 다 세기
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = collisionTime(i, j); // 충돌 시간(거리)
                if (t > 0) {
                    events.add(new Event(t, i, j));
                }
            }
        }

        events.sort(Comparator.comparingInt(e -> e.time));

        int idx = 0;
        while (idx < events.size()) {
            int curTime = events.get(idx).time;
            //해당 시간에 충돌하는 요소 다 세기
            Set<Integer> boom = new HashSet<>();

            while (idx < events.size() && events.get(idx).time == curTime) {
                Event ev = events.get(idx);
                if (atom_list[ev.a].alive && atom_list[ev.b].alive) {
                    boom.add(ev.a);
                    boom.add(ev.b);
                }
                idx++;
            }

            for (int id : boom) {
                atom_list[id].alive = false;
                sum += atom_list[id].e;
            }
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            atom_list = new Atom[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                Atom atom = new Atom();
                atom.x = Integer.parseInt(st.nextToken()) * 2;
                atom.y = Integer.parseInt(st.nextToken()) * 2;
                atom.dir = Integer.parseInt(st.nextToken());
                atom.e = Integer.parseInt(st.nextToken());
                atom_list[i] = atom;
            }

            sum = 0;
            solve();

            System.out.println("#" + tc + " " + sum);
        }
    }
    
    
    static class Atom {
        int x, y;
        int dir;
        int e;
        boolean alive = true;
    }
    
}