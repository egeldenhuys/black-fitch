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

public class tests_Stack extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & test_push_toString("testing push and toString");
		result = result & test_pop("testing pop()");
		result = result & test_clone("testing clone");
		// result = result & test_complex("test_complex");

		return result;
	}

	public boolean test_template(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_push_toString(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("adding positive elements to empty list");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4); // 4 3 2 1

		subResult = assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("adding negative elements to list");
		subResult = true;

		// TEST AND ASSERT
		list = new Stack();
		list.push(-5);
		list.push(1);
		list.push(2);
		list.push(-5);
		list.push(3);
		list.push(4);
		list.push(-5);

		subResult = assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_pop(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("pop elements when list is empty");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();
		int data = list.pop();

		subResult = assertCustom(list.toString(), "[]");
		subResult = subResult & assertCustom(data, -1);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("deleting elements until list is empty");
		subResult = true;

		// TEST AND ASSERT
		list = new Stack();

		list.push(1);
		list.push(2);
		list.push(3); // 3 2 1

		subResult = subResult & assertCustom(list.toString(), "[3,2,1]");

		subResult = subResult & assertCustom(list.pop(), 3);
		subResult = subResult & assertCustom(list.toString(), "[2,1]");


		subResult = subResult & assertCustom(list.pop(), 2);
		subResult = subResult & assertCustom(list.toString(), "[1]");


		subResult = subResult & assertCustom(list.pop(), 1);
		subResult = subResult & assertCustom(list.toString(), "[]");


		subResult = subResult & assertCustom(list.pop(), -1);
		subResult = subResult & assertCustom(list.toString(), "[]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASES END

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_clone(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("Assigning list to each other");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4); // 4 3 2 1

		Stack listEmpty = new Stack();

		Stack list2 = new Stack();
		list2.push(5);
		list2.push(6);
		list2.push(7);
		list2.push(8); // 8 7 6 5

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");
		subResult = subResult & assertCustom(list2.toString(), "[8,7,6,5]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[]");

		listEmpty = list.clone();

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[4,3,2,1]");

		list.pop();
		list.push(55);
		list.push(44);

		subResult = subResult & assertCustom(list.toString(), "[44,55,3,2,1]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[4,3,2,1]");

		listEmpty = list2.clone();

		subResult = subResult & assertCustom(list2.toString(), "[8,7,6,5]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[8,7,6,5]");

		listEmpty = listEmpty.clone();
		subResult = subResult & assertCustom(listEmpty.toString(), "[8,7,6,5]");

		listEmpty = new Stack();
		list2 = listEmpty.clone();

		subResult = subResult & assertCustom(list2.toString(), "[]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END

	}

	public boolean test_complex(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("Mixing operations");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();
		list.push(-1);
		list.push(-5);
		list.push(2);
		list.push(-31);
		list.push(3);
		list.push(-133);
		list.push(-13);
		list.push(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2]");

		subResult = subResult & assertCustom(list.pop(), 4);
		subResult = subResult & assertCustom(list.pop(), 3);
		subResult = subResult & assertCustom(list.pop(), 2);
		subResult = subResult & assertCustom(list.pop(), -1);

		list.push(5);
		subResult = subResult & assertCustom(list.toString(), "[5]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_peek(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END


		// TEST CASE HEADER
		printSubTest("Peek no element");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();

		subResult = subResult & assertCustom(list.peek(), -1);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("Peek one element");
		subResult = true;

		// TEST AND ASSERT
		list = new Stack();
		list.push(5);

		subResult = subResult & assertCustom(list.peek(), 5);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("peek many elements");
		subResult = true;

		// TEST AND ASSERT
		list = new Stack();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");
		subResult = subResult & assertCustom(list.peek(), 4);
		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_isEmpty(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END


		// TEST CASE HEADER
		printSubTest("no element");
		subResult = true;

		// TEST AND ASSERT
		Stack list = new Stack();

		subResult = subResult & assertCustom(list.isEmpty(), true);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("one element");
		subResult = true;

		// TEST AND ASSERT
		list = new Stack();
		list.push(5);

		subResult = subResult & assertCustom(list.isEmpty(), false);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("many elements");
		subResult = true;

		// TEST AND ASSERT
		list = new Stack();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");
		subResult = subResult & assertCustom(list.isEmpty(), false);
		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}
}
