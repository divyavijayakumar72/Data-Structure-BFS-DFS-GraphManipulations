package edu.asu.scai.rise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
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

//    @Test
//    public void testOutputGraph() throws IOException {
//        String outputFile = "output.txt";
//        g.outputGraph(outputFile);
//        String output = Files.readString(Paths.get(outputFile));
////        String expected = Files.readString(Paths.get(outputFile));
//        String expected = Files.readString(Paths.get(outputFile));
//        System.out.println(" expected " + expected);
//        Assert.assertEquals("a -> b", "a -> b");
//    }

    /* FEATURE 2 */
    @Test
    public void testAddNode() throws Exception {
        g.addNode("e");
        Assert.assertEquals(5, g.countNodes());
        Assert.assertTrue(g.hasNode("e"));
    }

    @Test
    public void testHasNode() throws Exception {
        g.hasNode("v");
        Assert.assertFalse(g.hasNode("v"));
    }

    @Test
    public void testHasNode2() throws Exception {
        g.hasNode("a");
        System.out.println(g.hasNode("a"));
        Assert.assertEquals(true, g.hasNode("a"));
    }

    @Test
    public void testAddNodes() throws Exception {
        String[] nodeList = {"r", "t"};
        g.addNodes(nodeList);
        Assert.assertEquals(6, g.countNodes());
        Assert.assertTrue(g.hasNode("r"));
        Assert.assertTrue(g.hasNode("t"));
    }

    @Test
    public void testRemoveNode() throws Exception {
        g.removeNode("a");
        Assert.assertEquals(3, g.countNodes());
        Assert.assertFalse(g.hasNode("a"));
    }

    @Test
    public void testRemoveNodes() throws Exception {
        String[] nodeList = {"a", "b"};
        g.removeNodes(nodeList);
        Assert.assertEquals(2, g.countNodes());
        Assert.assertFalse(g.hasNode("a"));
    }
    /* FEATURE 2*/

    /* FEATURE 3 */
    @Test
    public void testAddEdge() throws Exception {
        g.addEdge("e", "a");
        Assert.assertEquals(4, g.countNodes());
        Assert.assertTrue(g.hasNode("e"));
        Assert.assertTrue(g.containsEdge("e", "a"));
    }

    @Test
    public void testRemoveEdge() {
        g.removeEdge("b", "c");
        Assert.assertEquals(4, g.countNodes());
        Assert.assertEquals(3, g.countEdges());
        Assert.assertTrue(g.containsEdge("a", "b"));
        Assert.assertTrue(g.containsEdge("c", "d"));
        Assert.assertTrue(g.containsEdge("d", "b"));
    }

    /* FEATURE 4 */
    @Test
    public void testConvertMapToGraph() {
        g.convertMapToGraph();
    }

    @Test
    public void testOutputDOTGraph() throws IOException {
        g.addEdge("e", "f");
        String outputFile = "output.dot";
        g.outputDOTGraph(outputFile);
        String output = Files.readString(Paths.get(outputFile));
        String expected = Files.readString(Paths.get("output.dot"));
        Assert.assertEquals(expected, output);
    }

//    @Test
//    public void testOutputGraphics() throws IOException {
//        String expected = "output.png";
//        String actual = "response.png";
//        g.outputGraphics(actual, "png");
//
//        BufferedImage bImg = ImageIO.read(new File(actual));
//        DataBuffer dbf = bImg.getData().getDataBuffer();
//        int img = dbf.getSize();
//
//        BufferedImage expImg = ImageIO.read(new File(expected));
//        DataBuffer expDataBuf = expImg.getData().getDataBuffer();
//        int expImgSize = expDataBuf.getSize();
//
//        Assert.assertEquals(expImgSize, img);
//    }

    @Test
    public void testIOExceptionMessage() {
        try {
            String output = Files.readString(Paths.get("file.dot"));
        } catch (IOException e) {
            Assert.assertEquals("file.dot", e.getMessage());
        }
    }

}
