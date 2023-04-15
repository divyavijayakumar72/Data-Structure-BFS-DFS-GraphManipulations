package edu.asu.scai.rise;

import java.util.*;

public class DFSStrategy implements SearchAlgorithm {
    @Override
    public Path search(String src, String dst, Map<String, List<String>> map) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        dfsHelper(map, src, dst, visited, path);
        Path path2 = new Path((List<java.lang.String>) path);

        if(!path2.toString().equals("")) {
            System.out.println("The DFS path is " + path2);
            return path2;
        } else {
            System.out.println("No path found using DFS approach");
            return null;
        }
    }

    private boolean dfsHelper(Map<String, List<String>> map, String src, String dest, Set<String> visited, List<String> path) {
        visited.add(src); // checking src node as visited
        path.add(src); // adding source node to path
        if (src != null && dest != null && src.equals(dest)) {
            return true;
        }

        ArrayList<String> neighbors = getNeighbors(map, src);

        for (String adj : neighbors) {
            /* REFACTOR 5: Removing and/or combining 2 if-conditions to reduce lines of code */
            if (!visited.contains(adj) && dfsHelper(map, adj, dest, visited, path)) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    private ArrayList<String> getNeighbors(Map<String, List<String>> map, String node) {
        ArrayList<String> neighbors = new ArrayList<>();
        if(map.containsKey(node) && map.get(node).size() != 0) {
            neighbors.add((String) map.get(node).get(0));
        }
        return neighbors;
    }
}
