package edu.asu.scai.rise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class DFS extends GraphTemplatePattern {
    private boolean found;

    @Override
    protected void traverseHelperFunc(Map<String, List<String>> map, Queue<String> queue, String node, String destination, Map<String, String> parentMap) {
        visited.add(node);

        if(node.equals(destination)) {
            found = true;
            return;
        }

        ArrayList<String> neighbors = getNeighbors(map, node);

        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor) && !found) {
                parentMap.put(neighbor, node);
                traverseHelperFunc(map, queue, neighbor, destination, parentMap);
            }
        }


    }
}
