#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int dx[4]={1,0,0,-1};
int dy[4]={0,-1,1,0};


string solution(int n, int m, int x, int y, int r, int c, int k) {
    if (((abs(x - r) + abs(y - c)) % 2 != k % 2)||(abs(x - r) + abs(y - c) > k)) {
        return "impossible";
    }
    else {
        string retA = "";
        string cc = "dlru";
        int ny, nx;
        while(k > 0) {
            k--;
            for (int i = 0; i < 4; i++) {
                ny = y + dy[i];
                nx = x + dx[i];
                if (abs(nx - r) + abs(ny - c) <= k && ny >= 1 && ny <= m && nx >=1 && nx <= n) {
                    retA += cc[i];
                    y = ny;
                    x = nx;
                    break;
                }
            }    
        }
        return retA;
    }    
}