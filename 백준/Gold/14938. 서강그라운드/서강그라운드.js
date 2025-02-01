const fs = require("fs");

class MinHeap {
    constructor() {
        this.heap = [];
    }

    push(node) {
        this.heap.push(node);
        this.bubbleUp();
    }

    pop() {
        if (this.heap.length === 1) return this.heap.pop();
        const min = this.heap[0];
        this.heap[0] = this.heap.pop();
        this.bubbleDown();
        return min;
    }

    bubbleUp() {
        let index = this.heap.length - 1;
        while (index > 0) {
            let parentIndex = Math.floor((index - 1) / 2);
            if (this.heap[parentIndex].distance <= this.heap[index].distance) break;
            [this.heap[parentIndex], this.heap[index]] = [this.heap[index], this.heap[parentIndex]];
            index = parentIndex;
        }
    }

    bubbleDown() {
        let index = 0;
        const length = this.heap.length;
        while (true) {
            let leftChild = 2 * index + 1;
            let rightChild = 2 * index + 2;
            let smallest = index;

            if (leftChild < length && this.heap[leftChild].distance < this.heap[smallest].distance) {
                smallest = leftChild;
            }
            if (rightChild < length && this.heap[rightChild].distance < this.heap[smallest].distance) {
                smallest = rightChild;
            }
            if (smallest === index) break;

            [this.heap[smallest], this.heap[index]] = [this.heap[index], this.heap[smallest]];
            index = smallest;
        }
    }

    isEmpty() {
        return this.heap.length === 0;
    }
}

const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [n, m, r] = input.shift().split(" ").map(Number);

const items = input.shift().split(" ").map(Number);
const graph = Array.from({ length: n + 1 }, () => []);

for (let i = 0; i < r; i++) {
    const [start, end, distance] = input[i].split(" ").map(Number);
    graph[start].push({ end, distance });
    graph[end].push({ end: start, distance });
}

let result = 0;

function solve(start) {
    let collectedItems = items[start - 1];
    let visited = Array(n + 1).fill(false);
    visited[start] = true;

    const queue = new MinHeap();
    for (const neighbor of graph[start]) {
        queue.push(neighbor);
    }

    while (!queue.isEmpty()) {
        const { end, distance } = queue.pop();

        if (!visited[end] && distance <= m) {
            collectedItems += items[end - 1];
            visited[end] = true;

            for (const next of graph[end]) {
                queue.push({ end: next.end, distance: distance + next.distance });
            }
        }
    }

    result = Math.max(result, collectedItems);
}

for (let i = 1; i <= n; i++) {
    solve(i);
}

console.log(result);
