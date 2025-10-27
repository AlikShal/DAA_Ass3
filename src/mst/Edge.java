package mst;
//This is BONUS element for clean architecture
//edge in a weigthed graph, undirected
public class Edge implements Comparable<Edge> {
    private String from;
    private String to;
    private int weight;

    //Creates a new edge: @param from source vertex; @param to destination vertex; @param weight edge weight (cost)

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    //compare edges by weight for sort
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return from + " -- " + to + " (weight: " + weight + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return weight == edge.weight &&
               ((from.equals(edge.from) && to.equals(edge.to)) ||
                (from.equals(edge.to) && to.equals(edge.from)));
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode() + weight;
    }
}
