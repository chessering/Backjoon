#include <iostream>
#include <string>
#include <algorithm>
typedef __int128 lll;

using namespace std;

long long a, b;
lll t_a, t_b;
string ans = "";
lll c = 1;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> a >> b;

	t_a = a, t_b = b;

	while (c <= 9223372036854775807LL && c % b) {
		c = c * 2 + 1;
	}

	if (c > 9223372036854775807LL) {
		cout << -1;
		return 0;
	}

	t_a *= c / t_b;

	while (c) {
		if (t_a & 1) ans += '*';
		else ans += '-';
		t_a >>= 1, c >>= 1;
	}

	if (ans.length() > 60) cout << -1;
	else {
		while (!ans.empty()) {
			cout << ans.back();
			ans.pop_back();
		}
	}

}