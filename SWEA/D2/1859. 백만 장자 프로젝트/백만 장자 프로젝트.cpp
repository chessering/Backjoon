#include <iostream>
#include <vector>

using namespace std;

int main() {
	int test_case;
	int T;

	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		vector<int> arr;
		int num;
		long long total = 0;
		cin >> num;

		for (int i = 0; i < num; i++)
		{
			int price;
			cin >> price;
			arr.push_back(price);
		}

		int max = arr[arr.size() - 1];

		while (true)
		{
			arr.pop_back();
			if (arr.empty() == true)
			{
				break;
			}

			if (max > arr[arr.size() - 1])
			{
				total += max - arr[arr.size() - 1];
			}
			else
			{
				max = arr[arr.size() - 1];
			}
		}

		cout << "#" << test_case << " " << total << endl;

	}


	return 0;
}