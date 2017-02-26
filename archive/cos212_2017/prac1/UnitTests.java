import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class UnitTests {

	static Integer testCount = 1;
	static Integer testPassCount = 0;
	static Integer assertions = 0;
	static Integer assertionsPassed = 0;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...\n");

		//test_template("test_template");
		test_failure("test_failure");

		test_add_to_head("test_add_to_head");
		test_add_to_tail("test_add_to_tail");
		test_add_to_head_and_tail_mixed("test_add_to_head_and_tail_mixed");
		test_remove_head("test_remove_head");
		test_remove_tail("test_remove_tail");
		test_get_position("test_get_position");
		test_remove("test_remove");
		test_is_empty("test_is_empty");
		test_get_count("test_get_count");
		test_access("test_access");

		System.out.println("");
		testCount--;
		System.out.println("Test casses passed:" + testPassCount.toString() + "/" + testCount.toString());
		System.out.println("Assertions passed:" + assertionsPassed.toString() + "/" + assertions.toString());
	}

	// ==============
	// TEST CASES
	// ==============

	public static boolean test_failure(String name) {
		printHeader(name);
		boolean result = true;

		result = result && !assertCustom(10, 11, true);
		result = result && !assertCustom("ABC", "NAR", true);
		result = result && !assertCustom(true, false, true);

		result = result && assertCustom(1, 1);
		result = result && assertCustom("GET", "GET");
		result = result && assertCustom(true, true);
		result = result && assertCustom(false, false);

		printFooter(name, result);
		return result;
	}

	public static boolean test_template(String name) {
		printHeader(name);
		boolean result = true;

		String expected = "";
		String received = "";

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);

		expected = " H = (1) \n T = (2) \n (1, next = 2, prev = null) \n (2, next = null, prev = 1) \n";
		received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);


		printFooter(name, result);
		return result;
	}

	public static boolean test_add_to_tail(String name) {
		printHeader(name);
		boolean result = true;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);

		String expected = " H = (1) \n T = (4) \n (1, next = 2, prev = null) \n (2, next = 3, prev = 1) \n (3, next = 4, prev = 2) \n (4, next = null, prev = 3) \n";
		String received = captureListPrint(list, true);

		result = result && assertCustom(received, expected);

		printFooter(name, result);

		return result;

	}

	public static boolean test_add_to_head(String name) {
		printHeader(name);
		boolean result = true;

		DLList<Integer> list = new DLList<Integer>();

		list.addToHead(1);
		list.addToHead(2);
		list.addToHead(3);
		list.addToHead(4);

		String expected = " H = (4) \n T = (1) \n (4, next = 3, prev = null) \n (3, next = 2, prev = 4) \n (2, next = 1, prev = 3) \n (1, next = null, prev = 2) \n";
		String received = captureListPrint(list, true);

		result = result && assertCustom(received, expected);

		printFooter(name, result);
		return result;
	}

	public static boolean test_add_to_head_and_tail_mixed(String name) {
		printHeader(name);
		boolean result = true;


		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1); // 1
		list.addToHead(2); // 2 1
		list.addToHead(3); // 3 2 1
		list.addToTail(4); // 3 2 1 4

		String expected = " H = (3) \n T = (4) \n (3, next = 2, prev = null) \n (2, next = 1, prev = 3) \n (1, next = 4, prev = 2) \n (4, next = null, prev = 1) \n";
		String received = captureListPrint(list, true);

		result = result && assertCustom(received, expected);

		printFooter(name, result);

		return result;

	}

	public static boolean test_remove_head(String name) {
		printHeader(name);
		boolean result = true;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);

		printSubTest("Remove head when not null...");
		list.removeHead(); // 2 3 4
		list.removeHead(); // 3 4


		String expected = " H = (3) \n T = (4) \n (3, next = 4, prev = null) \n (4, next = null, prev = 3) \n";
		String received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove head when only node...");
		list.removeHead(); // 4
		list.removeHead(); // null


		String expected2 = " H = null\n T = null\n\n";
		String received2 = captureListPrint(list, true);
		result = result && assertCustom(received2, expected2);

		printSubTest("Remove head when empty...");
		boolean pass = false;

		try {
			list.removeHead();
		} catch (EmptyListException e) {
			pass = true;
		}

		result = result && pass;
		printFooter(name, result);

		return result;
	}

	public static boolean test_remove_tail(String name) {
		printHeader(name);
		boolean result = true;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);

		printSubTest("Remove tail when not null...");
		list.removeTail(); // 1 2 3
		list.removeTail(); // 1 2


		String expected = " H = (1) \n T = (2) \n (1, next = 2, prev = null) \n (2, next = null, prev = 1) \n";
		String received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove tail when only node...");
		list.removeTail(); // 4
		list.removeTail(); // null


		String expected2 = " H = null\n T = null\n\n";
		String received2 = captureListPrint(list, true);
		result = result && assertCustom(received2, expected2);

		printSubTest("Remove tail when empty...");
		boolean pass = false;

		try {
			list.removeTail();
		} catch (EmptyListException e) {
			pass = true;
		}

		result = result && pass;
		printFooter(name, result);

		return result;
	}

	public static boolean test_get_position(String name) {
		printHeader(name);
		boolean result = true;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);

		printSubTest("get position of value at head...");
		result = result && assertCustom(list.getPosition(1), 0);

		printSubTest("get position of value at tail...");
		result = result && assertCustom(list.getPosition(4), 3);

		printSubTest("get position of value at middle...");
		result = result && assertCustom(list.getPosition(2), 1);

		printSubTest("get position of invalid value...");
		result = result && assertCustom(list.getPosition(69), -1);

		printFooter(name, result);
		return result;
	}

	public static boolean test_remove(String name) {
		printHeader(name);
		boolean result = true;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1);
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);
		list.addToTail(5);

		printSubTest("Remove invalid element...");

		list.remove(69); // 1 2 3 4 5

		String expected = " H = (1) \n T = (5) \n (1, next = 2, prev = null) \n (2, next = 3, prev = 1) \n (3, next = 4, prev = 2) \n (4, next = 5, prev = 3) \n (5, next = null, prev = 4) \n";
		String received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove at head...");

		list.remove(1); // 2 3 4 5

		expected = " H = (2) \n T = (5) \n (2, next = 3, prev = null) \n (3, next = 4, prev = 2) \n (4, next = 5, prev = 3) \n (5, next = null, prev = 4) \n";
		received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove at tail...");

		list.remove(5); // 2 3 4
		expected = " H = (2) \n T = (4) \n (2, next = 3, prev = null) \n (3, next = 4, prev = 2) \n (4, next = null, prev = 3) \n";
		received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove at middle...");

		list.remove(3); // 2 4
		expected = " H = (2) \n T = (4) \n (2, next = 4, prev = null) \n (4, next = null, prev = 2) \n";
		received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove last element...");
		list.remove(4);
		list.remove(2);

		expected = " H = null\n T = null\n\n";
		received = captureListPrint(list, true);
		result = result && assertCustom(received, expected);

		printSubTest("Remove when empty...");

		boolean except = false;
		try {
			list.remove(70);
		} catch (EmptyListException e) {
			except = true;
		}
		result = result && except;

		printFooter(name, result);
		return result;
	}

	public static boolean test_is_empty(String name) {
		printHeader(name);
		boolean result = true;

		boolean expected;
		boolean received;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1); // 1

		printSubTest("Empty with one element");
		expected = false;
		received = list.isEmpty();
		result = result && assertCustom(received, expected);

		list.removeHead(); // null

		printSubTest("empty with no elements");
		expected = true;
		received = list.isEmpty();
		result = result && assertCustom(received, expected);

		printFooter(name, result);
		return result;
	}

	public static boolean test_get_count(String name) {
		printHeader(name);
		boolean result = true;

		Integer expected;
		Integer received;

		DLList<Integer> list = new DLList<Integer>();

		list.addToTail(1); // 1
		list.addToTail(2);
		list.addToTail(3);
		list.addToTail(4);


		expected = 4;
		received = list.getCount();
		result = result && assertCustom(received, expected);

		list.removeHead();
		list.removeHead();
		list.removeHead();
		expected = 1;
		received = list.getCount();
		result = result && assertCustom(received, expected);

		list.removeHead();
		expected = 0;
		received = list.getCount();
		result = result && assertCustom(received, expected);

		printFooter(name, result);
		return result;
	}

	public static boolean test_access(String name) {
		printHeader(name);
		boolean result = true;
		String expected;
		String received;


		SOList<Integer> soList = new SOList<Integer>();

		soList.addToTail(1);
		soList.addToTail(2);
		soList.addToTail(3);
		soList.addToTail(4);

		printSubTest("when accessed first element is accessed, nothing happens");
		soList.access(1); // 1 2 3 4
		expected = " H = (1) \n T = (4) \n (1, next = 2, prev = null) \n (2, next = 3, prev = 1) \n (3, next = 4, prev = 2) \n (4, next = null, prev = 3) \n";
		received = captureListPrint(soList, true);
		result = result && assertCustom(received, expected);

		printSubTest("when element is accessed, element is moved to front");
		soList.access(3); // 3 1 2 4
		expected = " H = (3) \n T = (4) \n (3, next = 1, prev = null) \n (1, next = 2, prev = 3) \n (2, next = 4, prev = 1) \n (4, next = null, prev = 2) \n";
		received = captureListPrint(soList, true);
		result = result && assertCustom(received, expected);

		printSubTest("if element not found, add it to tail");
		soList.access(69); // 3 1 2 4 69
		expected = " H = (3) \n T = (69) \n (3, next = 1, prev = null) \n (1, next = 2, prev = 3) \n (2, next = 4, prev = 1) \n (4, next = 69, prev = 2) \n (69, next = null, prev = 4) \n";
		received = captureListPrint(soList, true);
		result = result && assertCustom(received, expected);

		printSubTest("if list is empty, add element to tail");
		soList.removeHead();
		soList.removeTail();
		soList.removeHead();
		soList.removeTail();
		soList.removeHead(); // null

		soList.access(55); // 55
		expected = " H = (55) \n T = (55) \n (55, next = null, prev = null) \n";
		received = captureListPrint(soList, true);
		result = result && assertCustom(received, expected);

		printFooter(name, result);
		return result;
	}

	// ==============
	// UTILITIES
	// ==============

	public static void printSubTest(String name) {
		System.out.println("    " + name + "...");
	}

	public static void printHeader(String name) {
		System.out.println("==== " + name + " ====");
	}

	public static void printFooter(String name, boolean result) {
		System.out.println(resultToString(result));
		System.out.println("------- \n");
	}

	public static String resultToString(boolean value) {

		String result = "";
		testCount++;

		if (value == true) {
			result = "PASS";
			testPassCount++;
		} else {
			result = "FAIL";
		}

		return result;
	}

	public static String captureListPrint(DLList list, boolean verbose) {
		// http://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
		// Create a stream to hold the output

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		list.printList(verbose);
		// Put things back
		System.out.flush();
		System.setOut(old);
		// Show what happened
		return baos.toString();
	}

	public static <T> boolean assertCustom(T received, T expected) {

		assertions++;

		if (received.equals(expected)) {
			assertionsPassed++;
			return true;
		} else {
			System.out.println("---- START ASSERT FAILUIRE----");
			System.out.println("Recevied: \n" + received.toString());
			System.out.println("Expected: \n" + expected.toString());
			System.out.println("---- END SASSERT FAILUIRE----");
			return false;
		}
	}

	public static <T> boolean assertCustom(T received, T expected, boolean silent) {

		if (received.equals(expected)) {
			return true;
		} else {
			if (!silent) {
				System.out.println("---- START ASSERT FAILUIRE----");
				System.out.println("Recevied: \n" + received.toString());
				System.out.println("Expected: \n" + expected.toString());
				System.out.println("---- END SASSERT FAILUIRE----");
			}
			return false;
		}
	}

	public static boolean assertInt(Integer received, Integer expected) {

		if (received.equals(expected)) {
			return true;
		} else {
			System.out.println("---- START STRING ASSERT----");
			System.out.println("Recevied: \n" + received);
			System.out.println("Expected: \n" + expected);
			System.out.println("---- END STRING ASSERT ----");
			return false;
		}
	}
}
