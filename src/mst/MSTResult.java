package mst;

import java.util.*;

//Stores the result of MST algorithm execution

public class MSTResult {
    private List<Edge> mstEdges;
    private int totalCost;
    private int operationCount;
    private double executionTimeMs;
    private String algorithmName;

    public MSTResult() {
        this.mstEdges = new ArrayList<>();
        this.totalCost = 0;
        this.operationCount = 0;
        this.executionTimeMs = 0.0;
        this.algorithmName = "";
    }

    public MSTResult(String algorithmName) {
        this();
        this.algorithmName = algorithmName;
    }

    public List<Edge> getMstEdges() {
        return new ArrayList<>(mstEdges);
    }

    public void setMstEdges(List<Edge> mstEdges) {
        this.mstEdges = new ArrayList<>(mstEdges);
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public void setOperationCount(int operationCount) {
        this.operationCount = operationCount;
    }

    public double getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(double executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    //Calculate total cost from edges
    public void calculateTotalCost() {
        totalCost = 0;
        for (Edge edge : mstEdges) {
            totalCost += edge.getWeight();
        }
    }

    @Override
    public String toString() {
        return algorithmName + " MST: " + mstEdges.size() + " edges, " +
               "total cost: " + totalCost + ", operations: " + operationCount +
               ", time: " + String.format("%.2f", executionTimeMs) + "ms";
    }
}
