public class tests_Task2 extends JavaFitchUnitTest{

	public static final String GRAPH_DIR = "black-fitch_graphs";
	public static final String GRAPH_FILE_DIR = "black-fitch_graph_files";

	public static final String FILE_LARGE = GRAPH_FILE_DIR + "/large.txt";
	public static final String FILE_HEXAGON = GRAPH_FILE_DIR + "/hexagon.txt";
	public static final String FILE_GRAPH = GRAPH_FILE_DIR + "/graph.txt";
	public static final String FILE_HOUSE = GRAPH_FILE_DIR + "/house.txt";
	public static final String FILE_CIRCULAR = GRAPH_FILE_DIR + "/circular.txt";

	public tests_Task2(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		result = result & test_getOdd("Testing getOdd()");
		result = result & test_getPath("Testing getPath()");

		return result;
	}


	public boolean test_getOdd(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("getOdd() should return a sorted string off odd vertices");
		// ==========================================================

		Graph g = new Graph(FILE_GRAPH);
		String odd = g.getOdd();

		subResult = subResult & assertEquals("A,B,C,D", odd);

		g = new Graph(FILE_HOUSE);
		odd = g.getOdd();

		subResult = subResult & assertEquals("B,D", odd);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("When no odd vertices, return an empty string");
		// ==========================================================

		g = new Graph(FILE_CIRCULAR);
		odd = g.getOdd();

		subResult = subResult & assertEquals("", odd);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_getPath(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph.txt. getPath() should return the shortest path");
		// ==========================================================

		Graph g = new Graph(FILE_GRAPH);
		String path = g.getPath("A", "B");

		subResult = subResult & assertEquals("A,B", path);

		path = g.getPath("B", "C");
		subResult = subResult & assertEquals("B,A,C", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading LARGE.txt. getPath() should return the shortest path");
		// ==========================================================

		g = new Graph(FILE_LARGE);
		path = g.getPath("I", "V");

		subResult = subResult & assertEquals("I,L,K,J,X,D,T,V", path);

		path = g.getPath("Y", "R");
		subResult = subResult & assertEquals("Y,E,F,D,T,V,Q,C,R", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading hexagon.txt. getPath() should return the shortest path");
		// ==========================================================

		g = new Graph(FILE_HEXAGON);
		path = g.getPath("A", "E");

		subResult = subResult & assertEquals("A,G,E", path);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading circular.txt. getPath() should return the shortest path");
		// ==========================================================

		g = new Graph(FILE_CIRCULAR);
		path = g.getPath("D", "E");

		subResult = subResult & assertEquals("D,B,C,E", path);

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
