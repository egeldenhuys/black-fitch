import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.*;

public class tests_task4 {

	static Integer testCount = 1;
	static Integer testPassCount = 0;
	static Integer assertions = 0;
	static Integer assertionsPassed = 0;

	public static void main(String[] args) {

		System.out.println("Starting Java Fitch...\n");

		test_mergeSomeNodes("test_mergeSomeNodes");
		//test_getLargest("test_getLargest");
		//test_fuzz("test_fuzz");

		//test_brute_force_merge("test_brute_force_merge");

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

		result = result & assertCustom(received, expected);

		printFooter(name, result);
		return result;
	}

	// public static boolean test_fuzz(String name) {
	// 	printHeader(name);
	// 	boolean result = true;
	//
	// 	String expected = "";
	// 	String received = "";
	// 	Node head = null;
	//
	// 	int ITERATIONS = 100;
	// 	int NODE_COUNT = 100;
	// 	double rangeMin = -100;
	// 	double rangeMax = 100;
	//
	// 	// Merge loop
	// 	for (int i = 0; i < ITERATIONS; i++) {
	// 		printSubTest("Iteration: " + i);
	// 		Random rand = new Random();
	//
	// 		// Randoms loop
	// 		for (int j = 0; j < NODE_COUNT; j++) {
	// 			double randomValue = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
	// 			//int randomValue = rand.nextInt(200) -100;
	//
	// 			if (j == 0) {
	// 				head = new Node(randomValue, null);
	// 			} else {
	// 				addToTail(head, randomValue);
	// 			}
	//
	// 		}
	//
	// 		// Test
	// 		Recursive.mergeSomeNodes(head);
	// 		expected =
	// 		received = getList(head);
	// 		result = result & assertCustom(received, expected);
	// 	}
	//
	//
	// 	printFooter(name, result);
	// 	return result;
	// }

	// public static boolean test_getLargest(String name) {
	// 	printHeader(name);
	// 	boolean result = true;
	//
	// 	double expected = 0;
	// 	double received = 0;
	// 	Node head = null;
	// 	double largest = 0;
	//
	// 	printSubTest("first");
	// 	head = new Node(100.2, null);
	// 	addToTail(head, 90.4);
	// 	addToTail(head, 80.2);
	// 	addToTail(head, 85.2);
	// 	addToTail(head, 86.3);
	//
	// 	expected = 100.2;
	// 	received = Recursive.getLargest(head).getValue();
	// 	result = result & assertCustom(received, expected);
	//
	// 	printSubTest("second");
	// 	head = new Node(5, null);
	// 	addToTail(head, 900.4);
	// 	addToTail(head, 80.2);
	// 	addToTail(head, 85.2);
	// 	addToTail(head, 86.3);
	//
	// 	expected = 900.4;
	// 	received = Recursive.getLargest(head).getValue();
	// 	result = result & assertCustom(received, expected);
	//
	// 	printSubTest("last");
	// 	head = new Node(4.5, null);
	// 	addToTail(head, 90.4);
	// 	addToTail(head, 80.2);
	// 	addToTail(head, 85.2);
	// 	addToTail(head, 860.3);
	//
	// 	expected = 860.3;
	// 	received = Recursive.getLargest(head).getValue();
	// 	result = result & assertCustom(received, expected);
	//
	// 	printSubTest("null");
	// 	head = null;
	// 	result = result & assertCustom(Recursive.getLargest(head) == null, true);
	//
	//
	// 	printSubTest("skip first");
	// 	head = new Node(105.4, null);
	// 	addToTail(head, 90.4);
	// 	addToTail(head, 80.2);
	// 	addToTail(head, 85.2);
	// 	addToTail(head, 86.5);
	//
	// 	expected = 90.4;
	// 	received = Recursive.getLargest(head.getNextNode()).getValue();
	// 	result = result & assertCustom(received, expected);
	//
	// 	printSubTest("given last node");
	// 	head = new Node(4.5, null);
	// 	addToTail(head, 90.4);
	// 	addToTail(head, 80.2);
	// 	addToTail(head, 85.2);
	// 	addToTail(head, 1.2);
	//
	// 	expected = 1.2;
	// 	received = Recursive.getLargest(head.getNextNode().getNextNode().getNextNode().getNextNode()).getValue();
	// 	result = result & assertCustom(received, expected);
	//
	// 	printFooter(name, result);
	// 	return result;
	// }

	// public static boolean test_brute_force_merge(String name) {
	// 	printHeader(name);
	// 	boolean result = true;
	//
	// 	String expected = "";
	// 	String received = "";
	// 	Node head = null;
	//
	// 	//50 is the maximum and the 1 is our minimum
	//
	// 	// Merge loop
	// 	for (int i = 0; i < 10; i++) {
	// 		printSubTest("Iteration: " + i);
	// 		Random rand = new Random();
	//
	// 		// Randoms loop
	// 		for (int j = 0; j < 5; j++) {
	// 			//double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	// 			int randomValue = rand.nextInt(200) -100;
	//
	// 			if (j == 0) {
	// 				head = new Node(randomValue, null);
	// 			} else {
	// 				addToTail(head, randomValue);
	// 			}
	//
	// 		}
	//
	// 		System.out.println(" ");
	// 		System.out.println("Original: " + getList(head));
	// 		Recursive.mergeSomeNodes(head);
	// 		System.out.println("New     : " + getList(head));
	// 		System.out.println(" ");
	//
	// 	}
	//
	// 	return false;
	// }

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

		printSubTest("WTF");
		head = new Node(10.1, null);
		addToTail(head, 90.4);
		addToTail(head, 80.2);
		addToTail(head, 85.2);
		addToTail(head, 86.3);

