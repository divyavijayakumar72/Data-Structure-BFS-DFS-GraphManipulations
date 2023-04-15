package edu.asu.scai.rise;

import java.util.*;

public class BFSStrategy implements SearchAlgorithm {
    @Override
    public Path search(String src, String dst, Map<String, List<String>> map) {

        Map<String, String> path = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(src);
        visited.add(src);

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
                Path path1 = new Path((List<String>) result);
                System.out.println("The BFS path using Strategy pattern is " +  path1);
                return path1;
            }
            ArrayList<String> neighbors = getNeighbors(map, curr);
            // if curr is not equal to dst
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        path.put(neighbor, curr);
                        queue.add(neighbor);
                    }
                }
        }
        return null;
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
