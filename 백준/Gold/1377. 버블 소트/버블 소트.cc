#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;

int main() {
	cin >> n;
	vector<pair<int, int>> v(n);

	for (int i = 0; i < n; i++) {
		cin >> v[i].first;
		v[i].second = i;
	}

	int ans = -1;
	
	sort(v.begin(), v.end());
	for (int i = 0; i < n; i++) {
		if (ans < v[i].second - i) {
			ans = v[i].second - i;
		}
	}
	cout << ans + 1;

}