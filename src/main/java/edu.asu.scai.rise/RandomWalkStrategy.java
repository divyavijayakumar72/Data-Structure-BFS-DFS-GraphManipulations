package edu.asu.scai.rise;

import java.util.*;

public class RandomWalkStrategy implements SearchAlgorithm {
    @Override
    public Path search(String src, String dst, Map<String, List<String>> map) {
        Set<String> visited = new HashSet<>();
        List<String> res = new ArrayList<>();
        dfsHelper(src, dst, visited, res, map);
        Path path2 = new Path((List<java.lang.String>) res);

        if(!path2.toString().equals("")) {
            System.out.println("The random path using Strategy pattern is " + path2);
            return path2;
        } else {
            System.out.println("No path found using Random walk search approach");
            return null;
        }
    }

    private boolean dfsHelper(String src, String dest, Set<String> visited, List<String> res, Map<String, List<String>> map) {
        visited.add(src); // checking src node as visited
        res.add(src); // adding source node to path

        System.out.println("Visiting Path using Strategy Pattern {nodes = " + res + "}");

        Random random = new Random();
        if (src != null && dest != null && src.equals(dest)) {
            return true;
        }

        List<String> neighbors = getNeighbors(map, src);
        Collections.shuffle(neighbors, random);

        for (String adj : neighbors) {
            /* REFACTOR 5: Removing and/or combining 2 if-conditions to reduce lines of code */
            if (!visited.contains(adj) && dfsHelper(adj, dest, visited, res, map)) {
                return true;
            }
        }
        // when no neighbors this executes
        res.remove(res.size() - 1);
        return false;
    }

    private ArrayList<String> getNeighbors(Map<String, List<String>> map, String node) {
        ArrayList<String> neighbors = new ArrayList<>();
        if(map.containsKey(node) && map.get(node).size() != 0) {
            for(int i=0;i<map.get(node).size();i++) {
                neighbors.add(map.get(node).get(i));
            }
        }
        return neighbors;
    }

}
