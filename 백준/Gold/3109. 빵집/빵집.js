let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n")
const [r, c] = input.shift().split(" ").map(Number);
const road = input.map(v => v.trim().split(""));
let visited = Array.from(Array(r), () => Array(c).fill(false));

const dy = [-1, 0, 1];
let cnt = 0;
let flag = false;

const dfs = (y, x) => {
    if (visited[y][x] || flag) return;
    visited[y][x] = true;

    if (x === c - 1) {
        cnt++;
        flag = true;
        return;
    }

    for (let i = 0; i < 3; i++) {
        let ny = y + dy[i];
        let nx = x + 1;
        if (ny < 0 || ny >= r) continue;
        if (!visited[ny][nx] && road[ny][nx] === '.' && !flag) {
            dfs(ny, nx);
        }
    }
}

for (let i = 0; i < r; i++) {
    let y = i;
    flag = false;
    dfs(y, 0);
}

console.log(cnt);
