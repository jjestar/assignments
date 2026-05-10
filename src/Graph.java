import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Edge>> adjacencyList;
    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }
    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }
    public void addEdge(int from, int to) {
        Vertex src = vertices.get(from);
        Vertex dst = vertices.get(to);
        if (src == null || dst == null) return;
        adjacencyList.get(from).add(new Edge(src, dst));
    }
    public void printGraph() {
        List<Integer> sortedKeys = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(sortedKeys);
        for (int id : sortedKeys) {
            List<Edge> edges = adjacencyList.get(id);
            StringBuilder sb = new StringBuilder();
            sb.append("Vertex ").append(id).append(" -- [");
            for (int i = 0; i < edges.size(); i++) {
                sb.append(edges.get(i).getDestination().getId());
                if (i < edges.size() - 1) sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Edge> neighbors = adjacencyList.getOrDefault(current, new ArrayList<>());
            for (Edge edge : neighbors) {
                int neighborId = edge.getDestination().getId();
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.add(neighborId);
                }
            }
        }

        System.out.println("BFS from " + start + ": " + visited);
    }

    public void dfs(int start) {
        Set<Integer> visited = new LinkedHashSet<>();
        dfsHelper(start, visited);
        System.out.println("DFS from " + start + ": " + visited);
    }

    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        List<Edge> neighbors = adjacencyList.getOrDefault(current, new ArrayList<>());
        for (Edge edge : neighbors) {
            int neighborId = edge.getDestination().getId();
            if (!visited.contains(neighborId)) {
                dfsHelper(neighborId, visited);
            }
        }
    }

    public Map<Integer, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }
}
