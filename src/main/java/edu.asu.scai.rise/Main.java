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

        /* FEATURE 2 */
        String nodeList[] = {"q", "w", "r", "t", "h", "m"};
        String nodeListRemoved[] = {"w", "r"};
        graph.addNode("e");
        graph.addNodes(nodeList);
        graph.removeNode("e");
        graph.removeNodes(nodeListRemoved);
        /* FEATURE 2 */

        /* FEATURE 3 */
        graph.addEdge("q", "t");
        graph.removeEdge("q", "t");
        /* FEATURE 3 */

        /* FEATURE 4 */
        graph.outputDOTGraph("output.dot");
        graph.outputGraphics("response.png", "png");
        /* FEATURE 4 */

        /* PART 2- STEP 4 b added- DFS */
        graph.GraphSearch("b","d"); //added
        /* PART 2 - STEP 4 b added- DFS */



    }
}
