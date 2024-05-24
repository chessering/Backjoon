#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;

int main() {

	cin >> n;

	vector<int> v(n);

	int input;
	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}

	sort(v.begin(), v.end());

	for (int i = 0; i < n; i++) {
		if (v[i] + 1 == v[i + 1]) {
			int last = i + 2;
			if (last != n) {
				while (v[last] == v[i + 1]) last++;
			}
			if (last == n) {
				int start = i - 1;
				if (start >= 0) {
					while (v[start] == v[i]) {
						start--;
					}
				}
				v[start + 1]++;
				v[i + 1]--;
			}
			else {
				int tmp = v[last];
				v[last] = v[i + 1];
				v[i + 1] = tmp;
			}
		}
	}

	for (int i = 0; i < n; i++) {
		cout << v[i] << " ";
	}
}