#include <iostream>
#define MOD 1000000000
using namespace std;

void mult(long long a[], long long b[])
{
    long long r0 = (a[0] * b[0] + a[1] * b[2]) % MOD;
    long long r1 = (a[0] * b[1] + a[1] * b[3]) % MOD;
    long long r2 = (a[2] * b[0] + a[3] * b[2]) % MOD;
    long long r3 = (a[2] * b[1] + a[3] * b[3]) % MOD;
    a[0] = r0, a[1] = r1, a[2] = r2, a[3] = r3;
}
long long sum(long long n)
{
    long long p[4] = {1,1,1,0}, c[4] = {1,0,0,1};
    for(;n;n>>=1){
        if(n & 1) mult(c, p);
        mult(p, p);
    }
    return (c[2] * 2+ c[3] + MOD - 1) % MOD;
}
int main() {
    long long a, b;
    cin >> a >> b;
    cout << (sum(b) - sum(a - 1) + MOD) % MOD;
}