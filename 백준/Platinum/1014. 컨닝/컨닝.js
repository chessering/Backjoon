let [[C], ...I] = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n').map(e => e.split(' '));
let [i, O] = [0, []]
C = +C;
const check = (a, b) => ((a << 1) & b) == 0 && (a & (b << 1)) == 0;

while (i < I.length) {
  const [N, M] = I[i].map(Number);
  let [x, A] = [i++ + N, []];
    
  for (; i <= x; i++) A.push(parseInt(I[i][0].split('').map(e => (e == '.' ? 1 : 0)).join(''), 2))
  let dp = [...Array(N)].map(_ => Array(2 ** M).fill(0));
  
  for (let j = A[0]; ; j = (j - 1) & A[0]) {
    if (check(j, j)) dp[0][j] = j.toString(2).split('1').length - 1;
    if (!j) break;
  }
    
  for (let j = 1; j < N; j++)
    for (let k = A[j - 1]; ; k = (k - 1) & A[j - 1]) {
      for (let l = A[j]; ; l = (l - 1) & A[j]) {
        if (check(l, l) && check(k, l))
          dp[j][l] = Math.max(dp[j][l], dp[j - 1][k] + l.toString(2).split('1').length - 1);
        if (!l) break;
      }
      if (!k) break;
    }
  O.push(Math.max(...dp[N - 1]));
}
console.log(O.join('\n'));