/*
MIT License

Copyright (c) 2017 Evert Geldenhuys

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class tests_All extends UnitTest{


	public boolean run() {

		boolean result = true;
		result = result & test_findKey("Task1: findKey()");
		result = result & test_treeRotations("Task2: Tree Rotations");


		return result;
	}

	public Node findKey(Node root, int key, StringBuilder output) {
		// http://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
		// Create a stream to hold the output

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		Node ret = TreapOperations.findKey(root, key);
		// Put things back
		System.out.flush();
		System.setOut(old);
		// Show what happened

		output.append(baos.toString());
		return ret;
	}

	public void rotateDown(Node node, StringBuilder output) {
		// http://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
		// Create a stream to hold the output

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		TreapOperations.rotateDown(node);
		// Put things back
		System.out.flush();
		System.setOut(old);
		// Show what happened

		output.append(baos.toString());
	}

	public void rotateUp(Node node, StringBuilder output) {
		// http://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
		// Create a stream to hold the output

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		TreapOperations.rotateUp(node);
		// Put things back
		System.out.flush();
		System.setOut(old);
		// Show what happened

		output.append(baos.toString());
	}

	public boolean test_template(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test Template");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom("a", "b");


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END

	}

	public boolean test_findKey(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// Test cases provided by University of Pretoria
		// Ported to JavaFitch by @egeldenhuys

		StringBuilder recv = new StringBuilder();
		String expected = "";

		Node node1 = new Node(23687, 29, "hi");
		Node node2 = new Node(43546, 42, "hold");
		Node node3 = new Node(73487, 87, "test");
		Node node4 = new Node(23589, 49, "swing");
		Node node5 = new Node(98477, 48, "bird");
		Node node6 = new Node(30895, 47, "cat");
		Node node7 = new Node(78394, 23, "rocket");
		Node node8 = new Node(24511, 57, "daisy");
		Node node9 = new Node(51423, 97, "doggo");

		Node root = node9;
		node9.leftChild_ = node8; node8.parent_ = node9;
			node8.leftChild_ = node4; node4.parent_ = node8;
				node4.rightChild_ = node1; node1.parent_ = node4;
			node8.rightChild_ = node6; node6.parent_ = node8;
				node6.rightChild_ = node2; node2.parent_ = node6;
		node9.rightChild_ = node3; node3.parent_ = node9;
			node3.rightChild_ = node5; node5.parent_ = node3;
				node5.leftChild_ = node7; node7.parent_ = node5;

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_1 Travarse Left");
		subResult = true;
		// ==========================================================

		expected = "Accessed key of doggo\nAccessed key of daisy\nAccessed key of swing\nThe first result is swing\n";

		Node ret = findKey(root,23589, recv);
		recv.append("The first result is " + ret.getValue() + "\n");

		/*	Output should be:
			Accessed key of doggo
			Accessed key of daisy
			Accessed key of swing
			The first result is swing
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_2 Traverse Right");
		subResult = true;
		// ==========================================================

		recv = new StringBuilder();
		ret = findKey(root, 78394, recv);
		recv.append("The second result is " + ret.getValue() + "\n");

		expected = "Accessed key of doggo\nAccessed key of test\nAccessed key of bird\nAccessed key of rocket\nThe second result is rocket\n";


		/*	Output should be:
			Accessed key of doggo
			Accessed key of test
			Accessed key of bird
			Accessed key of rocket
			The second result is rocket
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("When key not found, return null");
		subResult = true;
		// ==========================================================

		recv = new StringBuilder();
		ret = findKey(root, 69, recv);

		subResult = subResult & assertNull(ret);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END

	}

	public boolean test_treeRotations(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// Test cases provided by University of Pretoria
		// Ported to JavaFitch by @egeldenhuys

		StringBuilder recv = new StringBuilder();
		String expected = "";

		Node node1 = new Node(23687, 29, "hi");
		Node node2 = new Node(43546, 42, "hold");
		Node node3 = new Node(73487, 87, "test");
		Node node4 = new Node(23589, 49, "swing");
		Node node5 = new Node(98477, 48, "bird");
		Node node6 = new Node(30895, 47, "cat");
		Node node7 = new Node(78394, 23, "rocket");
		Node node8 = new Node(24511, 57, "daisy");
		Node node9 = new Node(51423, 97, "doggo");

		Node root = node9;
		node9.leftChild_ = node8; node8.parent_ = node9;
			node8.leftChild_ = node4; node4.parent_ = node8;
				node4.rightChild_ = node1; node1.parent_ = node4;
			node8.rightChild_ = node6; node6.parent_ = node8;
				node6.rightChild_ = node2; node2.parent_ = node6;
		node9.rightChild_ = node3; node3.parent_ = node9;
			node3.rightChild_ = node5; node5.parent_ = node3;
				node5.leftChild_ = node7; node7.parent_ = node5;

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_1 Find Key");
		subResult = true;
		// ==========================================================

		expected = "Accessed key of doggo\nAccessed key of daisy\nAccessed key of swing\nAccessed key of hi\nThe first result is hi\n";

		recv = new StringBuilder();
		Node ret = findKey(root, 23687, recv);
		recv.append("The first result is " + ret.getValue() + "\n");

		/*
		Accessed key of doggo
		Accessed key of daisy
		Accessed key of swing
		Accessed key of hi
		The first result is hi
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_2 Rotate Left");
		subResult = true;
		// ==========================================================

		TreapOperations.rotateLeft(node8);
		recv = new StringBuilder();
		ret = findKey(root,23687, recv);
		recv.append("The first result is " + ret.getValue() + "\n");

		expected = "Accessed key of doggo\nAccessed key of cat\nAccessed key of daisy\nAccessed key of swing\nAccessed key of hi\nThe first result is hi\n";

		/*
		Accessed key of doggo
		Accessed key of cat
		Accessed key of daisy
		Accessed key of swing
		Accessed key of hi
		The first result is hi
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_3 Rotate Right");
		subResult = true;
		// ==========================================================


		TreapOperations.rotateRight(node6);
		recv = new StringBuilder();
		ret = findKey(root,43546, recv);
		recv.append("The second result is " + ret.getValue() + "\n");

		expected = "Accessed key of doggo\nAccessed key of daisy\nAccessed key of cat\nAccessed key of hold\nThe second result is hold\n";

		/*
		Accessed key of doggo
		Accessed key of daisy
		Accessed key of cat
		Accessed key of hold
		The second result is hold
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_4 Rotate Down");
		subResult = true;
		// ==========================================================

		recv = new StringBuilder();
		rotateDown(node8, recv);
		ret = findKey(root,43546, recv);
		recv.append("The third result is " + ret.getValue() + "\n");

		expected = "Accessed priority of swing\nAccessed priority of cat\nAccessed key of doggo\nAccessed key of swing\nAccessed key of daisy\nAccessed key of cat\nAccessed key of hold\nThe third result is hold\n";

		/*
		Accessed priority of swing
		Accessed priority of cat
		Accessed key of doggo
		Accessed key of swing
		Accessed key of daisy
		Accessed key of cat
		Accessed key of hold
		The third result is hold
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Test_5 Rotate Up");
		subResult = true;
		// ==========================================================

		recv = new StringBuilder();

		rotateUp(node8, recv);
		ret = findKey(root,43546, recv);
		recv.append("The fourth result is " + ret.getValue() + "\n");

		expected = "Accessed key of doggo\nAccessed key of daisy\nAccessed key of cat\nAccessed key of hold\nThe fourth result is hold\n";

		/*
		Accessed key of doggo
		Accessed key of daisy
		Accessed key of cat
		Accessed key of hold
		The fourth result is hold
		*/

		subResult = subResult & assertCustom(recv.toString(), expected);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Rotate Left Basic");
		subResult = true;
		// ==========================================================


		node1 = new Node(500, 50, "1");
		node2 = new Node(450, 40, "2");
		node3 = new Node(700, 38, "3");
		node4 = new Node(600, 20, "4");
		node5 = new Node(750, 25, "5");

		root = node1;
		node1.leftChild_ = node2; node2.parent_ = node1;
		node1.rightChild_ = node3; node3.parent_ = node1;
			node3.leftChild_ = node4; node4.parent_ = node3;
			node3.rightChild_ = node5; node5.parent_ = node3;

		TreapOperations.rotateLeft(root);

		subResult = subResult & assertCustom(node3.leftChild_, node1);
		subResult = subResult & assertNull(node3.parent_);
		subResult = subResult & assertCustom(node1.parent_, node3);
		subResult = subResult & assertCustom(node1.leftChild_, node2);
		subResult = subResult & assertCustom(node1.rightChild_, node4);
		subResult = subResult & assertCustom(node2.parent_, node1);
		subResult = subResult & assertCustom(node4.parent_, node1);
		subResult = subResult & assertNull(node2.leftChild_);
		subResult = subResult & assertNull(node2.rightChild_);
		subResult = subResult & assertNull(node4.leftChild_);
		subResult = subResult & assertNull(node4.rightChild_);
		subResult = subResult & assertCustom(node3.rightChild_, node5);
		subResult = subResult & assertCustom(node5.parent_, node3);
		subResult = subResult & assertNull(node5.leftChild_);
		subResult = subResult & assertNull(node5.rightChild_);

		System.out.println("\nROTATE LEFT AGAIN...");

		TreapOperations.rotateLeft(node3);

		subResult = subResult & assertCustom(node3.leftChild_, node1);
		subResult = subResult & assertCustom(node3.parent_, node5);
		subResult = subResult & assertCustom(node1.parent_, node3);
		subResult = subResult & assertCustom(node1.leftChild_, node2);
		subResult = subResult & assertCustom(node1.rightChild_, node4);
		subResult = subResult & assertCustom(node2.parent_, node1);
		subResult = subResult & assertCustom(node4.parent_, node1);
		subResult = subResult & assertNull(node2.leftChild_);
		subResult = subResult & assertNull(node2.rightChild_);
		subResult = subResult & assertNull(node4.leftChild_);
		subResult = subResult & assertNull(node4.rightChild_);
		subResult = subResult & assertNull(node3.rightChild_);
		subResult = subResult & assertNull(node5.parent_);
		subResult = subResult & assertCustom(node5.leftChild_, node3);
		subResult = subResult & assertNull(node5.rightChild_);

		System.out.println("\nROTATE LEFT AGAIN...");

		TreapOperations.rotateLeft(node5);

		subResult = subResult & assertCustom(node3.leftChild_, node1);
		subResult = subResult & assertCustom(node3.parent_, node5);
		subResult = subResult & assertCustom(node1.parent_, node3);
		subResult = subResult & assertCustom(node1.leftChild_, node2);
		subResult = subResult & assertCustom(node1.rightChild_, node4);
		subResult = subResult & assertCustom(node2.parent_, node1);
		subResult = subResult & assertCustom(node4.parent_, node1);
		subResult = subResult & assertNull(node2.leftChild_);
		subResult = subResult & assertNull(node2.rightChild_);
		subResult = subResult & assertNull(node4.leftChild_);
		subResult = subResult & assertNull(node4.rightChild_);
		subResult = subResult & assertNull(node3.rightChild_);
		subResult = subResult & assertNull(node5.parent_);
		subResult = subResult & assertCustom(node5.leftChild_, node3);
		subResult = subResult & assertNull(node5.rightChild_);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END

	}


}
