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

import java.util.*;

public class tests_Task1 extends UnitTest{

	// public String getOutput(BST list) {
	// 	// http://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
	// 	// Create a stream to hold the output
	//
	// 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// 	PrintStream ps = new PrintStream(baos);
	// 	// IMPORTANT: Save the old System.out!
	// 	PrintStream old = System.out;
	// 	// Tell Java to use your special stream
	// 	System.setOut(ps);
	// 	// Print some output: goes to your special stream
	// 	list.printList(verbose);
	// 	// Put things back
	// 	System.out.flush();
	// 	System.setOut(old);
	// 	// Show what happened
	// 	return baos.toString();
	// }


	public boolean run() {

		boolean result = true;
		//result = result & run_mock_tests("Running Mock Tests");
		result = result & test_simple("Tests");

		result = result & tree_single_node("Case: Single Node");
		result = result & tree_right_only("Case: Right Skewed Tree");
		result = result & tree_left_only("Case: left Skewed Tree");
		result = result & tree_perfect("Case: Perfect Tree");
		result = result & tree_empty("Case: Empty Tree");


		// result = result & test_insert("testing insert()");
		// result = result & test_inorderAscending_and_inorderDescending("testing inorderAscending() and inorderDescending()");
		// result = result & test_countLeftThreads_and_countRightThreads("testing countLeftThreads() and countRightThreads()");


		return result;
	}

	public boolean test_simple(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		String received = "";
		// HEADER END

		// TEST CASE HEADER
		printSubTest("Adding 50,60,40,30,45,55,70,51,57,65,75");
		subResult = true;

		DoubleThreadedBST<Integer> tree = createTree(new Integer[] {50,60,40,30,45,55,70,51,57,65,75});

		// TEST AND ASSERT
		subResult = subResult & assertCustom(tree.inorderAscending(), "30,40,45,50,51,55,57,60,65,70,75");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Delete 60");
		subResult = true;
		tree.delete(60);
		subResult = subResult & assertCustom(tree.inorderAscending(), "30,40,45,50,51,55,57,65,70,75");
		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Delete 70");
		subResult = true;
		tree.delete(70);
		subResult = subResult & assertCustom(tree.inorderAscending(), "30,40,45,50,51,55,57,65,75");
		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Delete 65");
		subResult = true;
		tree.delete(65);
		subResult = subResult & assertCustom(tree.inorderAscending(), "30,40,45,50,51,55,57,75");
		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);




		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public <T extends Comparable<? super T>> DoubleThreadedBST<T> createTree(T[] arr) {


		DoubleThreadedBST<T> tree = new DoubleThreadedBST<T>();

		for (int i = 0; i < arr.length; i++) {
			tree.insert(arr[i]);
		}

		return tree;
	}

	// public boolean run_mock_tests(String title) {
	// 	// HEADER
	// 	printHeader(title);
	// 	boolean result = true;
	// 	boolean subResult = true;
	// 	// HEADER END
	//
	// 	ArrayList<MockDoubleThreadedBST<int>> mockList = new ArrayList<MockDoubleThreadedBST<int>>();
	//
	// 	MockDoubleThreadedBST<int> a = new MockDoubleThreadedBST<int>();
	//
	// 	a.insert = new Array[]{1,2,3,4,5};
	//
	// 	mockList.add(a);
	//
	// 	System.out.println(mockList[0].inorderDescending());
	//
	// 	// Create a bunch of mock trees and place them in an array
	// 	// Loop through the array
	// 	/*
	// 		INSERT
	// 		Get the insert array from the mock
	// 		create a real using the insert array from the mock
	// 		Compare all the Mock attributes with the real tree
	//
	// 		DELETE
	// 		Create a real using the mock insert array
	// 		Get the array of deletes from the Mock
	// 		Call delete on the real and the mock using the values from the array
	// 			When delete is called on the mock, change its internal state to match
	// 			Mock will have an array of states to be applied after the corresponding delete
	// 	*/
	//
	// 	/*
	// 	attributes
	//
	// 	*/
	//
	// 	// FOOTER
	// 	printFooter(title, result);
	// 	return result;
	// 	// FOOTER END
	// }

