/*
Example test suite
*/

public class tests_white_Task1 extends JavaFitchUnitTest{

	public tests_white_Task1(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		result = result & test_reconstructGraph("Reconstruct Graph");

		return result;
	}


	public boolean test_reconstructGraph(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Constructing initial graph from graph.txt ( Graph() )");
		// ==========================================================

		Graph g = new Graph("graph.txt");

		subResult = subResult & assertEquals("  A B C D \nA 0 2 1 4 \nB 2 0 7 3 \nC 1 7 0 5 \nD 4 3 5 0 \n", GraphUtils.matrixToString(g.vertices, g.matrix));

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Reconstructing graph from g1.txt ( reconstructGraph() )");
		// ==========================================================
		boolean s = false;

		s = g.reconstructGraph("g1.txt");

		subResult = subResult & assertEquals("  A Z B R E CAT \nA 0 5 3 0 0 2 \nZ 5 0 0 0 0 0 \nB 3 0 0 1 9 0 \nR 0 0 1 0 4 0 \nE 0 0 9 4 0 0 \nCAT 2 0 0 0 0 0 \n", GraphUtils.matrixToString(g.vertices, g.matrix));
		subResult = subResult & assertEquals(true, s);
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
