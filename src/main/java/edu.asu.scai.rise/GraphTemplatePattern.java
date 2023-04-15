package edu.asu.scai.rise;

import java.util.*;

public abstract class GraphTemplatePattern {
    protected Set<String> visited;

    public Path traverse(Map<String, List<String>> map, String source, String destination) {

        visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> path = new HashMap<>();
        List<String> result = new ArrayList<>();

        // if value = 0, choose BFS else choose DFS
        traverseHelperFunc(map, queue, source, destination, path);

        // found the destination node, backtrack to construct the path
        if (visited.contains(destination)) { // REVISE
            String node = destination;
            while (!node.equals(source)) {
                result.add(node);
                node = path.get(node);
            }
            result.add(source);
            Collections.reverse(result);
            Path path1 = new Path((List<java.lang.String>) result);
            System.out.println("The path using Template Pattern is " +  path1);
            return path1;
        }
        return null;
    }

    protected abstract void traverseHelperFunc(Map<String, List<String>> map, Queue<String> queue, String node, String destination, Map<String, String> parentMap);

    protected ArrayList<String> getNeighbors(Map<String, List<String>> map, String node) {
        ArrayList<String> neighbors = new ArrayList<>();
        if(map.containsKey(node) && map.get(node).size() != 0) {
            for(int i=0;i<map.get(node).size();i++) {
                neighbors.add(map.get(node).get(i));
            }
        }
        return neighbors;
    }
}

