const input = require('fs').readFileSync('/dev/stdin').toString().trim().split(' ');

let n = parseInt(input[0]);

const ans = [];

for (let i = 2; i <= n; i++) {
    while(n % i === 0) {
        ans.push(i);
        n /= i;
    }
    if (n === 1) break;
}
console.log(ans.join('\n'));