		Recursive.mergeSomeNodes(head);
		expected = "[100.5,166.5,85.2]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("cone");
		head = new Node(50.0, null);
		addToTail(head, 60.0);
		addToTail(head, 40.0);
		addToTail(head, 70.0);
		addToTail(head, 30.0);
		addToTail(head, 80.0);
		addToTail(head, 20.0);

		Recursive.mergeSomeNodes(head);
		expected = "[130.0,60.0,110.0,30.0,20.0]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("cone");
		head = new Node(50.0, null);
		addToTail(head, 60.0);
		addToTail(head, 40.0);
		addToTail(head, 70.0);
		addToTail(head, 30.0);
		addToTail(head, 80.0);
		addToTail(head, 20.0);

		Recursive.mergeSomeNodes(head);
		expected = "[130.0,60.0,110.0,30.0,20.0]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("same after merge");
		head = new Node(10.1, null);
		addToTail(head, 20.4);
		addToTail(head, 20.2);
		addToTail(head, 20.2);

		Recursive.mergeSomeNodes(head);
		expected = "[30.5,20.2,20.2]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge alternating, pairs, even");
		head = new Node(10.1, null);
		addToTail(head, 20.2);
		addToTail(head, 50.3);
		addToTail(head, 30.5);

		Recursive.mergeSomeNodes(head);
		expected = "[60.4,50.7]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge alternating, pairs, odd");
		head = new Node(10.1, null);
		addToTail(head, 20.2);
		addToTail(head, 50.3);
		addToTail(head, 30.5);
		addToTail(head, 5.5);

		Recursive.mergeSomeNodes(head);
		expected = "[60.4,50.7,5.5]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("Merge ascending");
		head = new Node(1.1, null);
		addToTail(head, 2.2);
		addToTail(head, 3.3);
		addToTail(head, 4.4);
		addToTail(head, 0.67);

		Recursive.mergeSomeNodes(head);
		expected = "[5.5,5.5,0.67]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge descending");
		head = new Node(9.9, null);
		addToTail(head, 8.8);
		addToTail(head, 5.5);
		addToTail(head, 3.3);
		addToTail(head, 1.1);

		Recursive.mergeSomeNodes(head);
		expected = "[9.9,8.8,5.5,3.3,1.1]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge first and last only");
		head = new Node(1.42, null);
		addToTail(head, 4.3);
		addToTail(head, 4.2);
		addToTail(head, 10.51);

		Recursive.mergeSomeNodes(head);
		expected = "[11.93,4.3,4.2]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge last two");
		head = new Node(5.42, null);
		addToTail(head, 4.3);
		addToTail(head, 3.2);
		addToTail(head, 10.51);

		Recursive.mergeSomeNodes(head);
		expected = "[5.42,4.3,13.71]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge middle");
		head = new Node(10.4, null);
		addToTail(head, 3.3);
		addToTail(head, 5.2);
		addToTail(head, 1.4);

		Recursive.mergeSomeNodes(head);
		expected = "[10.4,8.5,1.4]";
		received = getList(head);
		result = result & assertCustom(received, expected);

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
		result = result & assertCustom(received, expected);

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
		result = result & assertCustom(received, expected);

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
		result = result & assertCustom(received, expected);

		printSubTest("merge when all the same");
		head = new Node(10.12, null);
		addToTail(head, 10.12);
		addToTail(head, 10.12);
		addToTail(head, 10.12);

		Recursive.mergeSomeNodes(head);
		expected = "[10.12,10.12,10.12,10.12]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("Merge with one element");
		head = new Node(10.12, null);

		Recursive.mergeSomeNodes(head);
		expected = "[10.12]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("Merge with two element ascending");
		head = new Node(10.12, null);
		addToTail(head, 20.45);

		Recursive.mergeSomeNodes(head);
		expected = "[30.57]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("Merge with two element descending");
		head = new Node(20.45, null);
		addToTail(head, 1.12);

		Recursive.mergeSomeNodes(head);
		expected = "[20.45,1.12]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("Merge with two element descending");
		head = new Node(20.45, null);
		addToTail(head, 1.12);

		Recursive.mergeSomeNodes(head);
		expected = "[20.45,1.12]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("merge descending");
		head = new Node(9.9, null);
		addToTail(head, 8.8);
		addToTail(head, 5.5);
		addToTail(head, 3.3);
		addToTail(head, 1.1);

		Recursive.mergeSomeNodes(head);
		expected = "[9.9,8.8,5.5,3.3,1.1]";
		received = getList(head);
		result = result & assertCustom(received, expected);

		printSubTest("given main");
		Node n1 = new Node(44.2);
		Node n2 = new Node(35.1,n1);
		Node n3 = new Node(21.6,n2);
		Node n4 = new Node(11.8,n3);
		Node n5 = new Node(15.3,n4);

		Recursive.mergeSomeNodes(n5);

		// 15.3
		result = result & assertCustom(n5.getValue(), 15.3);
		Node tmp = n5.getNextNode();

		//56.0
		result = result & assertCustom(tmp.getValue(), 56.0);
		tmp = tmp.getNextNode();

		//56.7
		result = result & assertCustom(tmp.getValue(), 56.7);
		tmp = tmp.getNextNode();

		// true
		result = result & assertCustom(tmp == null, true);

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

			result = result & !assertCustom(10, 11, true);
			result = result & !assertCustom("ABC", "NAR", true);
			result = result & !assertCustom(true, false, true);

			result = result & assertCustom(1, 1);
			result = result & assertCustom("GET", "GET");
			result = result & assertCustom(true, true);
			result = result & assertCustom(false, false);

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
			System.out.println("---- END ASSERT FAILUIRE----");
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
