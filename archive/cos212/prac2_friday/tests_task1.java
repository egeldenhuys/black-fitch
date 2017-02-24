import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class tests_task1 {

	static Integer testCount = 1;
	static Integer testPassCount = 0;
	static Integer assertions = 0;
	static Integer assertionsPassed = 0;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...\n");

		test_fail_works("test_fail_works");
		test_add_scalar("test_add_scalar");

		System.out.println("");
		testCount--;
		System.out.println("Test casses passed:" + testPassCount.toString() + "/" + testCount.toString());
		System.out.println("Assertions passed:" + assertionsPassed.toString() + "/" + assertions.toString());
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

	public static boolean test_add_scalar(String name) {
		printHeader(name);
		boolean result = true;

		String expected = "";
		String received = "";

		// MANY
		Node head = new Node(1.23, null);
		addToTail(head, 2.44);
		addToTail(head, 3.69);
		addToTail(head, 4.222);
		// [1.23,2.44,3.69,4.222]

		Recursive.addScalar(head, 5.2);

		expected = "[6.43,8.64,10.89,12.422]";
		received = getList(head);

		result = result && assertCustom(received, expected);

		// ONE
		head = null;
		head = new Node(7.77, null);

		Recursive.addScalar(head, 4.21);
		expected = "[11.98]";
		received = getList(head);

		result = result && assertCustom(received, expected);

		// None
		head = null;

		Recursive.addScalar(head, 7.77);
		expected = "[]";
		received = getList(head);

		result = result && assertCustom(received, expected);

		// GIVEN Main
		Node n1 = new Node(14.2);
		Node n2 = new Node(23.1,n1);
		Node n3 = new Node(21.6,n2);
		Node n4 = new Node(11.8,n3);
		Node n5 = new Node(15.3,n4);

		Recursive.addScalar(n5,3);

		result = result && assertCustom(n5.getValue(),18.3);
		result = result && assertCustom(n4.getValue(), 15.8);
		result = result && assertCustom(n3.getValue(), 26.6);
		result = result && assertCustom(n2.getValue(), 29.1);
		result = result && assertCustom(n1.getValue(), 21.2);
		result = result && (n1.getNextNode() == null);

		printFooter(name, result);
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
