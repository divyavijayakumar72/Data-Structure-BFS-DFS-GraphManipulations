package edu.asu.scai.rise;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GraphManager<String> graph = new GraphManager<>();

        /* FEATURE 1 */
        graph.parseGraph("input.dot");
        graph.countNodes();
        graph.getLabel();
        graph.countEdges();
        graph.getEdgeDirection();
        graph.containsEdge("a", "b");
        graph.toString();
        graph.outputGraph("output.txt");
        /* FEATURE 1 */

    }
}
