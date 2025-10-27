package mst;

import java.util.*;

/**
 Union-Find (DSU) struc
 used in kruskal to detect cycles, so we dont make loops
 */
public class UnionFind {
    private Map<String, String> parent;
    private Map<String, Integer> rank;
    private int operationCount;

    /**
     * init union-find struct (dsu)
     * @param vertices set of all vertexes
     */
    public UnionFind(Set<String> vertices) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        operationCount = 0;

        for (String vertex : vertices) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }
    }

    /**
     * Find the root of the set containing vertex
     * uses path comprassion for speed
     */
    public String find(String vertex) {
        operationCount++;
        if (!parent.get(vertex).equals(vertex)) {
            parent.put(vertex, find(parent.get(vertex)));
        }
        return parent.get(vertex);
    }

    /**
     * Union sets with vertices a and b
     * Uses union by rank for optimization
     * @return true if merged, false if alredy same set
     */
    public boolean union(String a, String b) {
        operationCount++;
        String rootA = find(a);
        String rootB = find(b);

        if (rootA.equals(rootB)) {
            return false; // Already in same set, would create cycle
        }

        // Union by rank
        if (rank.get(rootA) < rank.get(rootB)) {
            parent.put(rootA, rootB);
        } else if (rank.get(rootB) < rank.get(rootA)) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rank.get(rootA) + 1);
        }

        return true;
    }

    //Get total number of operations performed

    public int getOperationCount() {
        return operationCount;
    }

    //Reset operation counter

    public void resetOperationCount() {
        operationCount = 0;
    }
}
