let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
let [n, m] = input.shift().split(' ').map(Number);
let grim = Array.from(Array(n), () => Array(m).fill(0));
let visited = Array.from(Array(n), () => Array(m).fill(false));
const dx = [-1, 0, 1, 0];
const dy = [0, 1, 0, -1];


for (let i = 0; i < n ; i++) {
    grim[i] = input[i].split(" ").map(Number);
}

let count = 0, area = 0, ans = 0;

const dfs = (y, x) => {
    for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        if (0 <= nx && nx < m && 0 <= ny && ny < n && !visited[ny][nx] && grim[ny][nx] === 1) {
            visited[ny][nx] = true;
            area++;
            dfs(ny, nx);
        }
    }
}

for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
        if (!visited[i][j] && grim[i][j] === 1) {
            visited[i][j] = true;
            count++;
            area = 1;
            dfs(i, j);
            ans = Math.max(ans, area);
        }
    }
}

console.log(count);
console.log(ans);