const input = require('fs').readFileSync('/dev/stdin').toString().trim().split(' ');

const a = Number(input[0]);
const b = Number(input[1]);

const gcd = (a, b) => a % b === 0 ? b : gcd(b, a % b);
const lcm = (a, b) => a * b / gcd(a, b);

console.log(gcd(a, b));
console.log(lcm(a, b));