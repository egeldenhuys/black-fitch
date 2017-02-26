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

		UnitTest suite_queue = new tests_Queue();
		printHeader("Queue");
		result = result & suite_queue.run();

		testsCount += suite_queue.testsCount;
		testsPassed += suite_queue.testsPassed;
		assertsCount += suite_queue.assertsCount;
		assertsPassed += suite_queue.assertsPassed;

		UnitTest suite_deque = new tests_Deque();
		printHeader("Deque");
		result = result & suite_deque.run();

		testsCount += suite_deque.testsCount;
		testsPassed += suite_deque.testsPassed;
		assertsCount += suite_deque.assertsCount;
		assertsPassed += suite_deque.assertsPassed;

		printFooter("CircularList", suite_circularList.testResult, suite_circularList.testsCount, suite_circularList.testsPassed, suite_circularList.assertsCount, suite_circularList.assertsPassed);
		printFooter("Stack", suite_stack.testResult, suite_stack.testsCount, suite_stack.testsPassed, suite_stack.assertsCount, suite_stack.assertsPassed);
		printFooter("Queue", suite_queue.testResult, suite_queue.testsCount, suite_queue.testsPassed, suite_queue.assertsCount, suite_queue.assertsPassed);
		printFooter("Deque", suite_deque.testResult, suite_deque.testsCount, suite_deque.testsPassed, suite_deque.assertsCount, suite_deque.assertsPassed);

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
