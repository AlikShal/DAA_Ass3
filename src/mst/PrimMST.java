package mst;

import java.util.*;

//Implementation of Prim's algorithm for finding Minimum Spanning Tree

public class PrimMST {
    
    //Find MST by Prim algoritm: @param graph input graph (undirected); @return MSTResult with picked edges, total cost, ops cnt, time

    public static MSTResult findMST(Graph graph) {
        MSTResult result = new MSTResult("Prim");
        
        // Check if graph is empty
        if (graph.getVertices().isEmpty()) {
            return result;
        }

        long startTime = System.nanoTime();
        int operationCount = 0;
        
        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        
        Map<String, List<Edge>> adjList = graph.getAdjacencyList();
        
        // Start from arbitrary vertex
        String startVertex = graph.getVertices().iterator().next();
        visited.add(startVertex);
        
        // Add all edges from start vertex to heap
        for (Edge edge : adjList.get(startVertex)) {
            minHeap.offer(edge);
        }
        
        // Continue until all vertices are visited or heap is empty
        while (!minHeap.isEmpty() && visited.size() < graph.getVertexCount()) {
            Edge minEdge = minHeap.poll();
            operationCount++; // Count poll operation
            
            String nextVertex = minEdge.getTo();
            
            // Skip if vertex already visited
            if (visited.contains(nextVertex)) {
                continue;
            }
            
            // Add edge to MST
            mstEdges.add(minEdge);
            visited.add(nextVertex);
            
            // Add all edges from newly visited vertex
            for (Edge edge : adjList.get(nextVertex)) {
                operationCount++; // Count each edge inspection
                if (!visited.contains(edge.getTo())) {
                    minHeap.offer(edge);
                }
            }
        }
        
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;
        
        result.setMstEdges(mstEdges);
        result.calculateTotalCost();
        result.setOperationCount(operationCount);
        result.setExecutionTimeMs(executionTime);
        
        return result;
    }
    
    //check mst valid, connects all vertexes
    public static boolean isValidMST(MSTResult result, int vertexCount) {
        if (vertexCount == 0) return true;
        return result.getMstEdges().size() == vertexCount - 1;
    }
}
