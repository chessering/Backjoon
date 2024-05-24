#include <iostream>
#include <queue>

using namespace std;

int w, h, k;
int map[201][201];
bool visited[205][205][32];

int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, 1, 0, -1 };
int hdx[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
int hdy[] = { -2, -1, 1, 2, 2, 1, -1, -2 };


void bfs() {
	queue<pair<pair<int, int>, pair<int, int>>> q;
	q.push({ {0, 0}, {0, 0} });
	visited[0][0][0] = true;
	while (!q.empty()) {
		int y = q.front().first.first;
		int x = q.front().first.second;
		int cnt = q.front().second.first;
		int ab = q.front().second.second;
		q.pop();

		if (x == w - 1 && y == h - 1) {
			cout << cnt << '\n';
			return;
		}

		if (ab < k) {
			for (int i = 0; i < 8; i++) {
				int nx = x + hdx[i];
				int ny = y + hdy[i];
				if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
				if (map[ny][nx] == 0 && !visited[ny][nx][ab + 1]) {
					visited[ny][nx][ab + 1] = true;
					q.push({ {ny, nx}, {cnt + 1, ab + 1} });
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
			if (map[ny][nx] == 0 && !visited[ny][nx][ab]) {
				visited[ny][nx][ab] = true;
				q.push({ {ny, nx}, {cnt + 1, ab} });
			}
		}
	}
	cout << -1 << '\n';
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> k;
	cin >> w >> h;

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			cin >> map[i][j];
		}
	}

	bfs();
}