/**
* @Author: Thomas Scholtz <thomas>
* @Date:   2017-03-04T13:31:50+02:00
* @Email:  thomas@quantum-sicarius.za.net
* @Last modified by:   thomas
* @Last modified time: 2017-03-04T17:05:28+02:00
* @License: Attribution-NonCommercial-ShareAlike 4.0 International
*/

public class tests_DoubleThreadedBST extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & test_insert_double_threaded_bst("testing insert to double threaded BST");

		return result;
	}


	/*public boolean test_template(String title) {
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
}*/

public boolean test_insert_double_threaded_bst(String title) {
		// HEADER
		printHeader(title);
		boolean result = true;
		boolean subResult = true;
		// HEADER END

		// TEST CASE HEADER
		printSubTest("adding elements to binary tree");
		subResult = true;

		// TEST AND ASSERT
		DoubleThreadedBST<Integer> tree = new DoubleThreadedBST<Integer>();
    tree.insert(1);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);

    /*
    1
      \
        2
          \
            3
              \
                4
    */
    DTNode<Integer> root = tree.getRoot();
    subResult = assertCustom(root.data, 1);
		subResult = assertCustom(root.right.data, 2);
    subResult = assertCustom(root.right.right.data, 3);
    subResult = assertCustom(root.right.right.right.data, 4);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// TEST CASE HEADER
		printSubTest("adding the same element again should fail");
		subResult = true;

		// TEST AND ASSERT
		subResult = assertCustom(tree.insert(1), false);
    subResult = assertCustom(tree.insert(2), false);
    subResult = assertCustom(tree.insert(3), false);
    subResult = assertCustom(tree.insert(4), false);

		// TEST CASE FOOTER
		result = result & subResult;
		printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
  }
}
