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

public class BPlusTreeTest extends UnitTest{


	public boolean run() {

		boolean result = true;
		result = result & test_master("Testing sequential operations on a tree");

		return result;
	}


	public boolean test_template(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Creating a new BPlusTree with M = 5");
		// ==========================================================

		int trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("expected", "received", trace);


		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================


		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_master(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		String path = "";
		int m = 5;
		int trace = -1;
		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Creating a new BPlusTree with M = 5");
		// ==========================================================


		BPlusTree tree = new BPlusTree(m);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Inserting elements into root node until full with int and Integer");
		// ==========================================================

		/* Tree:
	         *NULL*
		*/

		int i = 50;
		path = tree.insertElement(i);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50]", path, trace);
		/* Tree:
			 [50]
		*/

		path = tree.search(33);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50],*NULL*", path, trace);

		Integer n = 70;
		path = tree.insertElement(n);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50]", path, trace);
		/* Tree:
			 [50][70]
		*/

		path = tree.search(33);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50][70],*NULL*", path, trace);

		n = 120;
		path = tree.insertElement(n);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50][70]", path, trace);
		/* Tree:
			 [50][70][120]
		*/

		path = tree.search(33);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50][70][120],*NULL*", path, trace);

		i = 10;
		path = tree.insertElement(i);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50][70][120]", path, trace);

		/* Tree:
			 [10][50][70][120]
		*/

		path = tree.search(33);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[10][50][70][120],*NULL*", path, trace);


		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Inserting element into root when full, should cause root split");
		// ==========================================================


		// Now we should get a root split
		path = tree.insertElement(20);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[10][50][70][120]", path, trace);

		/* Tree:
				 [50]
		[10][20]      [50][70][120]
		*/

		// Left
		path = tree.search(49);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50],[10][20],*NULL*", path, trace);

		// Right
		path = tree.search(51);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50],[50][70][120],*NULL*", path, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Inserting element into leaf until full");
		// ==========================================================

		path = tree.insertElement(75);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[50],[50][70][120]", path, trace);

		/* Tree:
		          [50]
		[10][20]      [50][70][75][120]
		*/

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Inserting element into full leaf, should share a key with left sibling and update separator key");
		// ==========================================================

		path = tree.insertElement(80);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[70],[70][75][80][120]", path, trace);

		// Right node full, so send keys to left brother
		/* Tree:
		              [70]
		[10][20][50]      [70][75][80][120]
		*/

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Inserting elements into leafs until full");
		// ==========================================================


		path = tree.insertElement(55);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[70],[10][20][50]", path, trace);

		/*
					     [70]
		[10][20][50][55]      [70][75][80][120]
		*/

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Inserting element when leaf and all siblings full, should cause split and send key copy to parent");
		// ==========================================================

		path = tree.insertElement(72);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[70],[70][75][80][120]", path, trace);

		/* Tree:
			              [70][75]
		[10][20][50][55]  [70][72]  [75][80][120]
		*/

		// Test all paths
		path = tree.search(51);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[70][75],[10][20][50][55],*NULL*", path, trace);

		path = tree.search(71);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[70][75],[70][72],*NULL*", path, trace);

		path = tree.search(76);
		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals("[70][75],[75][80][120],*NULL*", path, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// // =========================================================
		// // TEST CASE START										VVVV
		// subResult = start_test_case("Inserting element when leaf and all siblings full, should cause split and send key copy to parent");
		// // ==========================================================
		//
		// path = tree.insertElement(15);
		// subResult = subResult & assertEquals("[70],[70][75][80][120]", path);
		//
		// /*
		// 		         [55]               [75]
		// [10][15][20][50]      [55][70][72]      [75][80][120]
		// */

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}


}
