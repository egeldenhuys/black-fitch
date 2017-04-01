import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
import junit.framework.TestCase;


public class BPlusTreeTest extends TestCase{

	private BPlusTree tree;
	private int m;
	private String path;

	/*
	The int element passed as a parameter should be inserted in
	your B+-Tree.  The method should return a string representation
	of the path followed through the tree to find a node
	to insert into.  If the tree was empty, return the string
	representatio of the new root.
	*/
	@Test
	public void test_insert_into_root_only() {
		m = 5;
		tree = new BPlusTree(m);

		int i = 50;
		path = tree.insertElement(i);
		System.out.println(path);
		assertEquals("[50]", path);

		Integer n = 70;
		path = tree.insertElement(n);
		System.out.println(path);
		assertEquals("[50]", path);

		n = 120;
		path = tree.insertElement(n);
		System.out.println(path);
		assertEquals("[50][70]", path);

		i = 10;
		path = tree.insertElement(n);
		System.out.println(path);
		assertEquals("[50][70][120]", path);
		//"[10][50][70][120]"

	}

	@Ignore
	@Test // Assume not like this
	public void test_insert_into_root_only_assumed() {
		m = 5;
		tree = new BPlusTree(m);

		int i = 50;
		path = tree.insertElement(i);
		assertEquals("[50]", path);

		Integer n = 70;
		path = tree.insertElement(n);
		assertEquals("[50][70]", path);

		n = 120;
		path = tree.insertElement(n);
		assertEquals("[50][70][120]", path);

		i = 10;
		path = tree.insertElement(n);
		assertEquals("[10][50][70][120]", path);

	}

	@Test
	public void test_search() {
		m = 5;
		tree = new BPlusTree(m);

		path = tree.search(50);
		assertEquals("*NULL*", path);

		tree.insertElement(50);

		path = tree.search(50);
		assertEquals("[50]", path);

		tree.insertElement(70);

		tree.insertElement(120);
		tree.insertElement(10);

		path = tree.search(10);
		assertEquals("[10][50][70][120]", path);

		path = tree.search(69);
		assertEquals("[10][50][70][120],*NULL*", path);


	}

	@Test
	public void test_insert_root_split() {
		m = 5;
		tree = new BPlusTree(m);

		path = tree.insertElement(50);
		path = tree.insertElement(70);
		path = tree.insertElement(120);
		path = tree.insertElement(10);

		path = tree.search(10);
		assertEquals("[10][50][70][120]", path);

		// Now we should get a root split

		path = tree.insertElement(20);
		assertEquals("[10][50][70][120]", path);

		// Left
		path = tree.search(49);
		assertEquals("[50],[10][20],*NULL*", path);

		// Right
		path = tree.search(51);
		assertEquals("[50],[50][70][120],*NULL*", path);

	}

	@Test
	public void test_node_split() {

		m = 5;
		tree = new BPlusTree(m);

		path = tree.insertElement(50);
		path = tree.insertElement(70);
		path = tree.insertElement(120);
		path = tree.insertElement(10);
		// Now we should get a root split
		path = tree.insertElement(20);

		// Left
		path = tree.search(49);
		assertEquals("[50],[10][20],*NULL*", path);

		// Right
		path = tree.search(51);
		assertEquals("[50],[50][70][120],*NULL*", path);

		/*
		         [50]
		[10][20]      [50][70][120]
		*/

		// Now insert nodes until split

		path = tree.insertElement(75);
		assertEquals("[50],[50][70][120]", path);

		/*
		          [50]
		[10][20]      [50][70][75][120]
		*/

		path = tree.insertElement(80);
		assertEquals("[70],[70][75][80][120]", path);

		// Right node full, so send keys to left brother
		/*
		              [70]
		[10][20][50]      [70][75][80][120]
		*/

		path = tree.insertElement(55);
		assertEquals("[70],[10][20][50]", path);

		/*
					     [70]
		[10][20][50][55]      [70][75][80][120]
		*/

		path = tree.insertElement(72);
		assertEquals("[70],[70][75][80][120]", path);

		/*
		70,72,75,80,120 FULL
		LEFT is also FULL
		!RIGHT
		SPLIt in middle (75), Send 75 to parent and make a baby

					      [70]
		[10][20][50][55]      [70][72][75][80][120]

			              [70]         [75]
		[10][20][50][55]      [70][72]      [75][80][120]
		*/


		path = tree.search(51);
		assertEquals("[70][75],[10][20][50][55],*NULL*", path);


		path = tree.search(71);
		assertEquals("[70][75],[70][72],*NULL*", path);

		path = tree.search(76);
		assertEquals("[70][75],[75][80][120],*NULL*", path);

		path = tree.insertElement(15);
		assertEquals("[70],[70][75][80][120]", path);

		/*
				         [55]               [75]
		[10][15][20][50]      [55][70][72]      [75][80][120]
		*/
	}
}
