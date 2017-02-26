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

		result = result & suite_circularList.run();

		// TODO: Feels bad
		testsCount += suite_circularList.testsCount;
		testsPassed += suite_circularList.testsPassed;
		assertsCount += suite_circularList.assertsCount;
		assertsPassed += suite_circularList.assertsPassed;

		printFooter(result);
	}

	public static void printFooter(boolean result) {
		System.out.println("======= SUMMARY =======");
		System.out.println(resultToString(result));
		System.out.println("tests: " + testsCount + " | " + testsPassed + " passed");
		System.out.println("assertions: " + assertsCount + " | " + assertsPassed + " passed");
		System.out.println("=======================");
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
