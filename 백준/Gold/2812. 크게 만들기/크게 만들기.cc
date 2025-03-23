#include <iostream>
#include <string>
#include <stack>

using namespace std;

int n, k;
int cnt = 0;
string s;
stack<char> st;
stack<char> st2;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> k;
	cin >> s;

	for (int i = 0; i < n; i++) {
		while (!st.empty() && cnt < k && s[i] > st.top()) {
			cnt++;
			st.pop();
		}
		st.push(s[i]);
	}
	while (cnt < k) {
		cnt++;
		st.pop();
	}

	while (!st.empty()) {
		st2.push(st.top());
		st.pop();
	}
	while (!st2.empty()) {
		cout << st2.top();
		st2.pop();
	}

}