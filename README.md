# Conversion of a DOT file to a graph object and performing graph manipulations on it


**INTRODUCTION:**

The goal of this project is to convert a DOT file to a graph object and perform graph manipulations on it. I have created a custom graph class, **GraphManager.java** where I have used a Hashmap to represent the graph. 

I used **Graphviz-java** api library to convert DOT file to a graph object. I have used **Main.java** class to run the project.


**FEATURE 1:**

The Main class contains the following code for creating an instance of the GraphManger class and to execute Feature 1 features.

Running the below code in the Main method will execute the GraphManager class for the Feature 1 implementations. I have created an input file **input.dot** that contains a digraph with 4 nodes and 4 edges. For generating an output file, I have specified the file name as **output.txt**. Running outputGraph() method will generate a text file called output.txt in the project folder.
  
        
        GraphManager<String> graph = new GraphManager<>();
        graph.parseGraph("input.dot");
        graph.countNodes();
        graph.getLabel();
        graph.countEdges();
        graph.getEdgeDirection();
        graph.containsEdge("a", "b");
        graph.toString();
        graph.outputGraph("output.txt");
        
        
        
**FEATURE 2:**

Running the below lines of code in the Main class will execute the Feature 2 code implementation like adding and removing node and list of nodes. I have given input for the node list to be added and removed as follows:

        
        String nodeList[] = {"q", "w", "r", "t", "h", "m"};
        String nodeListRemoved[] = {"w", "r"};
        graph.addNode("e");
        graph.addNodes(nodeList);
        graph.removeNode("e");
        graph.removeNodes(nodeListRemoved);
        


**FEATURE 3:**

Running the below lines of code in the Main class will execute the Feature 3 code implementation like adding and removing edges from the graph. I have given input for the edges to be added and removed as follows:


        graph.addEdge("q", "t");
        graph.removeEdge("q", "t");



**FEATURE 4:**

Running the below lines of code in the Main class wil lexecute the Feature 4 code implementation like converting the graph object to DOT file and then to PNG format.

        graph.outputDOTGraph("output.dot");
        graph.outputGraphics("response.png", "png");
        
     
     
**CONCLUSION:**
     
Therefore, **running the Main.java class** will call the methods implemented in GraphManager.java class will execute the features specified in the assignment instructions. The **GraphManagerTest.java** class contains the unit test cases for each feature.
