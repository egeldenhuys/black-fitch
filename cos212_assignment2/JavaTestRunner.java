/*
This class constructs all test suites and runs them
*/

public class JavaTestRunner {

	static int testsCount = 0;
	static int testsPassed = 0;
	static int assertsCount = 0;
	static int assertsPassed = 0;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...");

		boolean result = true;

		UnitTest suite_doubleThreadedBST = new tests_DoubleThreadedBST();
		printHeader("DoubleThreadedBST");
		result = result & suite_doubleThreadedBST.run();

		testsCount += suite_doubleThreadedBST.testsCount;
		testsPassed += suite_doubleThreadedBST.testsPassed;
		assertsCount += suite_doubleThreadedBST.assertsCount;
		assertsPassed += suite_doubleThreadedBST.assertsPassed;


		printFooter("DoubleThreadedBST", suite_doubleThreadedBST.testResult, suite_doubleThreadedBST.testsCount, suite_doubleThreadedBST.testsPassed, suite_doubleThreadedBST.assertsCount, suite_doubleThreadedBST.assertsPassed);
		//printFooter("Stack", suite_stack.testResult, suite_stack.testsCount, suite_stack.testsPassed, suite_stack.assertsCount, suite_stack.assertsPassed);
		//printFooter("Queue", suite_queue.testResult, suite_queue.testsCount, suite_queue.testsPassed, suite_queue.assertsCount, suite_queue.assertsPassed);
		//printFooter("Deque", suite_deque.testResult, suite_deque.testsCount, suite_deque.testsPassed, suite_deque.assertsCount, suite_deque.assertsPassed);

		printFooter("OVERALL", result, testsCount, testsPassed, assertsCount, assertsPassed);
	}

	public static void printHeader(String title) {
		System.out.println("XXXXXXXXX SUITE: " + title + " XXXXXXXXXX");
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
