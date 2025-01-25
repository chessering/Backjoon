let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
const n = input[0];
let arr = input[1].split(" ").map(Number);

arr.sort((a, b) => a - b);
let sum = 0;

for (i of arr) {
    if (sum + 1 >= i) sum += i;
    else break;
}
console.log(sum + 1);