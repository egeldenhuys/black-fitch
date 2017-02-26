/*
This class constructs all test suites and runs them
*/

public class JavaTestRunner {

	static int testsCount = 0;
	static int testsPassed = 0;
	static int assertsCount = 0;
	static int assertsPassed = 0;
	static boolean result = true;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...");

		UnitTest suite_circularList = new tests_CircularList();
		printHeader("CircularList");
		result = result & suite_circularList.run();

		testsCount += suite_circularList.testsCount;
		testsPassed += suite_circularList.testsPassed;
		assertsCount += suite_circularList.assertsCount;
		assertsPassed += suite_circularList.assertsPassed;

		UnitTest suite_stack = new tests_Stack();
		printHeader("Stack");
		result = result & suite_stack.run();

		testsCount += suite_stack.testsCount;
		testsPassed += suite_stack.testsPassed;
		assertsCount += suite_stack.assertsCount;
		assertsPassed += suite_stack.assertsPassed;

		printFooter("CircularList", result, suite_circularList.testsCount, suite_circularList.testsPassed, suite_circularList.assertsCount, suite_circularList.assertsPassed);
		printFooter("Stack", result, suite_stack.testsCount, suite_stack.testsPassed, suite_stack.assertsCount, suite_stack.assertsPassed);
		printFooter("OVERALL", result, testsCount, testsPassed, assertsCount, assertsPassed);
	}

	public static void printHeader(String title) {
		System.out.println("_________ SUITE: " + title + " _________");
	}

	public static void printFooter(String title, boolean result, int testsCount, int testsPassed, int assertsCount, int assertsPassed) {
		System.out.println("________ SUMMARY for " + title + " _________");
		System.out.println(resultToString(result));
		System.out.println("tests: " + testsCount + " | " + testsPassed + " passed");
		System.out.println("assertions: " + assertsCount + " | " + assertsPassed + " passed");
		System.out.println("_____________________________________________");
	}

	public static String resultToString(boolean value) {

		String result = "";

		if (value == true) {
			result = "PASS";
		} else {
			result = "FAIL";
		}

		return result;
	}
}
