let input = require('fs').readFileSync("/dev/stdin").toString().trim().split('\n');
const n = Number(input[0]);
const arr = input.slice(1).map(str => str.trim());

function sol(n, arr) {
    let hashMap = new Map();
    let cnt = 0;
    let ans = 0;
    let cur = 9;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < arr[i].length; j++) {
            cnt = Math.pow(10, arr[i].length - (j + 1));
            hashMap.set(arr[i][j], (hashMap.get(arr[i][j]) || 0) + cnt);
        }
    }
    const sortMap = new Map([...hashMap.entries()].sort((a, b) => b[1] - a[1]));

    for (let [_, alphabetValue] of sortMap) {
        ans += cur * alphabetValue;
        cur -= 1;
    }
    return ans;
}

console.log(sol(n, arr));
