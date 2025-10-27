package mst;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 handels reading input json and writing output json files
 */
public class JSONHandler {
    private Gson gson;

    public JSONHandler() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    //Read graphs from input JSON file

    public List<Graph> readGraphsFromJSON(String filename) throws IOException {
        String jsonContent = Files.readString(Paths.get(filename));
        JsonObject rootObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        JsonArray graphsArray = rootObject.getAsJsonArray("graphs");

        List<Graph> graphs = new ArrayList<>();

        for (JsonElement graphElement : graphsArray) {
            JsonObject graphObj = graphElement.getAsJsonObject();
            
            int id = graphObj.get("id").getAsInt();
            
            // Read vertices
            JsonArray nodesArray = graphObj.getAsJsonArray("nodes");
            Set<String> vertices = new HashSet<>();
            for (JsonElement node : nodesArray) {
                vertices.add(node.getAsString());
            }
            
            // Read edges
            JsonArray edgesArray = graphObj.getAsJsonArray("edges");
            List<Edge> edges = new ArrayList<>();
            for (JsonElement edgeElement : edgesArray) {
                JsonObject edgeObj = edgeElement.getAsJsonObject();
                String from = edgeObj.get("from").getAsString();
                String to = edgeObj.get("to").getAsString();
                int weight = edgeObj.get("weight").getAsInt();
                edges.add(new Edge(from, to, weight));
            }
            
            graphs.add(new Graph(id, vertices, edges));
        }

        return graphs;
    }

    //Write results to output JSON file

    public void writeResultsToJSON(String filename, Map<Integer, GraphResultData> results) 
            throws IOException {
        JsonObject root = new JsonObject();
        JsonArray resultsArray = new JsonArray();

        for (Map.Entry<Integer, GraphResultData> entry : results.entrySet()) {
            GraphResultData data = entry.getValue();
            JsonObject graphResult = new JsonObject();
            
            graphResult.addProperty("graph_id", data.graphId);
            
            // Input stats
            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", data.vertexCount);
            inputStats.addProperty("edges", data.edgeCount);
            graphResult.add("input_stats", inputStats);
            
            // Prim results
            graphResult.add("prim", createAlgorithmResult(data.primResult));
            
            // Kruskal results
            graphResult.add("kruskal", createAlgorithmResult(data.kruskalResult));
            
            resultsArray.add(graphResult);
        }

        root.add("results", resultsArray);
        
        String jsonOutput = gson.toJson(root);
        Files.writeString(Paths.get(filename), jsonOutput);
    }

    //Creating JSON object for algorithm result

    private JsonObject createAlgorithmResult(MSTResult result) {
        JsonObject obj = new JsonObject();
        
        JsonArray mstEdgesArray = new JsonArray();
        for (Edge edge : result.getMstEdges()) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", edge.getFrom());
            edgeObj.addProperty("to", edge.getTo());
            edgeObj.addProperty("weight", edge.getWeight());
            mstEdgesArray.add(edgeObj);
        }
        
        obj.add("mst_edges", mstEdgesArray);
        obj.addProperty("total_cost", result.getTotalCost());
        obj.addProperty("operations_count", result.getOperationCount());
        obj.addProperty("execution_time_ms", 
            Math.round(result.getExecutionTimeMs() * 100.0) / 100.0);
        
        return obj;
    }

    //Data class to hold graph results

    public static class GraphResultData {
        public int graphId;
        public int vertexCount;
        public int edgeCount;
        public MSTResult primResult;
        public MSTResult kruskalResult;

        public GraphResultData(int graphId, int vertexCount, int edgeCount,
                              MSTResult primResult, MSTResult kruskalResult) {
            this.graphId = graphId;
            this.vertexCount = vertexCount;
            this.edgeCount = edgeCount;
            this.primResult = primResult;
            this.kruskalResult = kruskalResult;
        }
    }
}
