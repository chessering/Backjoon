#include <iostream>
#include <cmath>
using namespace std;

bool visited[27];
int alpha[25][25];
int r, c;
int dx[] = { 1, 0, -1, 0 };
int dy[] = { 0, 1, 0, -1 };
int max_len = 1;

void dfs(int y, int x, int cnt) {

    max_len = max(cnt, max_len);

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && nx < c && ny >= 0 && ny < r) {
            if (visited[alpha[ny][nx]] == false) {
                visited[alpha[ny][nx]] = true;
                dfs(ny, nx, cnt + 1);
                visited[alpha[ny][nx]] = false;
            }
        }
    }
}

int main() {

    cin >> r >> c;
    char alp;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            cin >> alp;
            alpha[i][j] = alp - 'A';
        }
    }

    visited[alpha[0][0]] = true;
    dfs(0, 0, 1);
    cout << max_len;
}
