let input = require("fs").readFileSync("/dev/stdin").toString().trim().split("\n").map((v) => v.split(" ").map(Number)).reverse();
const [G] = input.pop();
const [P] = input.pop();
const parent = Array.from({ length: G + 1 }, (_, i) => i);
let ans = 0;

for (let i = 0; i < P; i++) {
  const [gi] = input.pop();

  const dock = find(gi);

  if (dock !== 0) {
    merge(dock, dock - 1);
    ans++;
  } else break;
}

function find(u) {
  if (u == parent[u]) {
    return u;
  }
  return (parent[u] = find(parent[u]));
}

function merge(u, v) {
  const U = find(u);
  const V = find(v);
  if (U === V) return;

  parent[U] = V;
}

console.log(ans);