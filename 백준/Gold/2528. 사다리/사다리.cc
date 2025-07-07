#include <iostream>
#include <algorithm>

using namespace std;

int sec = 0;
int n, len;
int l[3001], dir[3001];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> len;
	for (int i = 1; i <= n; i++) {
		cin >> l[i] >> dir[i];
	}

	int s = 1;

	while (s < n) {

		if (l[s] == len || l[s + 1] == len) {
			s++;
			continue;
		}

		int curL, curR, curdir = dir[s], nxtL, nxtR, nxtdir = dir[s + 1];

		int cur_pos = sec % ((len - l[s]) * 2);
		if (cur_pos >= len - l[s]) cur_pos -= len - l[s], curdir ^= 1;
		if (curdir) curL = cur_pos, curR = cur_pos + l[s];
		else curL = len - cur_pos - l[s], curR = len - cur_pos;

		int nxt_pos = sec % ((len - l[s + 1]) * 2);
		if (nxt_pos >= len - l[s + 1]) nxt_pos -= len - l[s + 1], nxtdir ^= 1;
		if (nxtdir) nxtL = nxt_pos, nxtR = nxt_pos + l[s + 1];
		else nxtL = len - nxt_pos - l[s + 1], nxtR = len - nxt_pos;

		if (max(curL, nxtL) <= min(curR, nxtR)) s++;
		else sec++;
	}

	cout << sec << '\n';

}