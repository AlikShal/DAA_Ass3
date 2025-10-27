package mst;

import java.util.*;

/**
 Implementation of Kruskal's algorithm for finding Minimum Spanning Tree
 */
public class KruskalMST {
    
    //Finding MST by Kruskal algoritm: @param graph input graph (undirected); @return MSTResult with edges, total cost, ops count, time taken

    public static MSTResult findMST(Graph graph) {
        MSTResult result = new MSTResult("Kruskal");
        
        // Check if graph is empty
        if (graph.getVertices().isEmpty()) {
            return result;
        }

        long startTime = System.nanoTime();
        
        List<Edge> mstEdges = new ArrayList<>();
        UnionFind uf = new UnionFind(graph.getVertices());
        
        // Sort all edges by weight
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);
        
        int operationCount = 0;
        
        // Iterate through sorted edges
        for (Edge edge : sortedEdges) {
            operationCount++; // Count edge inspection
            
            String from = edge.getFrom();
            String to = edge.getTo();
            
            // Check if adding edge creates cycle
            if (uf.union(from, to)) {
                mstEdges.add(edge);
                
                // Stop when we have V-1 edges
                if (mstEdges.size() == graph.getVertexCount() - 1) {
                    break;
                }
            }
        }
        
        // Add Union-Find operations to total count
        operationCount += uf.getOperationCount();
        
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;
        
        result.setMstEdges(mstEdges);
        result.calculateTotalCost();
        result.setOperationCount(operationCount);
        result.setExecutionTimeMs(executionTime);
        
        return result;
    }
    
    //Check if MST is valid (connects all vertices)

    public static boolean isValidMST(MSTResult result, int vertexCount) {
        if (vertexCount == 0) return true;
        return result.getMstEdges().size() == vertexCount - 1;
    }
    
    //Check if MST contains cycle using DFS

    public static boolean hasCycle(List<Edge> edges, Set<String> vertices) {
        UnionFind uf = new UnionFind(vertices);
        for (Edge edge : edges) {
            if (!uf.union(edge.getFrom(), edge.getTo())) {
                return true; // Cycle detected
            }
        }
        return false;
    }
}
