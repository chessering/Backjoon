const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

const score = input[1].split(' ').map(Number);
const max = Math.max(...score);
const arr = score.map(x => x / max * 100);

let num = 0;
for (let i = 0; i < arr.length; i++) {
    num += arr[i]
}
const avg = num / arr.length;

console.log(avg);