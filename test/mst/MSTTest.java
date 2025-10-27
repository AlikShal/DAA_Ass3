package mst;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

//JUnit tests for MST algoritms. Check correctness and basic perfomance needs

public class MSTTest {
    
    private Graph smallGraph;
    private Graph mediumGraph;
    private Graph disconnectedGraph;
    
    @BeforeEach
    void setUp() {
        // Small test graph (5 vertices, 7 edges)
        Set<String> vertices1 = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));
        List<Edge> edges1 = Arrays.asList(
            new Edge("A", "B", 4),
            new Edge("A", "C", 3),
            new Edge("B", "C", 2),
            new Edge("B", "D", 5),
            new Edge("C", "D", 7),
            new Edge("C", "E", 8),
            new Edge("D", "E", 6)
        );
        smallGraph = new Graph(1, vertices1, edges1);
        
        // Medium test graph (10 vertices)
        Set<String> vertices2 = new HashSet<>(
            Arrays.asList("A","B","C","D","E","F","G","H","I","J")
        );
        List<Edge> edges2 = Arrays.asList(
            new Edge("A", "B", 1), new Edge("A", "C", 4),
            new Edge("B", "C", 2), new Edge("B", "D", 6),
            new Edge("C", "D", 3), new Edge("C", "E", 5),
            new Edge("D", "E", 7), new Edge("E", "F", 2),
            new Edge("F", "G", 4), new Edge("G", "H", 3),
            new Edge("H", "I", 5), new Edge("I", "J", 1),
            new Edge("G", "J", 6), new Edge("F", "J", 8)
        );
        mediumGraph = new Graph(2, vertices2, edges2);
        
        // Disconnected graph for testing
        Set<String> vertices3 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        List<Edge> edges3 = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("C", "D", 2)
        );
        disconnectedGraph = new Graph(3, vertices3, edges3);
    }
    
    // CORRECTNESS TESTS
    
    @Test
    @DisplayName("Test: Both algorithms find same total cost")
    void testSameTotalCost() {
        MSTResult primResult = PrimMST.findMST(smallGraph);
        MSTResult kruskalResult = KruskalMST.findMST(smallGraph);
        
        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost(),
            "Prim and Kruskal should find MST with same total cost");
    }
    
    @Test
    @DisplayName("Test: MST has V-1 edges")
    void testEdgeCount() {
        MSTResult primResult = PrimMST.findMST(smallGraph);
        MSTResult kruskalResult = KruskalMST.findMST(smallGraph);
        
        int expectedEdges = smallGraph.getVertexCount() - 1;
        
        assertEquals(expectedEdges, primResult.getMstEdges().size(),
            "Prim MST should have V-1 edges");
        assertEquals(expectedEdges, kruskalResult.getMstEdges().size(),
            "Kruskal MST should have V-1 edges");
    }
    
    @Test
    @DisplayName("Test: MST contains no cycles")
    void testNoCycles() {
        MSTResult primResult = PrimMST.findMST(smallGraph);
        MSTResult kruskalResult = KruskalMST.findMST(smallGraph);
        
        assertFalse(KruskalMST.hasCycle(primResult.getMstEdges(), smallGraph.getVertices()),
            "Prim MST should not contain cycles");
        assertFalse(KruskalMST.hasCycle(kruskalResult.getMstEdges(), smallGraph.getVertices()),
            "Kruskal MST should not contain cycles");
    }
    
    @Test
    @DisplayName("Test: MST connects all vertices")
    void testConnectivity() {
        MSTResult primResult = PrimMST.findMST(smallGraph);
        MSTResult kruskalResult = KruskalMST.findMST(smallGraph);
        
        assertTrue(isFullyConnected(primResult.getMstEdges(), smallGraph.getVertices()),
            "Prim MST should connect all vertices");
        assertTrue(isFullyConnected(kruskalResult.getMstEdges(), smallGraph.getVertices()),
            "Kruskal MST should connect all vertices");
    }
    
    @Test
    @DisplayName("Test: Handle disconnected graph")
    void testDisconnectedGraph() {
        MSTResult primResult = PrimMST.findMST(disconnectedGraph);
        MSTResult kruskalResult = KruskalMST.findMST(disconnectedGraph);
        
        // Should not have V-1 edges for disconnected graph
        assertTrue(primResult.getMstEdges().size() < disconnectedGraph.getVertexCount() - 1,
            "Disconnected graph should not produce full MST");
        assertTrue(kruskalResult.getMstEdges().size() < disconnectedGraph.getVertexCount() - 1,
            "Disconnected graph should not produce full MST");
    }
    
    // PERFORMANCE TESTS
    
    @Test
    @DisplayName("Test: Execution time is non-negative")
    void testExecutionTime() {
        MSTResult primResult = PrimMST.findMST(smallGraph);
        MSTResult kruskalResult = KruskalMST.findMST(smallGraph);
        
        assertTrue(primResult.getExecutionTimeMs() >= 0,
            "Prim execution time should be non-negative");
        assertTrue(kruskalResult.getExecutionTimeMs() >= 0,
            "Kruskal execution time should be non-negative");
    }
    
    @Test
    @DisplayName("Test: Operation count is positive")
    void testOperationCount() {
        MSTResult primResult = PrimMST.findMST(smallGraph);
        MSTResult kruskalResult = KruskalMST.findMST(smallGraph);
        
        assertTrue(primResult.getOperationCount() > 0,
            "Prim should perform operations");
        assertTrue(kruskalResult.getOperationCount() > 0,
            "Kruskal should perform operations");
    }
    
    @Test
    @DisplayName("Test: Results are reproducible")
    void testReproducibility() {
        MSTResult prim1 = PrimMST.findMST(smallGraph);
        MSTResult prim2 = PrimMST.findMST(smallGraph);
        
        assertEquals(prim1.getTotalCost(), prim2.getTotalCost(),
            "Multiple runs should produce same cost");
        assertEquals(prim1.getMstEdges().size(), prim2.getMstEdges().size(),
            "Multiple runs should produce same number of edges");
    }
    
    @Test
    @DisplayName("Test: Medium graph performance")
    void testMediumGraph() {
        MSTResult primResult = PrimMST.findMST(mediumGraph);
        MSTResult kruskalResult = KruskalMST.findMST(mediumGraph);
        
        assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost());
        assertEquals(mediumGraph.getVertexCount() - 1, primResult.getMstEdges().size());
    }
    
    // HELPER METHODS
    
    /**
     * Check if edges form a connected graph
     */
    private boolean isFullyConnected(List<Edge> edges, Set<String> vertices) {
        if (vertices.isEmpty()) return true;
        
        Map<String, List<String>> adj = new HashMap<>();
        for (String v : vertices) {
            adj.put(v, new ArrayList<>());
        }
        for (Edge e : edges) {
            adj.get(e.getFrom()).add(e.getTo());
            adj.get(e.getTo()).add(e.getFrom());
        }
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String start = vertices.iterator().next();
        queue.offer(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (String neighbor : adj.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        
        return visited.size() == vertices.size();
    }
}
