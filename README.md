# Assignment 4 Graph Traversal and Representation System

## A. Project Overview
This project implements a directed graph system in Java, supporting graph construction, traversal, and performance analysis. A **graph** is a data structure composed of **vertices** (nodes) and **edges** (connections between nodes). In this implementation, edges are directed, meaning they go from a source vertex to a destination vertex.
**BFS (Breadth-First Search)** explores the graph level by level, visiting all neighbors of a vertex before moving deeper. It uses a queue to track vertices to visit.
**DFS (Depth-First Search)** explores as far as possible along each branch before backtracking. It uses recursion (implicit call stack) to traverse the graph depth-first.
---

## B. Class Descriptions

### `Vertex`
Represents a single node in the graph. Each vertex has a unique integer `id`. Provides a constructor, a getter for `id`, and a `toString()` method.

### `Edge`
Represents a directed connection between two vertices. Stores a `source` and a `destination` vertex. Provides a constructor, getters for both fields, and a `toString()` method.

### `Graph`
Represents the graph using an **adjacency list** — a `HashMap` where each key is a vertex ID and the value is a list of `Edge` objects departing from that vertex. Supports adding vertices and edges, printing the graph structure, and running BFS/DFS traversals.

**Why adjacency list?** It is memory-efficient for sparse graphs and allows O(V + E) traversal time, unlike adjacency matrices which use O(V²) space.

### `Experiment`
Handles graph construction, traversal execution, time measurement, and result reporting. Builds graphs of different sizes and runs both traversal algorithms while recording nanosecond-level execution times.

---

## C. Algorithm Descriptions

### BFS (Breadth-First Search)

**Step-by-step:**
1. Add the start vertex to a queue and mark it as visited.
2. While the queue is not empty, dequeue the front vertex.
3. For each unvisited neighbor, mark it visited and enqueue it.
4. Repeat until the queue is empty.

**Use cases:** Shortest path in unweighted graphs, level-order traversal, finding all nodes at a given distance.

**Time complexity:** O(V + E) — each vertex and edge is processed once.

---

### DFS (Depth-First Search)

**Step-by-step:**
1. Mark the current vertex as visited.
2. Recursively visit each unvisited neighbor.
3. Backtrack when no unvisited neighbors remain.

**Use cases:** Cycle detection, topological sorting, solving mazes, checking connectivity.

**Time complexity:** O(V + E) — each vertex and edge is visited once.

---

## D. Experimental Results

Graphs were built using a fixed random seed (42) to ensure reproducibility. Edges were generated as directed, with no self-loops and no duplicate edges.

| Graph Size        | BFS Time (ns) | DFS Time (ns) |
|-------------------|---------------|---------------|
| Small (10 vertices, 15 edges)  | 11,797,178    | 322,866       |
| Medium (30 vertices, 60 edges) | 950,754       | 399,765       |
| Large (100 vertices, 300 edges)| 2,072,338     | 452,994       |

**Observations:**
- BFS time for the small graph is high due to JVM warm-up overhead on the first run.
- After warm-up, BFS and DFS are both O(V + E) and perform similarly.
- DFS is consistently fast due to recursive traversal and low overhead per call.
- As graph size increases, execution time grows proportionally with V + E, matching theoretical expectations.

---

## E. Screenshots

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/31ca3afc-d4f5-4d3f-a31a-4a74b1cd5295" />

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/113e8993-7b74-4c19-84b3-f866b7f79628" />

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/499df146-ac88-42cb-80e5-d707f7a70d4a" />

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/bfeef586-d69b-4fa0-9f3a-c2bcd507deaf" />

---

## F. Reflection

Working on this assignment gave me a clear understanding of how graph traversal algorithms behave in practice versus in theory. Both BFS and DFS have O(V + E) time complexity, but their traversal orders are very different — BFS explores neighbors level by level while DFS dives deep into a single path before backtracking. This difference becomes very visible when comparing the output sequences: BFS from vertex 0 visits vertex 3 and immediately explores all neighbors of 3, while DFS immediately follows the deepest path before returning.
The main challenge was correctly handling the visited set to avoid infinite loops in graphs with cycles, and ensuring the adjacency list was built consistently before traversal. I also learned that raw nanosecond benchmarks in Java can be misleading for small graphs due to JVM JIT compilation warm-up — the first call to any method is often much slower than subsequent ones, which explains the anomalous BFS time for the small graph. A more accurate benchmark would use repeated runs and average the results. Overall, this project made the relationship between data structure choice (adjacency list vs matrix) and algorithm performance very concrete.

---
## Repository Structure
```
assignments/
├── src/
│   ├── Vertex.java
│   ├── Edge.java
│   ├── Graph.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```
