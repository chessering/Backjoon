#include <iostream>
#include <vector>

using namespace std;

int n, k, m;

int main() {
	cin >> n >> k >> m;

	int cnt = 0;
	int pos = 0;
	if (k % n) pos = k % n;
	else pos = n;

	while (true) {
		cnt++;
		if (pos == m) break;

		if (m - pos > 0) {
			m -= pos;
			n--;
			if (k % n) pos = k % n;
			else pos = n;
		}
		else {
			m -= pos;
			m = n + m;
			n--;
			if (k % n) pos = k % n;
			else pos = n;
		}

	}

	cout << cnt << '\n';

}