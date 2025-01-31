let [n, m, k] = require("fs").readFileSync("/dev/stdin").toString().trim().split(" ").map(Number);

if (m + k - 1 <= n && n <= m * k) {
    let arr = [];
    for (let i = k; i > 0; i--) {
        arr.push(i);
    }
    n -= k;
    m -= 1;
    while(m) {
        let temp = Math.floor(n / m);
        for (let j = temp + k; j > k; j--) {
            arr.push(j);
        }
        k += temp;
        n -= temp;
        m -= 1;
    }
    console.log(arr.join(" "));
} else console.log(-1);
