let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
let t = +input[0];
let idx = 1;

let visited;
let arr;
let done;
let cnt = 0;

const dfs = (node) => {
    visited[node] = true;
    const next = arr[node];
    if (!visited[next]) dfs(next);
    else if (!done[next]) {
        for (let i = next; i !== node; i = arr[i]) {
            cnt += 1;
        }
        cnt += 1;
    }
    done[node] = true;
}

while(t--) {
    const n = +input[idx];
    const temp = input[idx + 1].split(" ").map(Number);
    arr = [0, ...temp];
    visited = Array(n + 1).fill(false);
    done = Array(n + 1).fill(false);

    for (let i = 1; i <= n; i++) {
        if (!visited[i]) dfs(i);
    }

    console.log(n - cnt);

    idx += 2;
    cnt = 0;
}