#include <iostream>
#include <map>

using namespace std;

int n, s;
int arr[41];
map<int, int> ssum;
long long cnt;

void rightSeq(int mid, int sum) {
	if (mid == n) {
		ssum[sum]++;
		return;
	}
	rightSeq(mid + 1, sum + arr[mid]);
	rightSeq(mid + 1, sum);
}

void leftSeq(int st, int sum) {
	if (st == n / 2) {
		cnt += ssum[s - sum];
		return;
	}
	leftSeq(st + 1, sum + arr[st]);
	leftSeq(st + 1, sum);
}

int main() {
	cin >> n >> s;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	rightSeq(n / 2, 0);
	leftSeq(0, 0);

	if (!s) cout << cnt - 1;
	else cout << cnt;
}