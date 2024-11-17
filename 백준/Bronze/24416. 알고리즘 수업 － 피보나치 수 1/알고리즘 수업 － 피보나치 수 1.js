const input = require('fs').readFileSync('/dev/stdin').toString().trim();

let cnt = 0;

function recursion(n) {
    if (n == 1 || n == 2) {
        cnt++;
        return 1;
    } else {
        return recursion(n - 1) + recursion(n - 2);
    }
}

recursion(input);
console.log(cnt, input - 2);