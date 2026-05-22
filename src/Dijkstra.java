import java.util.ArrayList;

public class Dijkstra {
    static class Edge {
        int destination;
        int weight;
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    static ArrayList<Edge>[] graph;
    static int numVertices;
    static void createGraph(int vertices) {
        numVertices = vertices;
        graph = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }
    }
    static void addEdge(int from, int to, int weight) {
        graph[from].add(new Edge(to, weight));
        graph[to].add(new Edge(from, weight));
    }
    static void dijkstra(int start) {
        int[] distance = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        for (int count = 0; count < numVertices; count++) {
            int current = -1;
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if (current == -1 || distance[i] < distance[current]) {
                        current = i;
                    }
                }
            }
            visited[current] = true;
            for (Edge edge : graph[current]) {
                int neighbor = edge.destination;
                int newDist = distance[current] + edge.weight;
                if (newDist < distance[neighbor]) {
                    distance[neighbor] = newDist;
                }
            }
        }
        System.out.println("shortest distance efrom vertex " + start + ":");
        for (int i = 0; i < numVertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("  Vertex " + i + " ==>> not reachable");
            } else {
                System.out.println("  Vertex " + i + " ==>> " + distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        createGraph(5);

        addEdge(0, 1, 4);
        addEdge(0, 2, 1);
        addEdge(2, 1, 2);
        addEdge(1, 3, 1);
        addEdge(2, 3, 5);
        addEdge(3, 4, 3);

        dijkstra(0);
    }
}