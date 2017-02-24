import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class tests_task3 {

	static Integer testCount = 1;
	static Integer testPassCount = 0;
	static Integer assertions = 0;
	static Integer assertionsPassed = 0;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...\n");

		test_mergeSomeNodes("test_mergeSomeNodes");

		System.out.println("");
		testCount--;
		System.out.println("Test casses passed:" + testPassCount.toString() + "/" + testCount.toString());
		System.out.println("Assertions passed:" + assertionsPassed.toString() + "/" + assertions.toString());
	}

	public static boolean test_mergeSomeNodes(String name) {
		printHeader(name);
		boolean result = true;

		String expected = "";
		String received = "";

		printSubTest("Merge one occurence");
		Node head = new Node(1.55, null);
		addToTail(head, 2.44);
		addToTail(head, 7.88);
		addToTail(head, 3.12);

		Recursive.mergeSomeNodes(head);
		expected = "[3.99,7.88,3.12]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("multiple merges");
		head = new Node(1.12, null);
		addToTail(head, 2.23);
		addToTail(head, 7.18);
		addToTail(head, 10.11);

		Recursive.mergeSomeNodes(head);
		expected = "[3.35,17.29]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge last two");
		head = new Node(10.12, null);
		addToTail(head, 9.23);
		addToTail(head, 7.18);
		addToTail(head, 10.11);

		Recursive.mergeSomeNodes(head);
		expected = "[10.12,9.23,17.29]";
		received = getList(head);
		result = result && assertCustom(received, expected);


		printSubTest("Merge no occurence");
		head = new Node(9.55, null);
		addToTail(head, 8.44);
		addToTail(head, 7.85);
		addToTail(head, 6.12);

		Recursive.mergeSomeNodes(head);
		expected = "[9.55,8.44,7.85,6.12]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("Merge null head");
		head = null;
		Recursive.mergeSomeNodes(head);

		expected = "[]";
		received = getList(head);
		result = result && assertCustom(received, expected);


		printSubTest("given main");
		// GIVEN Main
		Node n1 = new Node(44.2);
		Node n2 = new Node(35.1,n1);
		Node n3 = new Node(21.6,n2);
		Node n4 = new Node(11.8,n3);
		Node n5 = new Node(15.3,n4);

		Recursive.mergeSomeNodes(n5);

		Node tmp = n5;
		result = result && assertCustom(tmp.getValue(), 15.3);

		tmp = tmp.getNextNode();
		result = result && assertCustom(tmp.getValue(), 33.400000000000006);

		tmp = tmp.getNextNode();
		result = result && assertCustom(tmp.getValue(), 79.30000000000001);

		tmp = tmp.getNextNode();
		result = result && assertCustom(tmp == null, true);

		printFooter(name, result);
		return result;
	}


	public static boolean test_template(String name) {
		printHeader(name);
		boolean result = true;

		String expected = "";
		String received = "";

		result = result && assertCustom(received, expected);

		printFooter(name, result);
		return result;
	}

	public static void addToTail(Node head, double val) {

		Node tmp = head;

		// Get last node
		for (; tmp.getNextNode() != null; tmp = tmp.getNextNode()) { }

		Node newNode = new Node(val, null);
		tmp.setNextNode(newNode);
	}

	public static String getList(Node head) {

		Node tmp = head;
		String result = "";
		result += "[";

		for (; tmp != null; tmp = tmp.getNextNode()) {
			//double tmpVal = tmp.getValue();
			result += tmp.getValue();

			if (tmp.getNextNode() != null) {
				result += ",";
			}
		}

		result += "]";

		return result;
	}


	public static boolean test_fail_works(String name) {
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
