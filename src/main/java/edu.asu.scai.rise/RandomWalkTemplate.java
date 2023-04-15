package edu.asu.scai.rise;

import java.util.*;

public class RandomWalkTemplate extends GraphTemplatePattern {
    private boolean found;
    @Override
    protected void traverseHelperFunc(Map<String, List<String>> map, Queue<String> queue, String node, String destination, Map<String, String> parentMap, List<String> res) {
        Random random = new Random();

        visited.add(node);
        res.add(node);

        System.out.println("Visiting Path using Template Pattern {nodes = " + res + "}");

        if(node.equals(destination)) {
            found = true;
            return;
        }

        List<String> neighbors = getNeighbors(map, node);
        Collections.shuffle(neighbors, random);

        boolean hasUnvisitedNeighbors = false;
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                hasUnvisitedNeighbors = true;
                break;
            }
        }

        if (hasUnvisitedNeighbors) {
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor) && !found) {
                    parentMap.put(neighbor, node);
                    traverseHelperFunc(map, queue, neighbor, destination, parentMap, res);
                }
            }
        }

        res.remove(res.size() - 1);
    }
}
