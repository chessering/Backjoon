#include <iostream>
#include <queue>
#include <set>
#include <string>
#include <algorithm>

using namespace std;

queue<string> q;
int k;

void mswap(string &mystr, int left, int right) {
	char temp = mystr[left];
	mystr[left] = mystr[right];
	mystr[right] = temp;
	return;
}

int main() {
	string str;
	cin >> str >> k;
	q.push(str);

	for (int i = 0; i < k; i++) {
		set<string> s;
		int qsize = q.size();

		for (int j = 0; j < qsize; j++) {
			string item = q.front();
			q.pop();
			if (s.count(item) != 0) continue;
			s.insert(item);

			for (int l = 0; l < item.size() - 1; l++) {
				for (int r = l + 1; r < item.size(); r++) {
					if (!(l == 0 && item[r] == '0')) {
						mswap(item, l, r);
						q.push(item);
						mswap(item, l, r);
					}
				}
			}
		}
	}

	string ans = "0";
	while (!q.empty()) {
		ans = max(ans, q.front());
		q.pop();
	}
	if (ans[0] == '0') {
		cout << "-1";
	}
	else cout << ans;
	return 0;
}