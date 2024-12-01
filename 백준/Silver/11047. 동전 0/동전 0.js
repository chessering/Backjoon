const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
let [n, k] = input.shift().split(' ').map(Number);
const coins = input.map(Number).sort((a, b) => b - a);
let cnt = 0;

for (const coin of coins) {
    cnt += Math.floor(k / coin);
    k %= coin;
}

console.log(cnt);