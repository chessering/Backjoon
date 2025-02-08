function isPrime(num) {
    if (num <= 1) return false;
    if (num <= 3) return true;
    if (num % 2 === 0 || num % 3 === 0) return false;
    for (let i = 5; i * i <= num; i += 6) {
        if (num % i === 0 || num % (i + 2) === 0) return false;
    }
    return true;
}

function generatePrimes(limit) {
    const primes = [];
    for (let i = 2; i < limit; i++) {
        if (isPrime(i)) primes.push(i);
    }
    return primes;
}

function dfs(x, Y, primes, matched, visited) {
    const index = Y.indexOf(x);
    if (visited[index]) return false;
    visited[index] = true;
    for (const y of Y) {
        if (primes.has(x + y)) {
            if (!matched.has(y) || dfs(matched.get(y), Y, primes, matched, visited)) {
                matched.set(y, x);
                return true;
            }
        }
    }
    return false;
}

function main() {
    const input = require("fs").readFileSync('/dev/stdin').toString().trim().split('\n');
    const N = parseInt(input[0]);
    const X = input[1].split(' ').map(Number);

    const primes = new Set(generatePrimes(2000));
    const answers = [];

    for (let i = 1; i < X.length; i++) {
        const matched = new Map();
        if (primes.has(X[0] + X[i])) {
            if (N === 2) {
                answers.push(X[i]);
                break;
            }

            const Y = X.filter((_, index) => index !== 0 && index !== i);
            for (const y of Y) {
                const visited = new Array(Y.length).fill(false);
                dfs(y, Y, primes, matched, visited);
            }
        }

        if (N !== 2 && matched.size === N - 2) {
            answers.push(X[i]);
        }
    }

    if (answers.length === 0) {
        console.log(-1);
    } else {
        answers.sort((a, b) => a - b);
        console.log(answers.join(' '));
    }
}

main();
