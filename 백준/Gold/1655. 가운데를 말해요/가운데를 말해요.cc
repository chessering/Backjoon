#include <iostream>
#include <queue>

using namespace std;

int n;
priority_queue<int> max_pq;
priority_queue<int, vector<int>, greater<int>> min_pq;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n;

	vector<int> v(n);

	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}
	
	for (int i = 0; i < n; i++) {
		if (max_pq.size() > min_pq.size()) min_pq.push(v[i]);
		else max_pq.push(v[i]);

		if (!max_pq.empty() && !min_pq.empty()) {
			if (max_pq.top() > min_pq.top()) {
				int a = max_pq.top();
				max_pq.pop();
				int b = min_pq.top();
				min_pq.pop();
				max_pq.push(b);
				min_pq.push(a);
			}
		}
		cout << max_pq.top() << '\n';
	}
}
