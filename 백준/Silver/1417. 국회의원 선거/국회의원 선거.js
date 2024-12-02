const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n').map(Number);
const n = input[0];
let vote = input[1];
let arr = input.slice(2);

const sol = (n, vote, arr) =>{
    if (n === 1) return 0;

    let max = Math.max(...arr);
    let cnt = 0;

    while(vote <= max) {
        arr[arr.indexOf(max)] -= 1;
        vote += 1;
        cnt += 1;
        max = Math.max(...arr);
    }
    return cnt;
}

console.log(sol(n, vote, arr));
