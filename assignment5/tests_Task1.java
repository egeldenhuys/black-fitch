/*
Example test suite
*/

public class tests_Task1 extends JavaFitchUnitTest{

	public tests_Task1(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		//result = result & test_template("Read File");
		result = result & test_numEdges("Load graph from file with duplicate edges");

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

		Graph g = new Graph("graph.txt");

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

	public boolean test_numEdges(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Loading graph with duplicate edges");
		// ==========================================================

		Graph g = new Graph("duplicates.txt");

		subResult = subResult & assertEquals(g.numEdges("A", "B"), 2);
		subResult = subResult & assertEquals(g.numEdges("B", "C"), 3);
		subResult = subResult & assertEquals(g.numEdges("C", "B"), 3);
		subResult = subResult & assertEquals(g.numEdges("C", "D"), 1);
		subResult = subResult & assertEquals(g.numEdges("A", "C"), 0);

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
