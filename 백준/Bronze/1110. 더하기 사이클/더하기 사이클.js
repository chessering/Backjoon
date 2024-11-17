const fs = require('fs');
const input = Number(fs.readFileSync('/dev/stdin').toString());

let ans = input;
let count = 0;

while(true) {
    
    let sum = Math.floor(ans / 10) + ans % 10;
    ans = (ans % 10) * 10 + sum % 10;
    count++;

    if (ans === input) break;

}
console.log(count);