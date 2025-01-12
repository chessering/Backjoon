let input = require('fs').readFileSync("/dev/stdin").toString().trim().split('').map(Number);
let now_num = 0;
let now_idx = 0;

while (true) {
    now_num += 1
    nowArr = now_num.toString().split('').map(Number);

    for (let i = 0; i < nowArr.length; i++) {
        if (nowArr[i] === input[now_idx]) {
            now_idx += 1;
            if (now_idx >= input.length) {
                break;
            }
        }
    }

    if (now_idx >= input.length) {
        console.log(now_num);
        break;
    }
}

