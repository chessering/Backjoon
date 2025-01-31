const input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
const n = +input.shift();
let board = input.map(v => v.trim().split(''));
let ans = Infinity;

const changeRow = (row) => {
    for (let i = 0; i < n; i++) {
        if (board[row][i] == 'T') {
            board[row][i] = 'H';
        } else board[row][i] = 'T';
    }
}

const check = () => {
    let cnt = 0;
    for (let i = 0; i < n; i++) {
        let t = 0;
        for (let j = 0; j < n; j++) {
            if (board[j][i] == 'T') {
                t++;
            }
        }
        cnt += Math.min(t, n - t);
    }
    return cnt;
}

for (let i = 0; i < (1 << n); i++) {
    for (let j = 0; j < n; j++) {
        if (i & (1 << j)) changeRow(j);
    }

    let res = check();
    if (res < ans) ans = res;

    for (let j = 0; j < n; j++) {
        if (i & (1 << j)) changeRow(j);
    }
}

console.log(ans);
