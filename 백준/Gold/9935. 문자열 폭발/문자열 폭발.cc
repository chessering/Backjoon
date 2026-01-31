#include <iostream>
#include <stack>
#include <string>

using namespace std;

stack<char> st;
string s, bomb, ans = "";

int main() {
	cin >> s;
	cin >> bomb;
	for (int i = 0; i < s.size(); i++) {
		ans += s[i];
		if (ans.size() >= bomb.size()) {
			bool bombed = true;
			for (int j = 0; j < bomb.size(); j++) {
				if (ans[ans.length() - bomb.length() + j] != bomb[j]) {
					bombed = false;
					break;
				}
			}
			if (bombed) {
				ans.erase(ans.end() - bomb.size(), ans.end());
			}
		}
	}

	if (ans.empty()) cout << "FRULA" << '\n';
	else cout << ans << '\n';

}