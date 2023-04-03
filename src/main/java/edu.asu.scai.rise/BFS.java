package edu.asu.scai.rise;

import java.util.*;

public class BFS extends GraphTemplatePattern {
    @Override
    protected void traverseHelperFunc(Map<String, List<String>> map, Queue<String> queue, String src, String dst, Map<String, String> parentMap) {
        visited.add(src);
        queue.add(src);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr != null && dst != null && curr.equals(dst)) { // if curr is the destination then
                return;
            }

            ArrayList<String> neighbors = getNeighbors(map, curr);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    parentMap.put(neighbor, curr);
                }
            }
        }
    }
}
