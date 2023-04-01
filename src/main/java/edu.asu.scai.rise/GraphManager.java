package edu.asu.scai.rise;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.Renderer;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class GraphManager<String> {
    MutableGraph mg;
    MutableGraph newGraph;

    private Map<String, List<String>> map = new HashMap<>();
    Map<String, String> edgeDirection = new HashMap<>();
    Map<String, String> m = new HashMap<>();



    public void parseGraph(String filepath) throws IOException {
        mg = new Parser().read(new File((java.lang.String) filepath));
        BufferedReader reader = new BufferedReader(new FileReader((java.lang.String) filepath));
        java.lang.String line;

        while((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("digraph") || line.startsWith("graph")) {
                continue;
            }
            if (line.startsWith("}")) {
                break;
            }
            java.lang.String[] parts = line.split("->");
            if (parts.length == 2) {
                java.lang.String source = parts[0].trim();
                java.lang.String destination = parts[1].trim().replace(";", "");
                addEdgeFromFile((String) source, (String) destination);
            }
        }
        reader.close();
    }

    // REFACTOR 1: adding method "addKeyValue" to improve code re-usability
    public void addKeyValue(Map map, String key, String value) {
        map.put(key, value);
    }

    // REFACTOR 2: adding method "createGraph" to improve code re-usability and readability
    public Renderer createRenderer(MutableGraph graph, Format format) {
        return Graphviz.fromString(graph.toString()).render(format);
    }

    // From input.dot construct edges in map as k-v pairs
    public Map<String, String> addEdgeFromFile(String srcLabel, String dstLabel) {

        if (!map.containsKey(srcLabel)) {
            addNode(srcLabel);
        }

        if (!map.containsKey(dstLabel)) {
            addNode(dstLabel);
        }

        map.get(srcLabel).add(dstLabel);

        /* REFACTOR 3: change from for loop to foreach loop (line 78) to improve performance and simplicity of code */
        for(String key: map.keySet()) {
            for (String value : map.get(key)) {
                addKeyValue(m, key, value);
            }
        }

        return m;

    }

    public int countNodes() {
        return map.keySet().size();
    }

    // GET ALL NODES
    public ArrayList<String> getLabel() {
        ArrayList<String> arrList = new ArrayList<>();
        for(String data: map.keySet()) {
            arrList.add(data);
        }
//        System.out.println("Nodes list " + arrList);
        return arrList;
    }

    public int countEdges() {
        int count = 0;
        for (String v : map.keySet()) {
            count += map.get(v).size();
        }
//        System.out.println("edges count " + count);
        return count;
    }

    // GET NEIGHBORS
    public Map<String, String> getEdgeDirection() {
        /* REFACTOR 3: change from for loop to foreach loop (line 115) to improve performance and simplicity of code */
        for(String key: map.keySet()) {
            if(!map.get(key).equals("[]")) {
                for(String value : map.get(key)) {
                    edgeDirection.putIfAbsent(key, value);
                }
            }
        }

        return edgeDirection;
    }



    public boolean containsEdge(String source, String destination) {
        /* REFACTOR 4: rewriting if-else as a ternary operator to reduce lines of code */
        return map.get(source).contains(destination) ? true : false;
    }

    public java.lang.String toString() {
        convertMapToGraph();
        newGraph.toString();
        return newGraph.toString();
    }

    public void outputGraph(String filepath) throws IOException {
        convertMapToGraph();
        // REFACTOR 2: using method in place of whole line of code for better readability and code re-usability
        createRenderer(newGraph, Format.DOT).toFile(new File((java.lang.String) filepath));
    }

    public MutableGraph convertMapToGraph() {
        MutableGraph g = mutGraph().setDirected(true);

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String from = entry.getKey();
            g.add(mutNode((java.lang.String) from));
            for (String to : entry.getValue()) {
                g.add(mutNode((java.lang.String) from).addLink(mutNode((java.lang.String) to)));
            }
        }
        newGraph = g;
        return newGraph;
    }

    /* FEATURE 1 */

    /* FEATURE 2 */
    public Map<String, List<String>> addNode(String label) {
        if(!map.containsKey(label)) {
            map.put(label, new LinkedList<String>());
        }
        return map;
    }

    public Map<String, List<String>> addNodes(String[] label) {
        for(String val: label) {
            if(!map.containsKey(val)) {
                map.put(val, new LinkedList<String>());
            }
        }
        return map;
    }

    public boolean hasNode(String label) {
        /* REFACTOR 4: rewriting if-else as a ternary operator to reduce lines of code */
        return map.containsKey(label) ? true : false;
    }

    /* PART 2 - DFS helper method*/
    private boolean dfsHelper(String src, String dest, Set<String> visited, List<String> path) {
        visited.add(src); // checking src node as visited
        path.add(src); // adding source node to path
        if (src != null && dest != null && src.equals(dest)) {
            return true;
        }
        for (String adj : getNeighbors(src)) {
            if (!visited.contains(adj)) {
                if (dfsHelper(adj, dest, visited, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public Map<String, List<String>> removeNode(String label) {
        if(map.containsKey(label)) {
            map.remove(label);
        }

        for(String key: map.keySet()) {
            if(map.get(key).contains(label)) {
                map.get(key).remove(label);
            }
        }
        return map;
    }

    public Map<String, List<String>> removeNodes(String[] label) {
        for(String val: label) {
            if(map.containsKey(val)) {
                map.remove(val);
            }
        }

        for(String key: map.keySet()) {
            if(map.get(key).contains(label)) {
                map.get(key).remove(label);
            }
        }
        return map;
    }
    /* FEATURE 2 */

    /* FEATURE 3 */
    public Map<String, List<String>> addEdge(String srcLabel, String dstLabel) {
        addEdgeFromFile(srcLabel, dstLabel);
        map.remove(dstLabel);
        return map;
    }

    public Map<String, List<String>> removeEdge(String source, String destination) {
        for(String val: map.get(source)) {
            map.remove(source);
            addNode(source);
            addNode(destination);
        }
        return map;
    }
    /* FEATURE 3 */

    /* PART 2 - GET NEIGHBORS OF NODE */
    public ArrayList<String> getNeighbors(String node) {
        ArrayList<String> neighbors = new ArrayList<>();
        if(map.containsKey(node) && map.get(node).size() != 0) {
            neighbors.add((String) map.get(node).get(0));
        } else {
            return neighbors;
        }
        return neighbors;
    }
    /* PART 2 - GET NEIGHBORS OF NODE */

    /* FEATURE 4 */
    public void outputDOTGraph(String path) throws IOException {
        convertMapToGraph();
        // REFACTOR 2: using method in place of whole line of code for better readability and code re-usability
        createRenderer(newGraph, Format.DOT).toFile(new File((java.lang.String) path));
    }

    public void outputGraphics(String path, String format) {
        convertMapToGraph();
        // REFACTOR 2: using method in place of whole line of code for better readability and code re-usability
        RenderedImage img = createRenderer(newGraph, Format.PNG).toImage();

        try {
            ImageIO.write(img, (java.lang.String) format, new File((java.lang.String) path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* FEATURE 4 */


    /* PART 2 - BFS & DFS combined*/
    public Path GraphSearch(String src, String dst, int value) {
        if (value == 0) {
            Map<String, String> path = new HashMap<>();
            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.add(src); // Q.enqueue(root)
            visited.add(src); // label root as explored

            while (!queue.isEmpty()) { // while Q is not empty do
                String curr = queue.poll(); // curr := Q.dequeue()
                if (curr != null && dst != null && curr.equals(dst)) { // if curr is the destination then

                    // found the destination node, backtrack to construct the path
                    List<String> result = new ArrayList<>();
                    String node = dst;
                    while (!node.equals(src)) {
                        result.add(node);
                        node = path.get(node);
                    }
                    result.add(src);
                    Collections.reverse(result);
                    Path path1 = new Path((List<java.lang.String>) result);
                    System.out.println("The BFS path is " +  path1);
                    return path1;
                }
                // if curr is not equal to dst
                if(getNeighbors(curr) != null) {
                    for (String neighbor : getNeighbors(curr)) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            addKeyValue(path, neighbor, curr);
                            queue.add(neighbor);
                        }
                    }
                }
            }
            // destination node not found
            System.out.println("No path found using BFS approach");
            return null;
        } else if (value == 1){
            /* PART 2 - DFS */
            Set<String> visited = new HashSet<>();
            List<String> path = new ArrayList<>();
            dfsHelper(src, dst, visited, path);
            Path path2 = new Path((List<java.lang.String>) path);

            if(!path2.toString().equals("")) {
                System.out.println("The DFS path is " + path2);
                return path2;
            } else {
                System.out.println("No path found using DFS approach");
                return null;
            }

        } else {
            return null;
        }
    }
}
