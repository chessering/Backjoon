let input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
const [n, m] = input.shift().split(' ').map(Number);
const graph = input.map(line => line.split(''));
const visited = Array.from({ length: n }, () => new Array(m).fill(0));

let safeZone = 0;

for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
        if (visited[i][j] === 0) {
            dfs(i, j);
        }
    }
}

function dfs(x, y) {
    const cycle = [];
    
    while (true) {
        visited[x][y] = 1;
        cycle.push([x, y]);

        const [dx, dy] = dir(graph[x][y]);  
        x += dx;
        y += dy;

        if (x < 0 || y < 0 || x >= n || y >= m) return;

        if (visited[x][y] === 1) {
            for (let [r,c] of cycle) {
                if (x === r && y === c) {
                    safeZone += 1; 
                    break; 
                }
            }
            return; 
        } else if(visited[x][y] === 2){
            return; 
        }
    }

    for (let [r, c] of cycle) {
        visited[r][c] = 2;
    }
}

function dir(direction) {
    switch (direction) {
        case 'U': return [-1, 0];
        case 'D': return [1, 0];
        case 'L': return [0, -1];
        case 'R': return [0, 1];
    }
}

console.log(safeZone);