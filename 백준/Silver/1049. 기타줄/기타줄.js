let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n")
const [n, m] = input.shift().split(" ").map(Number);
let minPackage = Math.min(...input.map((item) => item.split(" ")[0]));
let minOne = Math.min(...input.map((item) => item.split(" ")[1]));
let ans = Number.MAX_SAFE_INTEGER;

let temp1 = n * minOne;
ans = Math.min(ans, temp1);

let temp2 = Math.ceil(n / 6) * minPackage;
ans = Math.min(ans, temp2);

let temp3 = Math.floor(n / 6) * minPackage + ((n % 6) * minOne);
ans = Math.min(ans, temp3);

console.log(ans);