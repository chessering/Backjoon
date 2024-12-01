const stdin = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
const input = stdin.map(v => v.split(' ').map(Number))
const [n, arr, op] = input;

//+-*/순서
let maxtot = Number.MIN_SAFE_INTEGER;
let mintot = Number.MAX_SAFE_INTEGER;

const calculate = [
    (a, b) => a + b,
    (a, b) => a - b,
    (a, b) => a * b,
    (a, b) => ~~(a / b),
];

function solve(tot, cnt) {
    if (cnt === n - 1) {
        maxtot = Math.max(maxtot, tot);
        mintot = Math.min(mintot, tot);
        return;
    }

    for (let i = 0; i < 4; i++) {
        if (op[i] > 0) {
            op[i]--;
            solve(calculate[i](tot, arr[cnt + 1]), cnt + 1);
            op[i]++;
        }
    }
}

solve(arr[0], 0);
console.log(maxtot);
console.log(mintot);
