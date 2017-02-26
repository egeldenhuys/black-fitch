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

public class tests_CircularList extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & test_addToFront_toString("testing CircularList::addToFront() and toString()");
		result = result & test_deleteFromBack("testing deleteFromBack()");
		result = result & test_clone("testing clone");
		result = result & test_complex("test_complex");

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
		CircularList list = new CircularList();
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);
		list.addToFront(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_addToFront_toString(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("adding positive elements to empty list");
		subResult = true;

		// TEST AND ASSERT
		CircularList list = new CircularList();
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);
		list.addToFront(4);

		subResult = assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("adding negative elements to list");
		subResult = true;

		// TEST AND ASSERT
		list = new CircularList();
		list.addToFront(-5);
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(-5);
		list.addToFront(3);
		list.addToFront(4);
		list.addToFront(-5);

		subResult = assertCustom(list.toString(), "[4,3,2,1]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);


		// TEST CASES END

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}

	public boolean test_deleteFromBack(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("deleting elements when list is empty");
		subResult = true;

		// TEST AND ASSERT
		CircularList list = new CircularList();
		int data = list.deleteFromBack();

		subResult = assertCustom(list.toString(), "[]");
		subResult = subResult & assertCustom(data, -1);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("deleting elements until list is empty");
		subResult = true;

		// TEST AND ASSERT
		list = new CircularList();

		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3); // 3 2 1

		subResult = subResult & assertCustom(list.toString(), "[3,2,1]");

		data = list.deleteFromBack();
		subResult = subResult & assertCustom(data, 1);
		subResult = subResult & assertCustom(list.toString(), "[3,2]");

		data = list.deleteFromBack();
		subResult = subResult & assertCustom(data, 2);
		subResult = subResult & assertCustom(list.toString(), "[3]");

		data = list.deleteFromBack();
		subResult = subResult & assertCustom(data, 3);
		subResult = subResult & assertCustom(list.toString(), "[]");

		data = list.deleteFromBack();
		subResult = subResult & assertCustom(data, -1);
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
		CircularList list = new CircularList();
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);
		list.addToFront(4); // 4 3 2 1

		CircularList listEmpty = new CircularList();

		CircularList list2 = new CircularList();
		list2.addToFront(5);
		list2.addToFront(6);
		list2.addToFront(7);
		list2.addToFront(8); // 8 7 6 5

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");
		subResult = subResult & assertCustom(list2.toString(), "[8,7,6,5]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[]");

		listEmpty = list.clone();

		subResult = subResult & assertCustom(list.toString(), "[4,3,2,1]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[4,3,2,1]");

		list.deleteFromBack();
		list.addToFront(55);
		list.addToFront(44);

		subResult = subResult & assertCustom(list.toString(), "[44,55,4,3,2]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[4,3,2,1]");

		listEmpty = list2.clone();

		subResult = subResult & assertCustom(list2.toString(), "[8,7,6,5]");
		subResult = subResult & assertCustom(listEmpty.toString(), "[8,7,6,5]");

		listEmpty = listEmpty.clone();
		subResult = subResult & assertCustom(listEmpty.toString(), "[8,7,6,5]");

		listEmpty = new CircularList();
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
		CircularList list = new CircularList();
		list.addToFront(-1);
		list.addToFront(-5);
		list.addToFront(2);
		list.addToFront(-31);
		list.addToFront(3);
		list.addToFront(-133);
		list.addToFront(-13);
		list.addToFront(4);

		subResult = subResult & assertCustom(list.toString(), "[4,3,2]");

		subResult = subResult & assertCustom(list.deleteFromBack(), 2);
		subResult = subResult & assertCustom(list.deleteFromBack(), 3);
		subResult = subResult & assertCustom(list.deleteFromBack(), 4);
		subResult = subResult & assertCustom(list.deleteFromBack(), -1);

		list.addToFront(5);
		subResult = subResult & assertCustom(list.toString(), "[5]");

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
	}
}
