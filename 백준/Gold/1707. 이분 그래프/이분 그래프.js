let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n");
let t = Number(input.shift());
let index = 0;

while(t--) {
    const [v, e] = input[index++].split(' ').map(Number);
    let graph = [...Array(v + 1)].map(() => []);
    let visited = [...Array(v + 1)].map(() => false);

    for (let i = 0; i < e; i++) {
        const [from, to] = input[index + i].split(' ').map(Number);
        graph[from].push(to);
        graph[to].push(from);
    }

    const bfs = (start) => {
        let queue = [start];
        let check = 1;

        visited[start] = check;

        while(queue.length) {
            let cur = queue.shift();

            if (visited[cur] === 1) check = 2;
            else if (visited[cur] === 2) check = 1;

            for (let i = 0; i < graph[cur].length; i++) {
                let next = graph[cur][i];
                if (!visited[next]) {
                    visited[next] = check;
                    queue.push(next);
                } else if (visited[next] === visited[cur]) {
                    return;
                }
            }
        }
    };

    for (let i = 1; i <= v; i++) {
        if (!visited[i]) {
            bfs(i);
        }
    }

    const Ans = () => {
        for (let i = 1; i <= v; i++) {
            for (let j = 0; j < graph[i].length; j++) {
                let next = graph[i][j];
                if (visited[i] === visited[next]) {
                    return console.log("NO");
                }
            }
        }
        return console.log("YES");
    }
    Ans();
    index += e;
}