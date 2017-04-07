/*
	Class that defines the base functions for unit tests
	Such as the layout
*/
public class UnitTest {

	public int testsPassed;
	public int testsCount;
	public int assertsPassed;
	public int assertsCount;
	public int assertsPassedSubTest;
	public int assertsCountSubTest;
	public boolean testResult = true;

	public boolean run() {
		System.out.println("UnitTest::run() not implemented!");

		return false;
	}

	public void printHeader(String title) {
		assertsCountSubTest = 0;
		assertsPassedSubTest = 0;

		System.out.println("\t=========================");
		System.out.println("\t" + title);
		System.out.println("\t=========================");

	}

	public boolean start_test_case(String title) {
		printSubTest(title);
		return true;
	}

	public boolean end_test_case(boolean result, boolean subResult) {
		printSubTestFooter(subResult);
		return result & subResult;
	}

	public void printFooter(String title, boolean result) {
		testResult = testResult & result;

		testsCount++;

		if (result == true) {
			testsPassed++;
		}

		System.out.println("\t_____ SUMMARY: " + title + " _____");
		//System.out.print("\t" + title + " Summary: ");
		System.out.println("\t" + resultToString(result));
		System.out.println("\tassertions: " + assertsPassedSubTest + " passed | " + assertsCountSubTest);
		System.out.println("\t_________________________");
	}

	public void printSubTest(String title) {

		System.out.println("\t\t-----------------------");
		System.out.print("\t\t" + title + ": ");
	}

	public void printSubTestFooter(boolean value) {
		if (value != false) {
			System.out.println(resultToString(value));
			System.out.println("\t\t-----------------------");
		} else {
			System.out.println("\n\t\t-----------------------");
		}

	}

	public String resultToString(boolean value) {

		String result = "";

		if (value == true) {
			result = "PASS";
		} else {
			result = "FAIL";
		}

		return result;
	}

	public <T> boolean assertEquals(T expected, T received, int tracer) {
		tracer++;

		assertsCount++;
		assertsCountSubTest++;

		if (received.equals(expected)) {
			assertsPassed++;
			assertsPassedSubTest++;
			return true;
		} else {
			System.out.println();
			System.out.println("++++ ASSERT FAIL on line " + tracer + " ++++");
			System.out.println("Expected: \n" + expected.toString() + "\n");
			System.out.println("Received: \n" + received.toString() + "\n");
			System.out.println("++++++++++++++++++++++");

			return false;
		}
	}

	public <T> boolean assertEquals(T expected, T received) {

		assertsCount++;
		assertsCountSubTest++;

		if (received.equals(expected)) {
			assertsPassed++;
			assertsPassedSubTest++;
			return true;
		} else {
			System.out.println();
			System.out.println("++++ ASSERT FAIL ++++");
			System.out.println("Expected: \n" + expected.toString());
			System.out.println("Received: \n" + received.toString() + "\n");
			System.out.println("++++++++++++++++++++++");

			return false;
		}
	}

	public <T> boolean assertNull(T received, int tracer) {
		tracer++;
		assertsCount++;
		assertsCountSubTest++;

		if (received == null) {
			assertsPassed++;
			assertsPassedSubTest++;
			return true;
		} else {
			System.out.println();
			System.out.println("++++ ASSERT FAIL on line " + tracer + " ++++");
			System.out.println("Expected: \n" + "null");
			System.out.println("Received: \n" + received.toString() + "\n");
			System.out.println("++++++++++++++++++++++");

			return false;
		}
	}

	public <T> boolean assertNull(T received) {

		assertsCount++;
		assertsCountSubTest++;

		if (received == null) {
			assertsPassed++;
			assertsPassedSubTest++;
			return true;
		} else {
			System.out.println();
			System.out.println("++++ ASSERT FAIL ++++");
			System.out.println("Expected: \n" + "null");
			System.out.println("Received: \n" + received.toString() + "\n");
			System.out.println("++++++++++++++++++++++");

			return false;
		}
	}
}
