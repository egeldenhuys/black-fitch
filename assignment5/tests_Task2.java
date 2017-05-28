public class tests_Task2 extends JavaFitchUnitTest{

	public static final String GRAPH_DIR = "black-fitch_graphs";
	public static final String GRAPH_FILE_DIR = "black-fitch_graph_files";

	public static final String FILE_GRAPH = GRAPH_FILE_DIR + "/graph.txt";
	public static final String FILE_HOUSE = GRAPH_FILE_DIR + "/house.txt";
	public static final String FILE_CIRCULAR = GRAPH_FILE_DIR + "/circular.txt";

	public tests_Task2(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		result = result & test_getOdd("Testing getOdd()");

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


}
