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

public class TrieTest extends UnitTest{


	public boolean run() {

		boolean result = true;
		result = result & test_trie_operations("Testing Sequential Trie Operations");

		return result;
	}

	/**
	* Prints all the nodes in the tree in a breadth-first fashion.
	* Source: University of Pretoria
	* Modified to return a String instead of printing to console.
	*/
	public String getTriePrint(Trie trie) {
		String result = "";

		Queue queue = new Queue(10);

		Node n = trie.root;
		if (n != null) {
			queue.enq(n);
			while (!queue.isEmpty()){
				n = queue.deq();
				result += trie.nodeToString(n);

				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null)
						queue.enq(n.ptrs[k]);
				}

				if (!queue.isEmpty()) {
					result += "\n";
				}
			}
		}

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

	public boolean test_trie_operations(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		String received = "";
		String expected = "";
		int trace = -1;

		// HEADER END

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Creating the given Trie");
		// ==========================================================
		char[] letters = {'Q', 'W', 'E', 'R', 'T', 'Y'};
		Trie trie = new Trie(letters);

		trie.insert("WWWWW");
		trie.insert("W");
		trie.insert("WRETE");
		trie.insert("WRWW");
		trie.insert("RTE");
		trie.insert("RTRRR");
		trie.insert("RTWE");
		trie.insert("RTWW");
		trie.insert("TTT");
		trie.insert("TYT");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,1)  (E,1)  (R,1)  (T,0)  (Y,0)  \nTTT\nTYT\nWRWW\nWRETE\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \nRTE\nRTRRR\nRTWW\nRTWE";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Deleting RTRRR, THEN [WER] becomes [WE]");
		// ==========================================================

		trie.delete("RTRRR");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \nTTT\nTYT\nWRWW\nWRETE\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \nRTE\nRTWW\nRTWE";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Deleting WRETE. THEN [WE] collapses AND [WRW] becomes child of [#WR] at [R]");
		// ==========================================================

		trie.delete("WRETE");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\nWRWW\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \nTTT\nTYT\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \nRTE\nRTWW\nRTWE";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Deleting RTE. Should set [E] in [WE(R)] to null");
		// ==========================================================

		trie.delete("RTE");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\nWRWW\n(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,0)  (Y,0)  \nTTT\nTYT\n(#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)  \nRTWW\nRTWE";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Deleting RTWE. [RTWW] should become child of [WRT] in [R]");
		// ==========================================================

		trie.delete("RTWE");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \nRTWW\n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\nWRWW\nTTT\nTYT";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("Deleting RTWW. [R] Should be removed from root and no crash");
		// ==========================================================

		trie.delete("RTWW");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\nWRWW\nTTT\nTYT";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN deleting valid word that does not exist in tree, THEN nothing happens");
		// ==========================================================

		trie.delete("QWE");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\nWRWW\nTTT\nTYT";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN deleting invalid, THEN nothing happens");
		// ==========================================================

		trie.delete("LOLCAT");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nW\nWWWWW\nWRWW\nTTT\nTYT";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN Deleting W, THEN [#] in [#WR] should be set to NULL");
		// ==========================================================

		trie.delete("W");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \n(#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)  \nWWWWW\nWRWW\nTTT\nTYT";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN Deleting [TTT], THEN [TYT] should become child of [WT] in [T]");
		// ==========================================================

		trie.delete("TTT");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,1)  (Y,0)  \n(#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)  \nTYT\nWWWWW\nWRWW";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN Deleting [WWWWW], THEN [WRWW] should become child of [WT] in position [W]");
		// ==========================================================

		trie.delete("WWWWW");

		received = getTriePrint(trie);
		expected = "(#,0)  (Q,0)  (W,1)  (E,0)  (R,0)  (T,1)  (Y,0)  \nWRWW\nTYT";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN Deleting [TYT] (the second last leaf), THEN the last leaf becomes the root");
		// ==========================================================

		trie.delete("TYT");

		received = getTriePrint(trie);
		expected = "WRWW";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN Deleting [WRWW] (the root), THEN [W] should become NULL");
		// ==========================================================

		trie.delete("WRWW");

		received = getTriePrint(trie);
		expected = "";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================
		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN trying to delete on an empty tree, THEN no crash");
		// ==========================================================

		trie.delete("Q");

		received = getTriePrint(trie);
		expected = "";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================

		// TEST CASE START										VVVV
		subResult = start_test_case("WHEN Inserting QWER, then we only have on leaf with key QWER");
		// ==========================================================

		trie.insert("QWER");

		received = getTriePrint(trie);
		expected = "QWER";

		trace = Thread.currentThread().getStackTrace()[1].getLineNumber();
		subResult = subResult & assertEquals(expected, received, trace);

		// =========================================================
		result = end_test_case(result, subResult);
		// TEST CASE END										^^^^
		// =========================================================



		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}


}
