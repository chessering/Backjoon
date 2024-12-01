const input = require('fs').readFileSync('/dev/stdin').toString().trim().split(/\s+/);

let n = Number(input[0]);
let cnt = 0;

let three, five;
five = Math.floor(n / 5);

while(1) {
    if (five < 0) {
        console.log(-1);
        return 0;
    }
    if ((n - (5 * five)) % 3 === 0) {
        three = Math.floor((n - (5 * five)) / 3);
        break;
    }
    five--;
}

console.log(five + three);


