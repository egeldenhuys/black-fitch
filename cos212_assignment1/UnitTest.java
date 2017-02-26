/*
	Class that defines the base functions for unit tests
	Such as the layout
*/
public class UnitTest {

	public int testsPassed;
	public int testsCount;
	public int assertsPassed;
	public int assertsCount;
	public boolean testResult;

	public boolean run() {
		System.out.println("UnitTest::run() not implemented!");

		return false;
	}

	public void printHeader(String title) {
		System.out.println("\t=========================");
		System.out.println("\t" + title);
		System.out.println("\t=========================");

	}

	public void printFooter(String title, boolean result) {
		testResult = result;

		testsCount++;

		if (result == true) {
			testsPassed++;
		}

		System.out.println("\t=========================");
		System.out.print("\t" + title + ": ");
		System.out.println(resultToString(result));
		System.out.println("\tassertions: " + assertsCount + " | " + assertsPassed + " passed");
		System.out.println("\t=========================");
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

	public <T> boolean assertCustom(T received, T expected) {

		assertsCount++;

		if (received.equals(expected)) {
			assertsPassed++;

			return true;
		} else {
			System.out.println();
			System.out.println("++++ ASSERT FAIL ++++");
			System.out.println("Recevied: \n" + received.toString() + "\n");
			System.out.println("Expected: \n" + expected.toString());
			System.out.println("++++++++++++++++++++++");

			return false;
		}
	}
}
