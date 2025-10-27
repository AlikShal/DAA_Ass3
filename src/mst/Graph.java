package mst;

import java.util.*;


 //Represents weighted undirected graph
 //This is BONUS element for clean architecture
public class Graph {
    private int graphId;
    private Set<String> vertices;
    private List<Edge> edges;
    private Map<String, List<Edge>> adjacencyList;

    //Creates a new graph: @param graphId unique identifier; @param vertices set of vertex names; @param edges list of edges

    public Graph(int graphId, Set<String> vertices, List<Edge> edges) {
        this.graphId = graphId;
        this.vertices = new HashSet<>(vertices);
        this.edges = new ArrayList<>(edges);
        buildAdjacencyList();
    }

    //build adj list for faster travesal

    private void buildAdjacencyList() {
        adjacencyList = new HashMap<>();
        for (String vertex : vertices) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
        for (Edge edge : edges) {
            // Adding edge in both directions for undirected graph
            adjacencyList.get(edge.getFrom()).add(edge);
            adjacencyList.get(edge.getTo()).add(
                new Edge(edge.getTo(), edge.getFrom(), edge.getWeight())
            );
        }
    }

    public int getGraphId() {
        return graphId;
    }

    public Set<String> getVertices() {
        return new HashSet<>(vertices);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    //Checking if graph is connected using BFS

    public boolean isConnected() {
        if (vertices.isEmpty()) return true;
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String start = vertices.iterator().next();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (Edge edge : adjacencyList.get(current)) {
                if (!visited.contains(edge.getTo())) {
                    visited.add(edge.getTo());
                    queue.offer(edge.getTo());
                }
            }
        }

        return visited.size() == vertices.size();
    }

    @Override
    public String toString() {
        return "Graph " + graphId + ": " + vertices.size() + 
               " vertices, " + edges.size() + " edges";
    }
}
