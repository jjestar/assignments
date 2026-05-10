import java.util.*;

public class Experiment {
    private List<String> results = new ArrayList<>();

    public void runTraversals(Graph g, int startVertex, String label, boolean printTraversal) {
        if (printTraversal) {
            System.out.println("\n--- " + label + " ---");
            System.out.print("BFS: ");
        }

        long bfsStart = System.nanoTime();
        if (printTraversal) {
            g.bfs(startVertex);
        } else {
            captureTraversal(g, startVertex, true);
        }
        long bfsEnd = System.nanoTime();

        if (printTraversal) {
            System.out.print("DFS: ");
        }

        long dfsStart = System.nanoTime();
        if (printTraversal) {
            g.dfs(startVertex);
        } else {
            captureTraversal(g, startVertex, false);
        }
        long dfsEnd = System.nanoTime();

        long bfsTime = bfsEnd - bfsStart;
        long dfsTime = dfsEnd - dfsStart;

        results.add(String.format("%-20s | BFS: %8d ns | DFS: %8d ns", label, bfsTime, dfsTime));
    }

    private void captureTraversal(Graph g, int start, boolean isBfs) {
        if (isBfs) {
            g.bfs(start);
        } else {
            g.dfs(start);
        }
    }

    public void runMultipleTests() {
        System.out.println("\n========== Running Performance Tests ==========\n");

        Graph small = buildGraph(10, 15);
        System.out.println("Small Graph (10 vertices) structure:");
        small.printGraph();
        runTraversals(small, 0, "Small (10 vertices)", true);

        Graph medium = buildGraph(30, 60);
        runTraversals(medium, 0, "Medium (30 vertices)", false);

        Graph large = buildGraph(100, 300);
        runTraversals(large, 0, "Large (100 vertices)", false);
    }

    public void printResults() {
        System.out.println("\n========== Performance Results ==========\n");
        System.out.printf("%-20s | %-20s | %-20s%n", "Graph Size", "BFS Time", "DFS Time");
        System.out.println("-".repeat(66));
        for (String result : results) {
            System.out.println(result);
        }
    }

    private Graph buildGraph(int numVertices, int numEdges) {
        Graph g = new Graph();
        for (int i = 0; i < numVertices; i++) {
            g.addVertex(new Vertex(i));
        }

        Random rand = new Random(42);
        Set<String> added = new HashSet<>();
        int count = 0;
        while (count < numEdges) {
            int from = rand.nextInt(numVertices);
            int to = rand.nextInt(numVertices);
            String key = from + "-" + to;
            if (from != to && !added.contains(key)) {
                g.addEdge(from, to);
                added.add(key);
                count++;
            }
        }
        return g;
    }
}
