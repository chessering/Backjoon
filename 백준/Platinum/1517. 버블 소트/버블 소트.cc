#include <iostream>
#include <vector>

using namespace std;

const int INF = 2e9;
const int inf = 1e9;

long long arr[500001];
vector<long long> v;
long long ans = 0;

void merge(int s, int mid, int e) {
	int i = s;
	int j = mid + 1;
	int k = s;
	while (i <= mid && j <= e) {
		if (v[i] <= v[j]) {
			arr[k] = v[i];
			i++;
		}
		else {
			arr[k] = v[j];
			ans += j - k;
			j++;
		}
		k++;
	}
	if (i > mid) {
		for (int x = j; x <= e; x++) {
			arr[k] = v[x];
			k++;
		}
	}
	else {
		for (int x = i; x <= mid; x++) {
			arr[k] = v[x];
			k++;
		}
	}
	for (int x = s; x <= e; x++) {
		v[x] = arr[x];
	}
}

void merge_sort(int s, int e) {
	if (s >= e) return;

	int mid = (s + e) / 2;
	merge_sort(s, mid);
	merge_sort(mid + 1, e);
	merge(s, mid, e);
}

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(NULL);

	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int k;
		cin >> k;
		v.push_back(k);
	}
	merge_sort(0, n - 1);
	cout << ans;
}