	public boolean tree_single_node(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Adding 1 to empty tree");
		subResult = true;
		// ==========================================================

		DoubleThreadedBST<Integer> tree = createTree(new Integer[] {1});


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Verify node structure");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getRoot().data, 1);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderAscending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderAscending(), "1");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderDescending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderDescending(), "1");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countLeftThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countLeftThreads(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countRightThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countRightThreads(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getNumberOfNodes()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 1);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getHeight()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getHeight(), 1);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("contains()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.contains(1), true);
		subResult = subResult & assertCustom(tree.contains(77), false);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("delete()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.delete(1), true);
		subResult = subResult & assertCustom(tree.contains(1), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 0);
		subResult = subResult & assertNull(tree.getRoot());

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

	public boolean tree_right_only(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Adding 1,2,3,4,5 to empty tree");
		subResult = true;
		// ==========================================================

		DoubleThreadedBST<Integer> tree = new DoubleThreadedBST<Integer>();
		for (int i = 1; i < 6; i++)
			tree.insert(i);


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Verify node structure");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getRoot().data, 1);
		subResult = subResult & assertCustom(tree.getRoot().right.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().right.right.data, 3);
		subResult = subResult & assertCustom(tree.getRoot().right.right.right.data, 4);
		subResult = subResult & assertCustom(tree.getRoot().right.right.right.right.data, 5);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderAscending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4,5");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderDescending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderDescending(), "5,4,3,2,1");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countLeftThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countLeftThreads(), 4);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countRightThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countRightThreads(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getNumberOfNodes()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 5);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getHeight()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getHeight(), 5);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("contains");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.contains(0), false);
		subResult = subResult & assertCustom(tree.contains(1), true);
		subResult = subResult & assertCustom(tree.contains(2), true);
		subResult = subResult & assertCustom(tree.contains(3), true);
		subResult = subResult & assertCustom(tree.contains(4), true);
		subResult = subResult & assertCustom(tree.contains(5), true);
		subResult = subResult & assertCustom(tree.contains(6), false);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Duplicated not allowed");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.insert(1), false);
		subResult = subResult & assertCustom(tree.contains(1), true);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 5);

		subResult = subResult & assertCustom(tree.insert(5), false);
		subResult = subResult & assertCustom(tree.contains(5), true);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 5);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// 1 2 3 4 5 (right)
		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("delete() first");
		subResult = true;
		// ==========================================================

		// Delete first
		//System.out.println("Checking if delete was successfull:");
		subResult = subResult & assertCustom(tree.delete(1), true);
		//System.out.println("Checking if the tree still contains 1:");
		subResult = subResult & assertCustom(tree.contains(1), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 4);
		subResult = subResult & assertCustom(tree.getRoot().data, 2);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,3,4,5");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// TEST CASE START										VVVV
		printSubTest("delete() last");
		subResult = true;
		// ==========================================================

		// Delete last
		subResult = subResult & assertCustom(tree.delete(5), true);
		subResult = subResult & assertCustom(tree.contains(5), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 3);
		subResult = subResult & assertCustom(tree.getRoot().data, 2);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,3,4");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// TEST CASE START										VVVV
		printSubTest("delete() remaining");
		subResult = true;
		// ==========================================================

		// Delete last fail
		System.out.println("A");
		subResult = subResult & assertCustom(tree.delete(5), false);
		subResult = subResult & assertCustom(tree.contains(5), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 3);
		subResult = subResult & assertCustom(tree.getRoot().data, 2);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,3,4");

		System.out.println("B");
		subResult = subResult & assertCustom(tree.delete(3), true);
		subResult = subResult & assertCustom(tree.contains(3), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 2);
		subResult = subResult & assertCustom(tree.getRoot().data, 2);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,4");

