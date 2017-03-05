public class MockDoubleThreadedBST<T extends Comparable<? super T>>
{

	T[] insert = {};
	String inorderAscending = "-2";
	String inorderDescending = "-2";
	//int getNumberOfNodes = -2;
	T getRoot = -2;
	int getHeight = -2;
	//int countLeftThreads = -2;
	//int countRightThreads = -2;


	public T getRoot() {
		return insert[0];

	}

	public int getNumberOfNodes() {
		return insert.length();
	}

	public String inorderAscending() {
		T[] tmp = insert.sort();

		for ()

	}

	public String inorderDescending() {
		T[] tmp = Arrays.sort(insert, Collections.reverseOrder());

		return tmp;
	}

	public boolean contains(T elem) {

	}

	/*
	WORKFLOW
		- Construct a mock tree by hand
		- Construct a real tree from the input array in the Mock tree
		- Compare the Mock output with the real output

		But what about Delete? Can

	OPERATIONS
	insert | Test by calling insert using the internal array
	delete | Has to modify the mock variables
			delete(6) - If exists need to pop from our array and recalculate the internal mock values

	TESTING MEMBERS
	delete_values[] Array of values to be deleted
	delete_mocks[] array of new mocks represnting the structures after the corresponding deletes

		TESTING DELETE
			Get the delete_values
			call delete on the real tree
			call delete on our tree
				- Mock will apply the mock tree from the mock array

	OUTPUT
	inorderAscending
	inorderDescending
	getNumberOfNodes
	getRoot
	get height
	countLeftThreads
	countRightThreads
	contains

	TESTING

	Construct mock objects

	MockDoubleThreadedBST<Integer> mock_1 = new MockDoubleThreadedBST<Integer>();
	mock_1.insert(new Integer[] {1,2,3,4,5}); // Set the insert array. Special Cases here
	mock_1.inorderAscending = "1,2,3,4,5";
	mock_1.inorderDescending = "5,4,3,2,1"
	etc...

	What about Delete?
	// Create the as it was mock before the delete
	mock_delete_1.insert(new Integer[] {1,2,3,4,5});
	...

	call delete on the real
	tree.delete(4);

	Construct a new Mock as it is after the delete

	mock_delete_1.delete()

	*/

}
