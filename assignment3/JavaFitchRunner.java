/*
This class constructs all test suites and runs them
*/

public class JavaFitchRunner {

	static int testsCount = 0;
	static int testsPassed = 0;
	static int assertsCount = 0;
	static int assertsPassed = 0;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...");

		boolean result = true;

		UnitTest suite_Task1 = new BPlusTreeTest();
		printHeader("Task 1");
		result = result & suite_Task1.run();

		testsCount += suite_Task1.testsCount;
		testsPassed += suite_Task1.testsPassed;
		assertsCount += suite_Task1.assertsCount;
		assertsPassed += suite_Task1.assertsPassed;

		printFooter("Testing Template", suite_Task1.testResult, suite_Task1.testsPassed, suite_Task1.testsCount, suite_Task1.assertsPassed, suite_Task1.assertsCount);
		printFooter("OVERALL", result, testsPassed, testsCount, assertsPassed, assertsPassed);
	}

	public static void printHeader(String title) {
		System.out.println("XXXXXXXXX SUITE: " + title + " XXXXXXXXXX");
	}

	public static void printFooter(String title, boolean result, int testsCount, int testsPassed, int assertsCount, int assertsPassed) {
		System.out.println("________ SUMMARY for " + title + " _________");
		System.out.println(resultToString(result));
		System.out.println("tests: " + testsPassed + " passed | " + testsCount);
		System.out.println("assertions: " + assertsPassed + " passed | " + assertsCount);
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
