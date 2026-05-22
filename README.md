# Serikov Dias SE-2513

## Bonus Task: Dijkstra's Algorithm

This bonus task extends the base graph implementation to support **weighted edges** and uses **Dijkstra's Algorithm** to find the shortest path from a starting vertex to all other vertices in the graph.
```
static class Edge {
    int destination; // destination of edge
    int weight;      // weight of the edge
    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}
static ArrayList<Edge>[] graph; //array where every every index is vertex with list of its neighbors
```
----
```
int[] distance = new int[numVertices]; // array to store shortest path
boolean[] visited = new boolean[numVertices]; // array to visit vertices

for (int i = 0; i < numVertices; i++) {
    distance[i] = Integer.MAX_VALUE; // at the start every path are equals to infinty
}
distance[start] = 0; // path to starting vertex is 0
```
```
int current = -1;
for (int i = 0; i < numVertices; i++) {
    if (!visited[i]) {
        // if vertex is not yet visited and closer than current
        if (current == -1 || distance[i] < distance[current]) {
            current = i; // we find new candidate
        }
    }
}
visited[current] = true; // change current
```
----
```
for (Edge edge : graph[current]) {
    int neighbor = edge.destination;
    int newDist = distance[current] + edge.weight; // new path through current
    
    // if new disntance shorter than previous
    if (newDist < distance[neighbor]) {
        distance[neighbor] = newDist; // we change distance
    }
}
```
