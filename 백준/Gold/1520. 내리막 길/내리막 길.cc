#include <iostream>

using namespace std;

int m, n;
int dp[501][501];
int arr[501][501];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

int dfs(int y, int x) {
	if (y == m - 1 && x == n - 1) return 1;

	if (dp[y][x] != -1) return dp[y][x];

	dp[y][x] = 0;
	
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
		if (arr[ny][nx] < arr[y][x]) {
			dp[y][x] += dfs(ny, nx);
		}
	}
	return dp[y][x];
}

int main() {
	cin >> m >> n;

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
			dp[i][j] = -1;
		}
	}
	
	cout << dfs(0, 0) << '\n';

}