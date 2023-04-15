package edu.asu.scai.rise;

import com.kitfox.svg.A;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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


        /*Merge conflict resolved check in again*/
//        System.out.println("Algorithm.BFS.ordinal(): " + Algorithm.BFS.ordinal());
//        graph.GraphSearch("a","d", Algorithm.BFS.ordinal());
        
//        System.out.println("Algorithm.BFS.ordinal() : " + Algorithm.DFS.ordinal());
//        graph.GraphSearch("b", "d", Algorithm.DFS.ordinal());


        /* PROJECT PART 3 */

        GraphTemplatePattern bfs = new BFS();
        System.out.println("Step 2 BFS");
        bfs.traverse(graph.map, "a", "d");

        GraphTemplatePattern dfs = new DFS();
        System.out.println("Step 2 DFS");
        dfs.traverse(graph.map, "b", "d");

        System.out.println("Step 3 BFS");
        graph.GraphSearch("a", "d", Algorithm.BFS);

        System.out.println("Step 3 DFS");
        graph.GraphSearch("b", "d", Algorithm.DFS);

    }
}
