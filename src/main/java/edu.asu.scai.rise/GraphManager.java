package edu.asu.scai.rise;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
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

    public Map<String, String> addEdgeFromFile(String srcLabel, String dstLabel) {

        if (!map.containsKey(srcLabel)) {
            addNode(srcLabel);
        }

        if (!map.containsKey(dstLabel)) {
            addNode(dstLabel);
        }

        map.get(srcLabel).add(dstLabel);

        for(String key: map.keySet()) {
            for(int i=0; i<map.get(key).size();i++) {
                m.put(key, map.get(key).get(i));
            }
        }
        System.out.println("map " + m);
        return m;
    }

    public int countNodes() {
        return map.keySet().size();
    }

    public ArrayList<String> getLabel() {
        ArrayList<String> arrList = new ArrayList<>();
        for(String data: map.keySet()) {
            arrList.add(data);
        }
        return arrList;
    }

    public int countEdges() {
        int count = 0;
        for (String v : map.keySet()) {
            count += map.get(v).size();
        }
        return count;
    }

    public Map<String, String> getEdgeDirection() {
        for(String key: map.keySet()) {
            if(!map.get(key).equals("[]")) {
                for(int i=0; i<map.get(key).size();i++) {
                    System.out.println(key + " -> " + map.get(key).get(i));
                    edgeDirection.putIfAbsent(key, map.get(key).get(i));
                }
            }
        }
        System.out.println("edge direction map " + edgeDirection);
        return edgeDirection;
    }

    public boolean containsEdge(String source, String destination) {
        if (map.get(source).contains(destination)) {
            return true;
        } else {
            return false;
        }
    }

    public java.lang.String toString() {
        convertMapToGraph();
        newGraph.toString();
        return newGraph.toString();
    }

    public void outputGraph(String filepath) throws IOException {
        convertMapToGraph();
        Graphviz.fromString((java.lang.String) newGraph.toString()).render(Format.DOT).toFile(new File((java.lang.String) filepath));
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

    public Map<String, List<String>> addNode(String label) {
        if(!map.containsKey(label)) {
            map.put(label, new LinkedList<String>());
        }
        return map;
    }
    /* FEATURE 1 */
    
}