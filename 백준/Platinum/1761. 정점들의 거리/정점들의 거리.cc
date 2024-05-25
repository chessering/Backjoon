#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int n, m;
vector<pair<int, int>> adj[40001];
int depth[40001];
int parent[40001][20];
int dist[40001][20];

void FindParent(int par, int cur, int dep, int cost) {
	depth[cur] = dep;
	parent[cur][0] = par;
	dist[cur][0] = cost;

	for (int i = 0; i < adj[cur].size(); i++) {
		if (adj[cur][i].first != par) {
			FindParent(cur, adj[cur][i].first, dep + 1, adj[cur][i].second);
		}
	}
	return;
}

int DistNode(int a, int b) {
	int sum = 0;
	if (depth[a] != depth[b]) {
		if (depth[a] < depth[b]) swap(a, b);

		int diff = depth[a] - depth[b];
		for (int i = 0; diff > 0; i++) {
			if (diff % 2 == 1) {
				sum += dist[a][i];
				a = parent[a][i];
			}
			diff = diff >> 1;
		}
	}
	
	if (a != b) {
		for (int k = 19; k >= 0; k--) {
			if (parent[a][k] != 0 && parent[a][k] != parent[b][k]) {
				sum += (dist[a][k] + dist[b][k]);
				a = parent[a][k];
				b = parent[b][k];
			}
		}
		sum += dist[a][0] + dist[b][0];
	}
	
	return sum;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n;

	for (int i = 0; i < n - 1; i++) {
		int u, v, cost;
		cin >> u >> v >> cost;
		adj[u].push_back({ v, cost });
		adj[v].push_back({ u, cost });
	}
	memset(parent, 0, sizeof(parent));
	memset(dist, 0, sizeof(dist));

	FindParent(0, 1, 0, 0);

	for (int k = 1; k < 20; k++) {
		for (int idx = 2; idx <= n; idx++) {
			if (parent[idx][k - 1] != 0) {
				parent[idx][k] = parent[parent[idx][k - 1]][k - 1];
				dist[idx][k] = dist[idx][k - 1] + dist[parent[idx][k - 1]][k - 1];
			}
		}
	}

	int pair_num;
	cin >> pair_num;

	while (pair_num--) {
		int a, b;
		cin >> a >> b;
		cout << DistNode(a, b) << '\n';
	}
}