package mst;

import java.io.*;
import java.util.*;

//main class to run MST algoritms and generate result

public class Main {
    
    public static void main(String[] args) {
        try {
            System.out.println("=".repeat(70));//readibility))
            System.out.println("MST ALGORITHM COMPARISON - Assignment 3");
            System.out.println("=".repeat(70));//readibility))
            
            // Read input graphs
            JSONHandler jsonHandler = new JSONHandler();
            //String inputFile = "src/data/ass_3_input.json";
            //String inputFile = "src/data/medium_graphs.json";
            //String inputFile = "src/data/large_graphs.json";
            String inputFile = "src/data/extra_large_graphs.json";
            System.out.println("\nReading input from: " + inputFile);
            
            List<Graph> graphs = jsonHandler.readGraphsFromJSON(inputFile);
            System.out.println("Successfully loaded " + graphs.size() + " graphs\n");
            
            // Store results for all graphs
            Map<Integer, JSONHandler.GraphResultData> allResults = new LinkedHashMap<>();
            
            // Process each graph
            for (Graph graph : graphs) {
                System.out.println("Processing " + graph);
                System.out.println("-".repeat(70));//readibility))
                
                // Check if graph is connected(making some log of mistake which also like some sort of readibility i would say)
                if (!graph.isConnected()) {
                    System.out.println("WARNING: Graph " + graph.getGraphId() + 
                                     " is disconnected!");
                }
                
                // Run Prim's algorithm
                MSTResult primResult = PrimMST.findMST(graph);
                System.out.println("Prim:    " + primResult);
                
                // Run Kruskal's algorithm
                MSTResult kruskalResult = KruskalMST.findMST(graph);
                System.out.println("Kruskal: " + kruskalResult);
                
                // Verify results match
                if (primResult.getTotalCost() == kruskalResult.getTotalCost()) {
                    System.out.println("✓ Both algorithms found same MST cost: " + 
                                     primResult.getTotalCost());
                } else {
                    System.out.println("✗ ERROR: Different MST costs!");
                }
                
                System.out.println();
                
                // Store results
                JSONHandler.GraphResultData resultData = 
                    new JSONHandler.GraphResultData(
                        graph.getGraphId(),
                        graph.getVertexCount(),
                        graph.getEdgeCount(),
                        primResult,
                        kruskalResult
                    );
                allResults.put(graph.getGraphId(), resultData);
            }
            
            // Write results to JSON
            //String outputFile = "src/data/ass_3_output_generatedByMyself.json";
            //String outputFile = "src/data/ass_3_output_generatedByMyselfMedium.json";
            //String outputFile = "src/data/ass_3_output_generatedByMyselfLarge.json";
            String outputFile = "src/data/ass_3_output_generatedByMyselfExtraLarge.json";
            jsonHandler.writeResultsToJSON(outputFile, allResults);
            System.out.println("Results written to: " + outputFile);
            
            // Generate CSV comparison table
            generateCSVReport(allResults);
            System.out.println("Comparison table written to: comparison_results.csv");
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("EXECUTION COMPLETED SUCCESSFULLY");
            System.out.println("=".repeat(70));
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Generate CSV file with comparison results
    private static void generateCSVReport(Map<Integer, JSONHandler.GraphResultData> results)
            throws IOException {
        StringBuilder csv = new StringBuilder();
        csv.append("Graph ID,Vertices,Edges,Algorithm,MST Cost,Operations,Time (ms)\n");

        for (JSONHandler.GraphResultData data : results.values()) {
            // Prim row
            csv.append(String.format("%d,%d,%d,%s,%d,%d,%.2f\n",
                    data.graphId,
                    data.vertexCount,
                    data.edgeCount,
                    "Prim",
                    data.primResult.getTotalCost(),
                    data.primResult.getOperationCount(),
                    data.primResult.getExecutionTimeMs()
            ));

            // Kruskal row
            csv.append(String.format("%d,%d,%d,%s,%d,%d,%.2f\n",
                    data.graphId,
                    data.vertexCount,
                    data.edgeCount,
                    "Kruskal",
                    data.kruskalResult.getTotalCost(),
                    data.kruskalResult.getOperationCount(),
                    data.kruskalResult.getExecutionTimeMs()
            ));
        }

        java.nio.file.Files.writeString(
                //java.nio.file.Paths.get("comparison_results.csv"),
                //java.nio.file.Paths.get("comparison_resultsMedium.csv"),
                //java.nio.file.Paths.get("comparison_resultsLarge.csv"),
                java.nio.file.Paths.get("comparison_resultsExtraLarge.csv"),
                csv.toString()
        );
    }

}
