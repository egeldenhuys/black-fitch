import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class tests_task4 {

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
		Node head;

		/* cases
			list is empty
			list contains one node
			list contains many nodes

		*/

		printSubTest("Merge ascending");
		head = new Node(1.1, null);
		addToTail(head, 2.2);
		addToTail(head, 3.3);
		addToTail(head, 4.4);
		addToTail(head, 0.67);

		Recursive.mergeSomeNodes(head);
		expected = "[5.5,5.5,0.67]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge descending");
		head = new Node(9.9, null);
		addToTail(head, 8.8);
		addToTail(head, 5.5);
		addToTail(head, 3.3);
		addToTail(head, 1.1);

		Recursive.mergeSomeNodes(head);
		expected = "[9.9,8.8,5.5,3.3,1.1]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge first and last only");
		head = new Node(1.42, null);
		addToTail(head, 4.3);
		addToTail(head, 4.2);
		addToTail(head, 10.51);

		Recursive.mergeSomeNodes(head);
		expected = "[11.93,4.3,4.2]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge last two");
		head = new Node(5.42, null);
		addToTail(head, 4.3);
		addToTail(head, 3.2);
		addToTail(head, 10.51);

		Recursive.mergeSomeNodes(head);
		expected = "[5.42,4.3,13.71]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge middle");
		head = new Node(10.4, null);
		addToTail(head, 3.3);
		addToTail(head, 5.2);
		addToTail(head, 1.4);

		Recursive.mergeSomeNodes(head);
		expected = "[10.4,8.5,1.4]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge complex 1");
		head = new Node(50.23, null);
		addToTail(head, 49.5);
		addToTail(head, 60.1);
		addToTail(head, 70.8);
		addToTail(head, 75.2);
		addToTail(head, 5.99);
		addToTail(head, 7.17);
		addToTail(head, 12.43);
		addToTail(head, 100.13);

		Recursive.mergeSomeNodes(head);
		expected = "[50.23,149.63,135.3,70.8,18.42,7.17]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge collapse (even)");
		head = new Node(50.1, null);
		addToTail(head, 60.2);
		addToTail(head, 70.3);
		addToTail(head, 80.4);
		addToTail(head, 90.5);
		addToTail(head, 100.6);

		Recursive.mergeSomeNodes(head);
		expected = "[150.7,150.7,150.7]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge collapse (odd)");
		head = new Node(50.1, null);
		addToTail(head, 60.2);
		addToTail(head, 70.3);
		addToTail(head, 71.21);
		addToTail(head, 80.4);
		addToTail(head, 90.5);
		addToTail(head, 100.6);

		Recursive.mergeSomeNodes(head);
		expected = "[150.7,150.7,150.7,71.21]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge when all the same");
		head = new Node(10.12, null);
		addToTail(head, 10.12);
		addToTail(head, 10.12);
		addToTail(head, 10.12);

		Recursive.mergeSomeNodes(head);
		expected = "[10.12,10.12,10.12,10.12]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("Merge with one element");
		head = new Node(10.12, null);

		Recursive.mergeSomeNodes(head);
		expected = "[10.12]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("Merge with two element ascending");
		head = new Node(10.12, null);
		addToTail(head, 20.45);

		Recursive.mergeSomeNodes(head);
		expected = "[30.57]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("Merge with two element descending");
		head = new Node(20.45, null);
		addToTail(head, 1.12);

		Recursive.mergeSomeNodes(head);
		expected = "[20.45,1.12]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("Merge with two element descending");
		head = new Node(20.45, null);
		addToTail(head, 1.12);

		Recursive.mergeSomeNodes(head);
		expected = "[20.45,1.12]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("merge descending");
		head = new Node(9.9, null);
		addToTail(head, 8.8);
		addToTail(head, 5.5);
		addToTail(head, 3.3);
		addToTail(head, 1.1);

		Recursive.mergeSomeNodes(head);
		expected = "[9.9,8.8,5.5,3.3,1.1]";
		received = getList(head);
		result = result && assertCustom(received, expected);

		printSubTest("given main");
		Node n1 = new Node(44.2);
		Node n2 = new Node(35.1,n1);
		Node n3 = new Node(21.6,n2);
		Node n4 = new Node(11.8,n3);
		Node n5 = new Node(15.3,n4);

		Recursive.mergeSomeNodes(n5);

		// 15.3
		result = result && assertCustom(n5.getValue(), 15.3);
		Node tmp = n5.getNextNode();

		//56.0
		result = result && assertCustom(tmp.getValue(), 56.0);
		tmp = tmp.getNextNode();

		//56.7
		result = result && assertCustom(tmp.getValue(), 56.7);
		tmp = tmp.getNextNode();

		// true
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
