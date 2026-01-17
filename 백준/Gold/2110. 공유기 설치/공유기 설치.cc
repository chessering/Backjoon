#include <iostream>
#include <algorithm>

using namespace std;

int n, c;
long long arr[200000];

int main() {

    cin >> n >> c;

    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    sort(arr, arr + n);

    int l = 0;
    int r = arr[n - 1];

    int result = 0;


    while (l <= r) {
        int install = 1;

        long long mid = (l + r) / 2;
        long long start = arr[0];

        for (int i = 1; i < n; i++) {
            long long end = arr[i];

            if (end - start >= mid) {
                install++;
                start = end;
            }
        }

        if (install >= c) {
            result = mid;
            l = mid + 1;
        }

        else {
            r = mid - 1;
        }

    }

    cout << result << '\n';
    
}