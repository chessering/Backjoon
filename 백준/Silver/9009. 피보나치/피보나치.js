let input = require('fs').readFileSync("/dev/stdin").toString().trim().split('\n').map(Number);
let [n, ...arr] = input;

let fib = [0, 1];

while (true) {
    let num = fib[fib.length - 2] + fib[fib.length - 1]
    if (num >= 1000000000) break;
    fib.push(num);
}

for (let i = 0; i < n; i++) {
    let target = arr[i];
    let newArr = [];
    while(target > 0) {
        for (let j = fib.length; j >= 1; j--) {
            if (fib[j] <= target) {
                target -= fib[j];
                newArr.push(fib[j]);
                break;
            }
        }
    }
    newArr.sort((a, b) => a - b);
    console.log(newArr.join(" "));
}
