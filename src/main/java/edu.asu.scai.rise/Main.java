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
        String nodeList[] = {"q", "w", "r", "t", "i", "m"};
        String nodeListRemoved[] = {"w", "r"};
        graph.addNode("x");
        graph.addNodes(nodeList);
        graph.removeNode("x");
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
        /* COMMENTING THIS SO THAT STRATEGY PATTERN CAN BE USED */
//        graph.GraphSearch("a","d", Algorithm.BFS.ordinal());
//
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

        System.out.println("Step 4 Random walk search using the input file provided in Assignment instructions");
        graph.parseGraph("random-walk-input.dot");
        graph.randomDFSSearch("a","c");

        /* TEMPLATE PATTERN RANDOM WALK SEARCH*/
        GraphTemplatePattern randomWalk = new RandomWalkTemplate();
        System.out.println("Step 4 Random Walk Search with Template Pattern");
        graph.parseGraph("random-walk-input.dot");
        randomWalk.traverse(graph.map, "a", "c");

        /* STRATEGY PATTERN RANDOM WALK SEARCH*/
        System.out.println("Step 4 Random Walk Search with Strategy Pattern");
        graph.GraphSearch("a", "c", Algorithm.RWS);

    }
}