		// 1 2
		System.out.println(tree.inorderAscending());
		System.out.println("C");
		subResult = subResult & assertCustom(tree.delete(2), true);
		System.out.println("C1");
		subResult = subResult & assertCustom(tree.contains(2), false);
		System.out.println("C2");
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 1);
		System.out.println("C3");
		subResult = subResult & assertCustom(tree.getRoot().data, 4);
		System.out.println("C4");
		subResult = subResult & assertCustom(tree.inorderAscending(), "4");

		System.out.println("D");
		subResult = subResult & assertCustom(tree.delete(4), true);
		subResult = subResult & assertCustom(tree.contains(4), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 0);
		subResult = subResult & assertNull(tree.getRoot());
		subResult = subResult & assertCustom(tree.inorderAscending(), "");

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

	public boolean tree_left_only(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Adding 5,4,3,2,1 to empty tree");
		subResult = true;
		// ==========================================================

		DoubleThreadedBST<Integer> tree = createTree(new Integer[] {5,4,3,2,1});


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Verify node structure");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getRoot().data, 5);
		subResult = subResult & assertCustom(tree.getRoot().left.data, 4);
		subResult = subResult & assertCustom(tree.getRoot().left.left.data, 3);
		subResult = subResult & assertCustom(tree.getRoot().left.left.left.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().left.left.left.left.data, 1);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderAscending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4,5");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderDescending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderDescending(), "5,4,3,2,1");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countLeftThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countLeftThreads(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countRightThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countRightThreads(), 4);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getNumberOfNodes()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 5);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getHeight()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getHeight(), 5);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("contains");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.contains(0), false);
		subResult = subResult & assertCustom(tree.contains(1), true);
		subResult = subResult & assertCustom(tree.contains(2), true);
		subResult = subResult & assertCustom(tree.contains(3), true);
		subResult = subResult & assertCustom(tree.contains(4), true);
		subResult = subResult & assertCustom(tree.contains(5), true);
		subResult = subResult & assertCustom(tree.contains(6), false);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("delete() first");
		subResult = true;
		// ==========================================================

		// 5 4 3 2 1

		// Delete first
		System.out.println("Checking if delete was successfull:");
		subResult = subResult & assertCustom(tree.delete(5), true);
		System.out.println("Checking if the tree still contains 5:");
		subResult = subResult & assertCustom(tree.contains(5), false);
		System.out.println("Checking if tree has 4 nodes");
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 4);
		subResult = subResult & assertCustom(tree.getRoot().data, 4);
		subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// TEST CASE START										VVVV
		printSubTest("delete() last");
		subResult = true;
		// ==========================================================
		// 4 3 2 1
		// Delete last
		subResult = subResult & assertCustom(tree.delete(1), true);
		subResult = subResult & assertCustom(tree.contains(1), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 3);
		subResult = subResult & assertCustom(tree.getRoot().data, 4);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,3,4");
		// 4 3 2
		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// TEST CASE START										VVVV
		printSubTest("delete() remaining");
		subResult = true;
		// ==========================================================

		// Delete middle
		System.out.println("A");
		subResult = subResult & assertCustom(tree.delete(3), true);
		System.out.println("A2");
		subResult = subResult & assertCustom(tree.contains(3), false);
		System.out.println("A3");
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 2);
		subResult = subResult & assertCustom(tree.getRoot().data, 4);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,4");

		subResult = subResult & assertCustom(tree.delete(2), true);
		subResult = subResult & assertCustom(tree.contains(2), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 1);
		subResult = subResult & assertCustom(tree.getRoot().data, 4);
		subResult = subResult & assertCustom(tree.inorderAscending(), "4");

		subResult = subResult & assertCustom(tree.delete(4), true);
		subResult = subResult & assertCustom(tree.contains(4), false);
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 0);
		subResult = subResult & assertNull(tree.getRoot());
		subResult = subResult & assertCustom(tree.inorderAscending(), "");

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

	public boolean tree_perfect(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Adding 10,15,5,6,2,14,16 to empty tree");
		subResult = true;
		// ==========================================================

		DoubleThreadedBST<Integer> tree = createTree(new Integer[] {10,15,5,6,2,14,16});


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Verify node structure");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getRoot().data, 10);
		subResult = subResult & assertCustom(tree.getRoot().right.data, 15);
		subResult = subResult & assertCustom(tree.getRoot().right.right.data, 16);
		subResult = subResult & assertCustom(tree.getRoot().right.left.data, 14);
		subResult = subResult & assertCustom(tree.getRoot().left.data, 5);
		subResult = subResult & assertCustom(tree.getRoot().left.left.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().left.right.data, 6);


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderAscending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderAscending(), "2,5,6,10,14,15,16");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderDescending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderDescending(), "16,15,14,10,6,5,2");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countLeftThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countLeftThreads(), 3);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countRightThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countRightThreads(), 3);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getNumberOfNodes()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 7);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getHeight()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getHeight(), 3);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("contains");
		subResult = true;
		// ==========================================================

		// 16,15,14,10,6,5,2

		subResult = subResult & assertCustom(tree.contains(1), false);
		subResult = subResult & assertCustom(tree.contains(2), true);
		subResult = subResult & assertCustom(tree.contains(5), true);
		subResult = subResult & assertCustom(tree.contains(6), true);
		subResult = subResult & assertCustom(tree.contains(13), false);
		subResult = subResult & assertCustom(tree.contains(17), false);
		subResult = subResult & assertCustom(tree.contains(10), true);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("delete() root");
		subResult = true;
		// ==========================================================

		// 10,15,5,6,2,14,16

		// Delete first
		System.out.println("Checking if delete was successfull:");
		subResult = subResult & assertCustom(tree.delete(10), true);
		System.out.println("Checking if the tree still contains root:");
		subResult = subResult & assertCustom(tree.contains(10), false);
		System.out.println("Checking if tree has 6 nodes");
		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 6);
		subResult = subResult & assertCustom(tree.getRoot().data, 15);
		subResult = subResult & assertCustom(tree.inorderAscending(), "2,5,6,14,15,16");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// // TEST CASE START										VVVV
		// printSubTest("delete() last");
		// subResult = true;
		// // ==========================================================
		// // 4 3 2 1
		// // Delete last
		// subResult = subResult & assertCustom(tree.delete(1), true);
		// subResult = subResult & assertCustom(tree.contains(1), false);
		// subResult = subResult & assertCustom(tree.getNumberOfNodes(), 3);
		// subResult = subResult & assertCustom(tree.getRoot().data, 4);
		// subResult = subResult & assertCustom(tree.inorderAscending(), "2,3,4");
		// // 4 3 2
		// // =========================================================
		// result = result & subResult;
		// printSubTestFooter(subResult);
		// // TEST CASE END										^^^^
		// // =========================================================
		//
		// // TEST CASE START										VVVV
		// printSubTest("delete() remaining");
		// subResult = true;
		// // ==========================================================
		//
		// // Delete middle
		// System.out.println("A");
		// subResult = subResult & assertCustom(tree.delete(3), true);
		// System.out.println("A2");
		// subResult = subResult & assertCustom(tree.contains(3), false);
		// System.out.println("A3");
		// subResult = subResult & assertCustom(tree.getNumberOfNodes(), 2);
		// subResult = subResult & assertCustom(tree.getRoot().data, 4);
		// subResult = subResult & assertCustom(tree.inorderAscending(), "2,4");
		//
		// subResult = subResult & assertCustom(tree.delete(2), true);
		// subResult = subResult & assertCustom(tree.contains(2), false);
		// subResult = subResult & assertCustom(tree.getNumberOfNodes(), 1);
		// subResult = subResult & assertCustom(tree.getRoot().data, 4);
		// subResult = subResult & assertCustom(tree.inorderAscending(), "4");
		//
		// subResult = subResult & assertCustom(tree.delete(4), true);
		// subResult = subResult & assertCustom(tree.contains(4), false);
		// subResult = subResult & assertCustom(tree.getNumberOfNodes(), 0);
		// subResult = subResult & assertNull(tree.getRoot());
		// subResult = subResult & assertCustom(tree.inorderAscending(), "");
		//
		// // =========================================================
		// result = result & subResult;
		// printSubTestFooter(subResult);
		// // TEST CASE END										^^^^
		// // =========================================================

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean tree_empty(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Creating empty tree");
		subResult = true;
		// ==========================================================

		DoubleThreadedBST<Integer> tree = new DoubleThreadedBST<Integer>();


		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("Verify node structure");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertNull(tree.getRoot());

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderAscending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderAscending(), "");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("inorderDescending()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.inorderDescending(), "");

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countLeftThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countLeftThreads(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("countRightThreads()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.countRightThreads(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getNumberOfNodes()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getNumberOfNodes(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("getHeight()");
		subResult = true;
		// ==========================================================

		subResult = subResult & assertCustom(tree.getHeight(), 0);

		// =========================================================
		result = result & subResult;
		printSubTestFooter(subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		printSubTest("contains");
		subResult = true;
		// ==========================================================

		// 16,15,14,10,6,5,2

		subResult = subResult & assertCustom(tree.contains(1), false);
		subResult = subResult & assertCustom(tree.contains(0), false);

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

	public boolean test_template(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		String received = "";
		// HEADER END

		// TEST CASE HEADER
		printSubTest("Adding 1,2,3,4,5");
		subResult = true;
		DoubleThreadedBST<Integer> tree = new DoubleThreadedBST<Integer>();
		for (int i = 1; i < 6; i++)
			tree.insert(i);

		// TEST AND ASSERT
		subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4,5");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_insert(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		String received = "";
		// HEADER END

		// TEST CASE HEADER
		printSubTest("Adding 1,2,3,4,5. CASE: Right branch only.");
		subResult = true;

		DoubleThreadedBST<Integer> tree = createTree(new Integer[] {1,2,3,4,5});

		subResult = subResult & assertCustom(tree.getRoot().data, 1);
		subResult = subResult & assertCustom(tree.getRoot().right.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().right.right.data, 3);
		subResult = subResult & assertCustom(tree.getRoot().right.right.right.data, 4);
		subResult = subResult & assertCustom(tree.getRoot().right.right.right.right.data, 5);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Adding 5,4,3,2,1. CASE: Left branch only");
		subResult = true;

		// TEST AND ASSERT
		tree = createTree(new Integer[] {5,4,3,2,1});

		subResult = subResult & assertCustom(tree.getRoot().data, 5);
		subResult = subResult & assertCustom(tree.getRoot().left.data, 4);
		subResult = subResult & assertCustom(tree.getRoot().left.left.data, 3);
		subResult = subResult & assertCustom(tree.getRoot().left.left.left.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().left.left.left.left.data, 1);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Adding 10,5,2,8,6,9,7. CASE: Left only from root");
		subResult = true;

		tree = createTree(new Integer[] {10,5,2,8,6,9,7});

		subResult = subResult & assertCustom(tree.getRoot().data, 10);
		subResult = subResult & assertCustom(tree.getRoot().left.data, 5);
		subResult = subResult & assertCustom(tree.getRoot().left.left.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().left.right.data, 8);
		subResult = subResult & assertCustom(tree.getRoot().left.right.left.data, 6);
		subResult = subResult & assertCustom(tree.getRoot().left.right.left.right.data, 7);
		subResult = subResult & assertCustom(tree.getRoot().left.right.right.data, 9);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		printSubTest("Adding 10,15,7,8,6,9. CASE: Perfect Tree");
		subResult = true;

		tree = createTree(new Integer[] {10,15,5,6,2,14,16});


		subResult = subResult & assertCustom(tree.getRoot().data, 10);
		subResult = subResult & assertCustom(tree.getRoot().right.data, 15);
		subResult = subResult & assertCustom(tree.getRoot().right.right.data, 16);
		subResult = subResult & assertCustom(tree.getRoot().right.left.data, 14);
		subResult = subResult & assertCustom(tree.getRoot().left.data, 5);
		subResult = subResult & assertCustom(tree.getRoot().left.left.data, 2);
		subResult = subResult & assertCustom(tree.getRoot().left.right.data, 6);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_inorderAscending_and_inorderDescending(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		String received = "";
		// HEADER END

		// TEST CASE HEADER
		printSubTest("Adding 1,2,3,4,5. CASE: Right branch only.");
		subResult = true;
		DoubleThreadedBST<Integer> tree = createTree(new Integer[] {1,2,3,4,5});

		subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4,5");
		subResult = subResult & assertCustom(tree.inorderDescending(), "5,4,3,2,1");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Adding 5,4,3,2,1. CASE: Left branch only");
		subResult = true;
		tree = createTree(new Integer[] {5,4,3,2,1});

		subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4,5");
		subResult = subResult & assertCustom(tree.inorderDescending(), "5,4,3,2,1");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("Adding 10,5,2,8,6,9,7. CASE: Left only from root");
		subResult = true;
		tree = createTree(new Integer[] {10,5,2,8,6,9,7});

		subResult = subResult & assertCustom(tree.inorderAscending(), "2,5,6,7,8,9,10");
		subResult = subResult & assertCustom(tree.inorderDescending(), "10,9,8,7,6,5,2");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		printSubTest("Adding 10,15,7,8,6,9. CASE: Perfect Tree");
		subResult = true;

		tree = new DoubleThreadedBST<Integer>();
		tree.insert(10);
		tree.insert(15);
		tree.insert(5);
		tree.insert(6);
		tree.insert(2);
		tree.insert(14);
		tree.insert(16);

		subResult = subResult & assertCustom(tree.inorderAscending(), "2,5,6,10,14,15,16");
		subResult = subResult & assertCustom(tree.inorderDescending(), "16,15,14,10,6,5,2");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("Adding 100,90,95,80,91,79,170,160,150,200,165");
		subResult = true;

		tree = createTree(new Integer[] {100,90,95,80,91,79,170,160,150,200,165});

		subResult = subResult & assertCustom(tree.inorderAscending(), "79,80,90,91,95,100,150,160,165,170,200");
		subResult = subResult & assertCustom(tree.inorderDescending(), "200,170,165,160,150,100,95,91,90,80,79");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	// public boolean test_countLeftThreads_and_countRightThreads(String title) {
	// 	// HEADER
	// 	printHeader(title);
	// 	boolean result = true;
	// 	boolean subResult = true;
	// 	String received = "";
	// 	// HEADER END
	//
	// 	// TEST CASE HEADER
	// 	printSubTest("Adding 1,2,3,4,5. CASE: Right branch only.");
	// 	subResult = true;
	// 	DoubleThreadedBST<Integer> tree = createTree(new Integer[] {1,2,3,4,5});
	//
	// 	subResult = subResult & assertCustom(tree.countLeftThreads, 4);
	// 	subResult = subResult & assertCustom(tree.countRightThreads, 0);
	//
	// 	// TEST CASE FOOTER
	// 	result = result & subResult;
	// 	printSubTestFooter(subResult);
	//
	// 	// TEST CASE HEADER
	// 	printSubTest("Adding 5,4,3,2,1. CASE: Left branch only");
	// 	subResult = true;
	// 	tree = createTree(new Integer[] {5,4,3,2,1});
	//
	// 	subResult = subResult & assertCustom(tree.inorderAscending(), "1,2,3,4,5");
	// 	subResult = subResult & assertCustom(tree.inorderDescending(), "5,4,3,2,1");
	//
	// 	// TEST CASE FOOTER
	// 	result = result & subResult;
	// 	printSubTestFooter(subResult);
	//
	// 	// TEST CASE HEADER
	// 	printSubTest("Adding 10,5,2,8,6,9,7. CASE: Left only from root");
	// 	subResult = true;
	// 	tree = createTree(new Integer[] {10,5,2,8,6,9,7});
	//
	// 	subResult = subResult & assertCustom(tree.inorderAscending(), "2,5,6,7,8,9,10");
	// 	subResult = subResult & assertCustom(tree.inorderDescending(), "10,9,8,7,6,5,2");
	//
	// 	// TEST CASE FOOTER
	// 	result = result & subResult;
	// 	printSubTestFooter(subResult);
	//
	//
	// 	printSubTest("Adding 10,15,7,8,6,9. CASE: Perfect Tree");
	// 	subResult = true;
	//
	// 	tree = new DoubleThreadedBST<Integer>();
	// 	tree.insert(10);
	// 	tree.insert(15);
	// 	tree.insert(5);
	// 	tree.insert(6);
	// 	tree.insert(2);
	// 	tree.insert(14);
	// 	tree.insert(16);
	//
	// 	subResult = subResult & assertCustom(tree.inorderAscending(), "2,5,6,10,14,15,16");
	// 	subResult = subResult & assertCustom(tree.inorderDescending(), "16,15,14,10,6,5,2");
	//
	// 	// TEST CASE FOOTER
	// 	result = result & subResult;
	// 	printSubTestFooter(subResult);
	//
	//
	// 	// TEST CASE HEADER
	// 	printSubTest("Adding 100,90,95,80,91,79,170,160,150,200,165");
	// 	subResult = true;
	//
	// 	tree = createTree(new Integer[] {100,90,95,80,91,79,170,160,150,200,165});
	//
	// 	subResult = subResult & assertCustom(tree.inorderAscending(), "79,80,90,91,95,100,150,160,165,170,200");
	// 	subResult = subResult & assertCustom(tree.inorderDescending(), "200,170,165,160,150,100,95,91,90,80,79");
	//
	// 	// TEST CASE FOOTER
	// 	result = result & subResult;
	// 	printSubTestFooter(subResult);
	//
	// 	// FOOTER
	// 	printFooter(title, result);
	// 	return result;
	// 	// FOOTER END
	// }

}
