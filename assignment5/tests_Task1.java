/*
Example test suite
*/

public class tests_Task1 extends JavaFitchUnitTest{

	public static final String GRAPH_DIR = "black-fitch_graphs";
	public static final String GRAPH_FILE_DIR = "black-fitch_graph_files";

	public tests_Task1(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		//result = result & test_template("Read File");
		result = result & test_draw_graphs("Drawing graphs");
		result = result & test_numEdges("numEdges()");
		result = result & test_getDegree("getDegree()");
		result = result & test_changeLabel("changeLabel()");
		result = result & test_depthFristTraversal("depthFirstTraversal()");

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
		subResult = start_test_case("Testing...");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");

		//int trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		//subResult = subResult & assertEquals("123", "-1", trace);

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

		Graph g = new Graph(GRAPH_FILE_DIR + "/graph.txt");
		GraphUtils.graphToImage(g,  GRAPH_FILE_DIR + "/graph.txt", GRAPH_DIR + "/graph_received.png");
		GraphUtils.graphFileToImage(GRAPH_FILE_DIR + "/graph.txt", GRAPH_DIR + "/graph_expected.png");

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
		subResult = start_test_case("Loading dulicates.txt. Testing numEdges()");
		// ==========================================================


		Graph g = new Graph(GRAPH_FILE_DIR + "/duplicates.txt");

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
		subResult = start_test_case("Loading tree.txt. Testing changeLabel()");
		// ==========================================================

		Graph g = new Graph(GRAPH_FILE_DIR + "/tree.txt");

		boolean r = g.changeLabel("ROOT", "Bob");

		GraphUtils.graphToImage(g, GRAPH_FILE_DIR + "/tree.txt", GRAPH_DIR + "/tree_changeLabel_receieved.png", new String[]{"ROOT"}, new String[]{"Bob"});
		subResult = subResult & assertEquals(r, true);


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

		subResult = subResult & assertEquals("A,B,C,A,D,B,C", path);

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
