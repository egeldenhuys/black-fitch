/*
Example test suite
*/

public class tests_Task1 extends JavaFitchUnitTest{

	public tests_Task1(String n) {
		super(n);
	}

	public boolean run() {

		boolean result = true;
		result = result & test_template("Case: Single Node");

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
		subResult = start_test_case("Creating a new BPlusTree with M = 5");
		// ==========================================================

		subResult = subResult & assertEquals("123", "-1");

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
