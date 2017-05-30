public class tests_Task1 extends JavaFitchUnitTest{

	public static final String GRAPH_DIR = "black-fitch_graphs";
	public static final String GRAPH_FILE_DIR = "black-fitch_graph_files";

	public tests_Task1(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		result = result & test_draw_graphs("Drawing graphs");
		result = result & test_numEdges("numEdges()");
		result = result & test_getDegree("getDegree()");
		result = result & test_depthFristTraversal("depthFirstTraversal()");
		result = result & test_clone("clone()");
		result = result & test_reconstructGraph("reconstructGraph()");
		result = result & test_changeLabel("changeLabel()");

		return result;
	}


	public boolean test_template(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Template");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		//subResult = subResult & assertEquals("123", "-1");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}


	public boolean test_draw_graphs(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing graph.txt...");
		// ==========================================================

		Graph g = new Graph("graph.txt");
		GraphUtils.graphToImage(g,  "graph.txt", GRAPH_DIR + "/graph_received.png");
		GraphUtils.graphFileToImage("graph.txt", GRAPH_DIR + "/graph_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing duplicates.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/duplicates.txt");
		GraphUtils.graphToImage(g, GRAPH_FILE_DIR + "/duplicates.txt", GRAPH_DIR + "/duplicates_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/duplicates.txt", GRAPH_DIR + "/duplicates_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing tree.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/tree.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/tree.txt", GRAPH_DIR + "/tree_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/tree.txt", GRAPH_DIR + "/tree_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing house.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/house.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/house.txt", GRAPH_DIR + "/house_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/house.txt", GRAPH_DIR + "/house_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing doubleHouse.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/doubleHouse.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/doubleHouse.txt", GRAPH_DIR + "/doubleHouse_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/doubleHouse.txt", GRAPH_DIR + "/doubleHouse_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing hexagon.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/hexagon.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/hexagon.txt", GRAPH_DIR + "/hexagon_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/hexagon.txt", GRAPH_DIR + "/hexagon_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing circular.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/circular.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/circular.txt", GRAPH_DIR + "/circular_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/circular.txt", GRAPH_DIR + "/circular_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Drawing large.txt...");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/large.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/large.txt", GRAPH_DIR + "/large_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/large.txt", GRAPH_DIR + "/large_expected.png");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_clone(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Create new graph from graph.txt. clone(), changeLabel()");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");
		Graph g2 = new Graph(GRAPH_FILE_DIR + "/tree.txt");

		g2 = g.clone();
		g.changeLabel("A", "THE ORIGINAL LABEL");

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Testing with DFS()");
		// ==========================================================

		// graph.txt
		String path = g2.depthFirstTraversal("A");
		subResult = subResult & assertEquals("A,B,C,D", path);

		g = null;

		path = g2.depthFirstTraversal("A");
		subResult = subResult & assertEquals("A,B,C,D", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_reconstructGraph(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		String path = g.depthFirstTraversal("A");

		subResult = subResult & assertEquals("A,B,C,D", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("reconstructGraph with hexagon.txt");
		// ==========================================================

		boolean r = g.reconstructGraph(GRAPH_FILE_DIR + "/hexagon.txt");

		path = g.depthFirstTraversal("A");

		subResult = subResult & assertEquals("A,B,C,D,E,F,G", path);
		subResult = subResult & assertEquals(true, r);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("reconstructGraph with invalid graph");
		// ==========================================================

		r = g.reconstructGraph(GRAPH_FILE_DIR + "/invalid.txt");
		subResult = subResult & assertEquals(false, r);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_numEdges(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing numEdges()");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		subResult = subResult & assertEquals(1, g.numEdges("A", "B"));
		subResult = subResult & assertEquals(1, g.numEdges("B", "C"));
		subResult = subResult & assertEquals(1, g.numEdges("C", "B"));
		subResult = subResult & assertEquals(1, g.numEdges("C", "D"));
		subResult = subResult & assertEquals(1, g.numEdges("A", "C"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing numEdges() with invalid edge");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/house.txt");
		subResult = subResult & assertEquals(0, g.numEdges("Z", "Y"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading dulicates.txt. Testing numEdges()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/duplicates.txt");

		subResult = subResult & assertEquals(2, g.numEdges("A", "B"));
		subResult = subResult & assertEquals(3, g.numEdges("B", "C"));
		subResult = subResult & assertEquals(3, g.numEdges("C", "B"));
		subResult = subResult & assertEquals(1, g.numEdges("C", "D"));
		subResult = subResult & assertEquals(0, g.numEdges("A", "C"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_getDegree(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing getDegree()");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		subResult = subResult & assertEquals(3, g.getDegree("A"));
		subResult = subResult & assertEquals(3, g.getDegree("B"));
		subResult = subResult & assertEquals(3, g.getDegree("C"));
		subResult = subResult & assertEquals(3, g.getDegree("D"));
		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing getDegree() with invalid vertex");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		subResult = subResult & assertEquals(0, g.getDegree("Z"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading tree.txt. Testing getDegree()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/tree.txt");

		subResult = subResult & assertEquals(2, g.getDegree("ROOT"));
		subResult = subResult & assertEquals(3, g.getDegree("B"));
		subResult = subResult & assertEquals(1, g.getDegree("D"));
		subResult = subResult & assertEquals(1, g.getDegree("G"));
		subResult = subResult & assertEquals(3, g.getDegree("C"));
		subResult = subResult & assertEquals(1, g.getDegree("F"));
		subResult = subResult & assertEquals(1, g.getDegree("E"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading house.txt. Testing getDegree()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/house.txt");

		subResult = subResult & assertEquals(2, g.getDegree("C"));
		subResult = subResult & assertEquals(2, g.getDegree("E"));
		subResult = subResult & assertEquals(3, g.getDegree("B"));
		subResult = subResult & assertEquals(3, g.getDegree("D"));
		subResult = subResult & assertEquals(2, g.getDegree("A"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading doubleHouse.txt. Testing getDegree()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/doubleHouse.txt");

		subResult = subResult & assertEquals(2, g.getDegree("C"));
		subResult = subResult & assertEquals(2, g.getDegree("E"));
		subResult = subResult & assertEquals(3, g.getDegree("B"));
		subResult = subResult & assertEquals(4, g.getDegree("D"));
		subResult = subResult & assertEquals(3, g.getDegree("A"));
		subResult = subResult & assertEquals(2, g.getDegree("F"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading duplicates.txt. Testing getDegree()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/duplicates.txt");

		subResult = subResult & assertEquals(1, g.getDegree("D"));
		subResult = subResult & assertEquals(4, g.getDegree("C"));
		subResult = subResult & assertEquals(5, g.getDegree("B"));
		subResult = subResult & assertEquals(2, g.getDegree("A"));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading hexagon.txt. Testing getDegree()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/hexagon.txt");

		subResult = subResult & assertEquals(6, g.getDegree("G"));
		subResult = subResult & assertEquals(4, g.getDegree("D"));
		subResult = subResult & assertEquals(3, g.getDegree("F"));
		subResult = subResult & assertEquals(3, g.getDegree("E"));
		subResult = subResult & assertEquals(4, g.getDegree("A"));
		subResult = subResult & assertEquals(3, g.getDegree("B"));
		subResult = subResult & assertEquals(3, g.getDegree("C"));


		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_changeLabel(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing changeLabel()");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		boolean r = g.changeLabel("A", "JOHN");

		//GraphUtils.graphToImage(g, GRAPH_FILE_DIR + "/tree.txt", GRAPH_DIR + "/tree_changeLabel_receieved.png", new String[]{"ROOT"}, new String[]{"Bob"});

		subResult = subResult & assertEquals(true, r);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Testing depthFirstTraversal() after label change");
		// ==========================================================

		// graph.txt
		String path = g.depthFirstTraversal("C");
		subResult = subResult & assertEquals("C,B,D,JOHN", path);

		path = g.depthFirstTraversal("JOHN");

		subResult = subResult & assertEquals("JOHN,B,C,D", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading house.txt. Testing changeLabel() with invalid label");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/house.txt");

		r = g.changeLabel("Zebra", "Lolwat");

		subResult = subResult & assertEquals(false, r);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_depthFristTraversal(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing depthFirstTraversal()");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");
		String path = g.depthFirstTraversal("A");
		subResult = subResult & assertEquals("A,B,C,D", path);

		path = g.depthFirstTraversal("B");
		subResult = subResult & assertEquals("B,A,C,D", path);

		path = g.depthFirstTraversal("C");
		subResult = subResult & assertEquals("C,A,B,D", path);

		path = g.depthFirstTraversal("D");
		subResult = subResult & assertEquals("D,A,B,C", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. Testing depthFirstTraversal() with invalid node");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/graph.txt");
		path = g.depthFirstTraversal("Z");

		subResult = subResult & assertEquals("", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading duplicates.txt. Testing depthFirstTraversal()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/duplicates.txt");

		path = g.depthFirstTraversal("A");
		subResult = subResult & assertEquals("A,B,C,D", path);

		path = g.depthFirstTraversal("C");
		subResult = subResult & assertEquals("C,B,A,D", path);

		path = g.depthFirstTraversal("D");
		subResult = subResult & assertEquals("D,C,B,A", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading house.txt. Testing depthFirstTraversal()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/house.txt");
		path = g.depthFirstTraversal("A");

		subResult = subResult & assertEquals("A,B,D,C,E", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading doubleHouse.txt. Testing depthFirstTraversal()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/doubleHouse.txt");
		path = g.depthFirstTraversal("D");

		subResult = subResult & assertEquals("D,A,B,E,C,F", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading hexagon.txt. Testing depthFirstTraversal()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/hexagon.txt");
		path = g.depthFirstTraversal("G");
		subResult = subResult & assertEquals("G,A,B,C,D,E,F", path);

		path = g.depthFirstTraversal("F");
		subResult = subResult & assertEquals("F,A,B,C,D,E,G", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading circular.txt. Testing depthFirstTraversal()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/circular.txt");

		path = g.depthFirstTraversal("B");
		subResult = subResult & assertEquals("B,C,E,A,D", path);

		path = g.depthFirstTraversal("E");
		subResult = subResult & assertEquals("E,A,D,B,C", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading large.txt. Testing depthFirstTraversal()");
		// ==========================================================

		g = new Graph(GRAPH_FILE_DIR + "/large.txt");

		path = g.depthFirstTraversal("X");
		subResult = subResult & assertEquals("X,C,Q,V,T,D,A,F,E,Y,G,H,M,J,K,L,I,N,P,W,U,R,S,B", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}


}
