package edu.asu.scai.rise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphManagerTest {
    GraphManager<String> g = new GraphManager<>();

    @Before
    public void setup() throws Exception {
        g.parseGraph("input.dot");
    }

    /* FEATURE 1 */
    @Test
    public void testParseGraph() {
        Assert.assertEquals(4, g.countNodes());
        Assert.assertEquals(4, g.countEdges());
        Assert.assertTrue(g.containsEdge("a", "b"));
        Assert.assertTrue(g.containsEdge("b", "c"));
        Assert.assertTrue(g.containsEdge("c", "d"));
        Assert.assertTrue(g.containsEdge("d", "b"));
    }

    @Test
    public void testCountNodes() {
        g.countNodes();
        Assert.assertEquals(4, g.countNodes());
    }

    @Test
    public void testGetLabel() {
        g.getLabel();
        ArrayList<String> labelList = new ArrayList<String>();
        labelList.add("a");
        labelList.add("b");
        labelList.add("c");
        labelList.add("d");
        Assert.assertEquals(labelList, g.getLabel());
    }

    @Test
    public void testCountEdges() {
        g.countEdges();
        Assert.assertEquals(4, g.countEdges());
    }

    @Test
    public void testGetEdgeDirection() {
        g.getEdgeDirection();
        Map<String, String> expected = new HashMap<>();
        Map<String, String> actual = new HashMap<>();
        expected.put("a", "b");
        expected.put("b", "c");
        expected.put("c", "d");
        expected.put("d", "b");
        g.addEdgeFromFile("a", "b");
        g.addEdgeFromFile("b", "c");
        g.addEdgeFromFile("c", "d");
        g.addEdgeFromFile("d", "b");

//        System.out.println(g.m.toString());
        actual = g.m;
//        System.out.println("testedgedirction " + g.m.toString() + expected);
        Assert.assertEquals(expected, g.m);
    }

    @Test
    public void testContainsEdge() {
        boolean val = g.containsEdge("a", "b");
        Assert.assertEquals(true, val);
    }

    @Test
    public void testContainsEdge2() {
        boolean val2 = g.containsEdge("a", "c");
        Assert.assertEquals(false, val2);
    }

    @Test
    public void testToString() {
        g.convertMapToGraph();
        g.toString();
        String expectedGraph = "digraph {\n" +
                "\"a\" -> \"b\"\n" +
                "\"b\" -> \"c\"\n" +
                "\"c\" -> \"d\"\n" +
                "\"d\" -> \"b\"\n" +
                "}";
        Assert.assertEquals(expectedGraph, g.toString());
    }

    @Test
    public void testOutputGraph() throws IOException {
        String outputFile = "output.dot";
        g.outputGraph(outputFile);
        String output = Files.readString(Paths.get(outputFile));

        String expected = Files.readString(Paths.get("output.txt"));
        System.out.println("output: " + output + " " + expected);
        Assert.assertEquals(expected, output);
    }
   /* FEATURE 1 */


}
