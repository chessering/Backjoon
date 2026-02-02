#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

char board[11][11];
bool visit[11][11][11][11];
vector<int> ans;
int dy[4] = { 0, 1, 0, -1 };
int dx[4] = { -1, 0, 1, 0 };
int n, m;
int endx, endy, cnt = 0;

struct ss {
	int ry;
	int rx;
	int by;
	int bx;
	int cnt;
};

void move(int& ry, int& rx, int& dist, int& i) {

	while (board[ry + dy[i]][rx + dx[i]] != '#' && board[ry][rx] != 'O') {
		ry += dy[i];
		rx += dx[i];
		dist += 1;
	}

}

void bfs(int ry, int rx, int by, int bx) {

	queue<ss> q;
	q.push({ ry, rx, by, bx, 0 });
	visit[ry][rx][by][bx] = true;


	while (!q.empty()) {
		ss step = q.front();
		q.pop();
		int rx = step.rx;
		int ry = step.ry;
		int bx = step.bx;
		int by = step.by;
		int cnt = step.cnt;

		if (cnt >= 10) break;

		for (int i = 0; i < 4; i++) {
			int nrx = rx;
			int nry = ry;
			int nbx = bx;
			int nby = by;
			int rdist = 0, bdist = 0;


			move(nry, nrx, rdist, i);
			move(nby, nbx, bdist, i);

			if (board[nby][nbx] == 'O') continue;
			if (board[nry][nrx] == 'O') {
				ans.push_back(cnt + 1);
				return;
			}

			if (nry == nby && nrx == nbx) {
				if (rdist > bdist) {
					nrx -= dx[i];
					nry -= dy[i];
				}
				else {
					nbx -= dx[i];
					nby -= dy[i];
				}
			}

			if (visit[nry][nrx][nby][nbx]) continue;
			visit[nry][nrx][nby][nbx] = true;
			q.push({ nry, nrx, nby, nbx, cnt + 1 });

		}

	}

	cout << -1;
}

int main() {
	cin >> n >> m;

	int Rx, Ry, Bx, By;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
			if (board[i][j] == 'R') {
				Ry = i;
				Rx = j;
			}
			else if (board[i][j] == 'B') {
				By = i;
				Bx = j;
			}
		}
	}


	bfs(Ry, Rx, By, Bx);

	if (!ans.empty()) {
		sort(ans.begin(), ans.end());
		cout << ans[0];
	}


}