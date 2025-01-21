let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
const [m, n, k] = input.shift().split(" ").map(Number);
const graph = Array.from(Array(m), () => Array(n).fill(0));
const visited = Array.from(Array(m), () => Array(n).fill(false));
const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

for (let i = 0; i < k; i++) {
    const [x1, y1, x2, y2] = input[i].split(" ").map(Number);

    for (let y = m - y2; y < m - y1; y++) {
        for (let x = x1; x < x2; x++) {
            graph[y][x] = 1;
        }
    }
}

let count = 0;

const dfs = (y, x) => {
    count++;
    visited[y][x] = true;

    for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        if (ny >= 0 && ny < m && nx >= 0 && nx < n && !graph[ny][nx] && !visited[ny][nx]) {
            dfs(ny, nx);
        }
    }
};

let result = [];

for (let i = 0; i < m; i++) {
    for (let j = 0; j < n; j++) {
        if (!visited[i][j] && !graph[i][j]) {
            count = 0;
            dfs(i, j);
            result.push(count);
        }
    }
}

console.log(result.length);
console.log(result.sort((a, b) => a - b).join(' '));