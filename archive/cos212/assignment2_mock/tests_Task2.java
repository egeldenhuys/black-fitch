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

import java.util.Arrays;

import java.util.*;

public class tests_Task2 extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & run_mock_tests("Running Mock Tests");

		return result;
	}

	public <T extends Comparable<? super T>> DoubleThreadedAVL<T> createTree(T[] arr) {


		DoubleThreadedAVL<T> tree = new DoubleThreadedAVL<T>();

		for (int i = 0; i < arr.length; i++) {
			tree.insert(arr[i]);
		}

		return tree;
	}

	public <T extends Comparable<? super T>> void exploreTreeRec(DTNode<T> node) {

		if (node != null) {
			//String result = "[" + node.data + "]" + "[L: " + ((node.left==null) ? "null" : node.left.data) + "](" + node.hasLeftThread + ")[R: " + ((node.right==null) ? "null" : node.right.data) + "]" + "(" + node.hasRightThread + ")" + "[Balance " + node.balance +"]";
			String result = "(" + node.hasLeftThread + ")" +
			((node.left==null) ? "null" : node.left.data) +
			" <- " + node.data + " -> " +
			((node.right==null) ? "null" : node.right.data) +
			"(" + node.hasRightThread + ")" +
			" [" + node.balance + "]";

			//"[" + node.data + "]" + "[L: " + ((node.left==null) ? "null" : node.left.data) + "](" + node.hasLeftThread + ")[R: " + ((node.right==null) ? "null" : node.right.data) + "]" + "(" + node.hasRightThread + ")" + "[Balance " + node.balance +"]";

			System.out.println(result);
		}

		if (!node.hasLeftThread && node.left != null) {
			exploreTreeRec(node.left);
		}

		if (!node.hasRightThread && node.right != null) {
			exploreTreeRec(node.right);
		}
	}

	public boolean run_mock_tests(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		ArrayList<MockDoubleThreadedAVL<Integer>> mockList = new ArrayList<MockDoubleThreadedAVL<Integer>>();

		MockDoubleThreadedAVL<Integer> tmpMock = new MockDoubleThreadedAVL<Integer>();
		tmpMock.insert = new Integer[]{1,2,3,4,5};
		tmpMock.name = "1,2,3,4,5";
		tmpMock.getHeight = 5;
		mockList.add(tmpMock);

		tmpMock = new MockDoubleThreadedAVL<Integer>();
		tmpMock.insert = new Integer[]{5,4,3,2,1};
		tmpMock.name = "5,4,3,2,1";
		tmpMock.getHeight = 5;
		mockList.add(tmpMock);

		tmpMock = new MockDoubleThreadedAVL<Integer>();
		tmpMock.insert = new Integer[]{1};
		tmpMock.name = "Single Node";
		tmpMock.getHeight = 1;
		mockList.add(tmpMock);

		for (int i = 0; i < mockList.size(); i++) {
			// TEST CASE HEADER
			printSubTest(mockList.get(i).name);
			subResult = true;

			DoubleThreadedAVL<Integer> real = new DoubleThreadedAVL<Integer>();

			// Create real tree
			for (int j = 0; j < mockList.get(i).insert.length; j++) {
				System.out.println("INSERT: " + mockList.get(i).insert[j]);

				real.insert(mockList.get(i).insert[j]);

				System.out.println("STRUCTURE: " );
				exploreTreeRec(real.getRoot());
			}

			result = result & assertCustom(real.getHeight(), mockList.get(i).getHeight());
			result = result & assertCustom(real.inorderAscending(), mockList.get(i).inorderAscending());
			result = result & assertCustom(real.inorderDescending(), mockList.get(i).inorderDescending());

			// TEST CASE FOOTER
			result = result & subResult;
			printSubTestFooter(subResult);
		}


		// Create a bunch of mock trees and place them in an array
		// Loop through the array
		/*
			INSERT
			Get the insert array from the mock
			create a real using the insert array from the mock
			Compare all the Mock attributes with the real tree

			DELETE
			Create a real using the mock insert array
			Get the array of deletes from the Mock
			Call delete on the real and the mock using the values from the array
				When delete is called on the mock, change its internal state to match
				Mock will have an array of states to be applied after the corresponding delete
		*/

		/*
		attributes

		*/

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}


}
