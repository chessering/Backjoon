let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n")
const [m, n] = input.shift().trim().split(" ").map(Number);
const maze = input.map((row) => row.trim().split("").map(Number));

function bfs(sy, sx) {
    const deque = [];
    deque.push([sy, sx, 0]);
    const check = Array.from(Array(n), () => new Array(m).fill(0));
    check[sy][sx] = 1;
    const dy = [-1, 0, 1, 0];
    const dx = [0, 1, 0, -1];
    while (deque.length) {
      const [y, x, cnt] = deque.shift();
      if (y === n - 1 && x === m - 1) return cnt;

      for (let i = 0; i < 4; i++) {
        const [ny, nx] = [y + dy[i], x + dx[i]];
        if (nx < 0 || ny < 0 || ny >= n || nx >= m) continue;
        if (check[ny][nx]) continue;
        check[ny][nx] = 1;
        if (maze[ny][nx]) {
          maze[ny][nx] = 0;
          deque.push([ny, nx, cnt + 1]);
        } else {
          deque.unshift([ny, nx, cnt]);
        }
      }
    }
}

console.log(bfs(0, 0));
