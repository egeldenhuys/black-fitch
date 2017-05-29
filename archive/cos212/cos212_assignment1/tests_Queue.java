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

public class tests_Queue extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & test_enqueu_toString("testing enqueue and toString");
		result = result & test_dequeue("testing dequeue()");
		result = result & test_clone("testing clone");
		result = result & test_complex("test_complex");
		result = result & test_front("test_front");
		result = result & test_isEmpty("test_isEmpty");

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
		Queue list = new Queue();
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_enqueu_toString(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("adding positive elements to empty list");
		subResult = true;

		// TEST AND ASSERT
		Queue list = new Queue();
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		subResult = assertCustom(list.toString(), "[1,2,3,4]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("adding negative elements to list");
		subResult = true;

		// TEST AND ASSERT
		list = new Queue();
		list.enqueue(-5);
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(-5);
		list.enqueue(3);
		list.enqueue(4);
		list.enqueue(-5);

		subResult = assertCustom(list.toString(), "[1,2,3,4]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_dequeue(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("dequeue elements when list is empty");
		subResult = true;

		// TEST AND ASSERT
		Queue list = new Queue();
		int data = list.dequeue();

		subResult = assertCustom(list.toString(), "[]");
		subResult = subResult & assertCustom(data, -1);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("deleting elements until list is empty");
		subResult = true;

		// TEST AND ASSERT
		list = new Queue();

		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3); // 1 2 3

		subResult = subResult & assertCustom(list.toString(), "[1,2,3]");

		subResult = subResult & assertCustom(list.dequeue(), 1);
		subResult = subResult & assertCustom(list.toString(), "[2,3]");


		subResult = subResult & assertCustom(list.dequeue(), 2);
		subResult = subResult & assertCustom(list.toString(), "[3]");


		subResult = subResult & assertCustom(list.dequeue(), 3);
		subResult = subResult & assertCustom(list.toString(), "[]");


		subResult = subResult & assertCustom(list.dequeue(), -1);
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
		Queue list = new Queue();
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		Queue listEmpty = new Queue();

		Queue list2 = new Queue();
		list2.enqueue(5);
		list2.enqueue(6);
		list2.enqueue(7);
		list2.enqueue(8); // 5 6 7 8

		subResult = subResult & assertCustom(list.toString(), "[1,2,3,4]");
		subResult = subResult & assertCustom(list2.toString(), "[5,6,7,8]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[]");

		listEmpty = list.clone();

		subResult = subResult & assertCustom(list.toString(), "[1,2,3,4]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[1,2,3,4]");

		list.dequeue();
		list.enqueue(55);
		list.enqueue(44);

		subResult = subResult & assertCustom(list.toString(), "[2,3,4,55,44]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[1,2,3,4]");

		listEmpty = list2.clone();

		subResult = subResult & assertCustom(list2.toString(), "[5,6,7,8]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[5,6,7,8]");

		listEmpty = listEmpty.clone();
		subResult = subResult & assertCustom(listEmpty.toString(), "[5,6,7,8]");

		listEmpty = new Queue();
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
		Queue list = new Queue();
		list.enqueue(-1);
		list.enqueue(-5);
		list.enqueue(2);
		list.enqueue(-31);
		list.enqueue(3);
		list.enqueue(-133);
		list.enqueue(-13);
		list.enqueue(4);

		subResult = subResult & assertCustom(list.toString(), "[2,3,4]");

		subResult = subResult & assertCustom(list.dequeue(), 2);
		subResult = subResult & assertCustom(list.dequeue(), 3);
		subResult = subResult & assertCustom(list.dequeue(), 4);
		subResult = subResult & assertCustom(list.dequeue(), -1);

		list.enqueue(5);
		subResult = subResult & assertCustom(list.toString(), "[5]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_front(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END


		// TEST CASE HEADER
		printSubTest("front no element");
		subResult = true;

		// TEST AND ASSERT
		Queue list = new Queue();

		subResult = subResult & assertCustom(list.front(), -1);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("front one element");
		subResult = true;

		// TEST AND ASSERT
		list = new Queue();
		list.enqueue(5);

		subResult = subResult & assertCustom(list.front(), 5);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("front many elements");
		subResult = true;

		// TEST AND ASSERT
		list = new Queue();
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		subResult = subResult & assertCustom(list.toString(), "[1,2,3,4]");
		subResult = subResult & assertCustom(list.front(), 1);
		subResult = subResult & assertCustom(list.toString(), "[1,2,3,4]");

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
		Queue list = new Queue();

		subResult = subResult & assertCustom(list.isEmpty(), true);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("one element");
		subResult = true;

		// TEST AND ASSERT
		list = new Queue();
		list.enqueue(5);

		subResult = subResult & assertCustom(list.isEmpty(), false);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASE HEADER
		printSubTest("many elements");
		subResult = true;

		// TEST AND ASSERT
		list = new Queue();
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		subResult = subResult & assertCustom(list.toString(), "[1,2,3,4]");
		subResult = subResult & assertCustom(list.isEmpty(), false);
		subResult = subResult & assertCustom(list.toString(), "[1,2,3,4]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}
}
