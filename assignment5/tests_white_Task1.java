/*
Example test suite
*/

public class tests_white_Task1 extends JavaFitchUnitTest{

	public tests_white_Task1(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		result = result & test_template("Read File");

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


}
