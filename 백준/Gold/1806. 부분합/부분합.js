const input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
const [n, s] = input[0].split(" ").map(Number);
const arr = input[1].split(" ").map(Number);

let sum = 0;
let minlength = Infinity;
let start = 0;
let end = 0;

while(end < n) {
    sum += arr[end];

    while (sum >= s) {
        sum -= arr[start];
        minlength = Math.min(minlength, end - start + 1);
        start++;
    }
    end++;
}

console.log(minlength === Infinity ? 0 : minlength);