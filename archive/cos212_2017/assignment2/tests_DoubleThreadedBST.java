/**
* @Author: Thomas Scholtz <thomas>
* @Date:   2017-03-04T13:31:50+02:00
* @Email:  thomas@quantum-sicarius.za.net
* @Last modified by:   thomas
* @Last modified time: 2017-03-12T15:59:21+02:00
* @License: Attribution-NonCommercial-ShareAlike 4.0 International
*/

public class tests_DoubleThreadedBST extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & test_insert_double_threaded_bst("testing insert to double threaded BST");
    result = result & test_threads_double_threaded_bst("testing threads");
    result = result & test_inorderAscending("testing inorderAscending");
    result = result & test_inorderDecending("testing inorderAscending");
    result = result & test_count_left_threads("testing count left threads");
    result = result & test_count_right_threads("testing count right threads");
    result = result & test_count_nodes("testing count of nodes");
    result = result & test_tree_height("testing height of tree");
		result = result & test_tree_delete("testing delete element");
		result = result & test_tree_clone("testing clone tree");
		result = result & test_tree_copy("testing copy tree");
		return result;
	}

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

    // TEST CASE HEADER
    printSubTest("adding strange configuration of elements to binary tree");
    subResult = true;

    // TEST AND ASSERT
    tree = new DoubleThreadedBST<Integer>();
    tree.insert(5);
    tree.insert(8);
    tree.insert(6);
    tree.insert(2);
    tree.insert(4);
    tree.insert(1);

    /*
            5
          /   \
        2       8
      /   \    /
    1       4 6

    */
    root = tree.getRoot();
    subResult = assertCustom(root.data, 5);
    subResult = assertCustom(root.right.data, 8);
    subResult = assertCustom(root.right.left.data, 6);
    subResult = assertCustom(root.left.data, 2);
    subResult = assertCustom(root.left.left.data, 1);
    subResult = assertCustom(root.left.right.data, 4);

    // TEST CASE FOOTER
    result = result & subResult;
    printSubTestFooter(subResult);

		// FOOTER
		printFooter(title, result);
		return result;
		// FOOTER END
  }

  public boolean test_threads_double_threaded_bst(String title) {
  		// HEADER
  		printHeader(title);
  		boolean result = true;
  		boolean subResult = true;
  		// HEADER END

  		// TEST CASE HEADER
  		printSubTest("adding elements to binary tree should establish threads [right handed threads]");
  		subResult = true;

  		// TEST AND ASSERT
  		DoubleThreadedBST<Integer> tree = new DoubleThreadedBST<Integer>();
      tree.insert(4);
      tree.insert(3);
      tree.insert(2);
      tree.insert(1);

      /*
                  4
                /
              3
            /
          2
        /
      1
      */
      DTNode<Integer> root = tree.getRoot();
      subResult = assertCustom(root.hasRightThread, false);
  		subResult = assertCustom(root.left.hasRightThread, true);
      subResult = assertCustom(root.left.left.hasRightThread, true);
      subResult = assertCustom(root.left.left.left.hasRightThread, true);

  		// TEST CASE FOOTER
  		result = result & subResult;
  		printSubTestFooter(subResult);

      // TEST CASE HEADER
      printSubTest("adding elements to binary tree should establish threads [left handed threads]");
  		subResult = true;

  		// TEST AND ASSERT
  		tree = new DoubleThreadedBST<Integer>();
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
      root = tree.getRoot();
      subResult = assertCustom(root.hasLeftThread, false);
  		subResult = assertCustom(root.right.hasLeftThread, true);
      subResult = assertCustom(root.right.right.hasLeftThread, true);
      subResult = assertCustom(root.right.right.right.hasLeftThread, true);

  		// TEST CASE FOOTER
  		result = result & subResult;
  		printSubTestFooter(subResult);

      // TEST CASE HEADER
      printSubTest("adding strange configuration of elements to binary tree [left and right threads]");
      subResult = true;

      // TEST AND ASSERT
      tree = new DoubleThreadedBST<Integer>();
      tree.insert(5);
      tree.insert(8);
      tree.insert(6);
      tree.insert(2);
      tree.insert(4);
      tree.insert(1);

      /*
              5
            /   \
          2       8
        /   \    /
      1       4 6

      */
      // In total:
      // 3 right threads.
      // 2 left threads.

      root = tree.getRoot();
      // Node 5 should not have any threads.
      subResult = assertCustom(root.hasRightThread, false);
      subResult = assertCustom(root.hasLeftThread, false);

      // Node 8 should not have any threads.
      subResult = assertCustom(root.right.hasRightThread, false);
      subResult = assertCustom(root.right.hasLeftThread, false);

      // Node 6 should thread back to 8 [right].
      subResult = assertCustom(root.right.left.hasRightThread, true);
      subResult = assertCustom(root.right.left.right.data, 8);
      // Node 6 should thread back to 5 [left].
      subResult = assertCustom(root.right.left.hasLeftThread, true);
      subResult = assertCustom(root.right.left.left.data, 5);

      // Node 2 should not have any threads.
      subResult = assertCustom(root.left.hasRightThread, false);
      subResult = assertCustom(root.left.hasLeftThread, false);

      // Node 1 should have thread back to 2 [right].
      subResult = assertCustom(root.left.left.hasRightThread, true);
      subResult = assertCustom(root.left.left.right.data, 2);
      // Node 1 should not have a left thread.
      subResult = assertCustom(root.left.left.hasLeftThread, false);

      // Node 4 should have thread back to 5 [right].
      subResult = assertCustom(root.left.right.hasRightThread, true);
      subResult = assertCustom(root.left.right.right.data, 5);
      // Node 4 should have thread back to 2 [left].
      subResult = assertCustom(root.left.right.hasLeftThread, true);
      subResult = assertCustom(root.left.right.left.data, 2);

      // TEST CASE FOOTER
      result = result & subResult;
      printSubTestFooter(subResult);

  		// FOOTER
  		printFooter(title, result);
  		return result;
  		// FOOTER END
    }

    public boolean test_inorderAscending(String title) {
    		// HEADER
    		printHeader(title);
    		boolean result = true;
    		boolean subResult = true;
    		// HEADER END

    		// TEST CASE HEADER
    		printSubTest("inorderAscending");
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
        subResult = assertCustom(tree.inorderAscending().toString(), "1,2,3,4");

    		// TEST CASE FOOTER
    		result = result & subResult;
    		printSubTestFooter(subResult);

        // TEST CASE HEADER
        printSubTest("adding strange configuration of elements to binary tree [inorderAscending]");
        subResult = true;

        // TEST AND ASSERT
        tree = new DoubleThreadedBST<Integer>();
        tree.insert(5);
        tree.insert(8);
        tree.insert(6);
        tree.insert(2);
        tree.insert(4);
        tree.insert(1);

        /*
                5
              /   \
            2       8
          /   \    /
        1       4 6

        */
        subResult = assertCustom(tree.inorderAscending().toString(), "1,2,4,5,6,8");

        // TEST CASE FOOTER
        result = result & subResult;
        printSubTestFooter(subResult);

    		// FOOTER
    		printFooter(title, result);
    		return result;
    		// FOOTER END
      }

      public boolean test_inorderDecending(String title) {
      		// HEADER
      		printHeader(title);
      		boolean result = true;
      		boolean subResult = true;
      		// HEADER END

      		// TEST CASE HEADER
      		printSubTest("inorderDescending");
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
          subResult = assertCustom(tree.inorderDescending().toString(), "4,3,2,1");

      		// TEST CASE FOOTER
      		result = result & subResult;
      		printSubTestFooter(subResult);

          // TEST CASE HEADER
          printSubTest("adding strange configuration of elements to binary tree [inorderDescending]");
          subResult = true;

          // TEST AND ASSERT
          tree = new DoubleThreadedBST<Integer>();
          tree.insert(5);
          tree.insert(8);
          tree.insert(6);
          tree.insert(2);
          tree.insert(4);
          tree.insert(1);

          /*
                  5
                /   \
              2       8
            /   \    /
          1       4 6

          */
          subResult = assertCustom(tree.inorderDescending().toString(), "8,6,5,4,2,1");

          // TEST CASE FOOTER
          result = result & subResult;
          printSubTestFooter(subResult);

      		// FOOTER
      		printFooter(title, result);
      		return result;
      		// FOOTER END
        }

        public boolean test_count_right_threads(String title) {
        		// HEADER
        		printHeader(title);
        		boolean result = true;
        		boolean subResult = true;
        		// HEADER END

        		// TEST CASE HEADER
        		printSubTest("count right threads");
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
            subResult = assertCustom(tree.countRightThreads(), 0);

        		// TEST CASE FOOTER
        		result = result & subResult;
        		printSubTestFooter(subResult);

            // TEST CASE HEADER
            printSubTest("adding strange configuration of elements to binary tree [count right threads]");
            subResult = true;

            // TEST AND ASSERT
            tree = new DoubleThreadedBST<Integer>();
            tree.insert(5);
            tree.insert(8);
            tree.insert(6);
            tree.insert(2);
            tree.insert(4);
            tree.insert(1);

            /*
                    5
                  /   \
                2       8
              /   \    /
            1       4 6

            */
            subResult = assertCustom(tree.countRightThreads(), 3);

            // TEST CASE FOOTER
            result = result & subResult;
            printSubTestFooter(subResult);

        		// FOOTER
        		printFooter(title, result);
        		return result;
        		// FOOTER END
          }

          public boolean test_count_left_threads(String title) {
              // HEADER
              printHeader(title);
              boolean result = true;
              boolean subResult = true;
              // HEADER END

              // TEST CASE HEADER
              printSubTest("count left threads");
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
              subResult = assertCustom(tree.countLeftThreads(), 3);

              // TEST CASE FOOTER
              result = result & subResult;
              printSubTestFooter(subResult);

              // TEST CASE HEADER
              printSubTest("adding strange configuration of elements to binary tree [count right threads]");
              subResult = true;

              // TEST AND ASSERT
              tree = new DoubleThreadedBST<Integer>();
              tree.insert(5);
              tree.insert(8);
              tree.insert(6);
              tree.insert(2);
              tree.insert(4);
              tree.insert(1);

              /*
                      5
                    /   \
                  2       8
                /   \    /
              1       4 6

              */
              subResult = assertCustom(tree.countLeftThreads(), 2);

              // TEST CASE FOOTER
              result = result & subResult;
              printSubTestFooter(subResult);

              // FOOTER
              printFooter(title, result);
              return result;
              // FOOTER END
            }

            public boolean test_count_nodes(String title) {
                // HEADER
                printHeader(title);
                boolean result = true;
                boolean subResult = true;
                // HEADER END

                // TEST CASE HEADER
                printSubTest("count number of nodes");
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
                subResult = assertCustom(tree.getNumberOfNodes(), 4);

                // TEST CASE FOOTER
                result = result & subResult;
                printSubTestFooter(subResult);

                // TEST CASE HEADER
                printSubTest("adding strange configuration of elements to binary tree [count right threads]");
                subResult = true;

                // TEST AND ASSERT
                tree = new DoubleThreadedBST<Integer>();
                tree.insert(5);
                tree.insert(8);
                tree.insert(6);
                tree.insert(2);
                tree.insert(4);
                tree.insert(1);

                /*
                        5
                      /   \
                    2       8
                  /   \    /
                1       4 6

                */
                subResult = assertCustom(tree.getNumberOfNodes(), 6);

                // TEST CASE FOOTER
                result = result & subResult;
                printSubTestFooter(subResult);

                // FOOTER
                printFooter(title, result);
                return result;
                // FOOTER END
              }

              public boolean test_tree_height(String title) {
                  // HEADER
                  printHeader(title);
                  boolean result = true;
                  boolean subResult = true;
                  // HEADER END

                  // TEST CASE HEADER
                  printSubTest("get the height of the tree");
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
                  subResult = assertCustom(tree.getHeight(), 4);

                  // TEST CASE FOOTER
                  result = result & subResult;
                  printSubTestFooter(subResult);

                  // TEST CASE HEADER
                  printSubTest("adding strange configuration of elements to binary tree [count right threads]");
                  subResult = true;

                  // TEST AND ASSERT
                  tree = new DoubleThreadedBST<Integer>();
                  tree.insert(5);
                  tree.insert(8);
                  tree.insert(6);
                  tree.insert(2);
                  tree.insert(4);
                  tree.insert(1);

                  /*
                          5
                        /   \
                      2       8
                    /   \    /
                  1       4 6

                  */
                  subResult = assertCustom(tree.getHeight(), 3);

                  // TEST CASE FOOTER
                  result = result & subResult;
                  printSubTestFooter(subResult);

                  // FOOTER
                  printFooter(title, result);
                  return result;
                  // FOOTER END
                }

								public boolean test_tree_delete(String title) {
	                  // HEADER
	                  printHeader(title);
	                  boolean result = true;
	                  boolean subResult = true;
	                  // HEADER END

	                  // TEST CASE HEADER
	                  printSubTest("delete an element of the tree");
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
	                  subResult = assertCustom(tree.delete(1), true);
										subResult = assertCustom(tree.delete(3), true);
										subResult = assertCustom(tree.delete(9), false);

										/*
	                  2
	                    \
	                      4
	                  */

										DTNode<Integer> root = tree.getRoot();
										// Should not have any threads.
										printSubTest("root should now be 2 [delete element]");
										subResult = assertCustom(root.data, 2);
										subResult = assertCustom(root.right.data, 4);
										subResult = assertCustom(root.hasLeftThread, false);
										subResult = assertCustom(root.hasRightThread, false);

										printSubTest("next node should be 4 [delete element]");
										// Node 4 should have a thread back to 2 [left].
										subResult = assertCustom(root.right.data, 4);
										subResult = assertCustom(root.right.hasLeftThread, true);
										subResult = assertCustom(root.right.left.data, 2);
										subResult = assertCustom(root.hasRightThread, false);

	                  // TEST CASE FOOTER
	                  result = result & subResult;
	                  printSubTestFooter(subResult);

	                  // TEST CASE HEADER
	                  printSubTest("adding strange configuration of elements to binary tree [delete random element]");
	                  subResult = true;

	                  // TEST AND ASSERT
	                  tree = new DoubleThreadedBST<Integer>();
	                  tree.insert(5);
	                  tree.insert(8);
	                  tree.insert(6);
	                  tree.insert(2);
	                  tree.insert(4);
	                  tree.insert(1);
										tree.insert(7);
										tree.insert(10);
										tree.insert(9);
										tree.insert(11);

	                  /*
	                          5
	                        /   \
	                      2       8
	                    /   \    /	\
	                  1       4 6			10
																\		/	\
																 7 9	 11
	                  */
										subResult = assertCustom(tree.delete(2), true);
										printSubTest("Should be able to delete 8");
										subResult = assertCustom(tree.delete(8), true);

										/*
														5
													/   \
												4       9
											/        /  \
										1         6		 10
																\    \
																	7   11
										*/
										root = tree.getRoot();

										// Node 5 should not have any threads.
										subResult = assertCustom(root.data, 5);
							      subResult = assertCustom(root.hasRightThread, false);
							      subResult = assertCustom(root.hasLeftThread, false);

										// Node 4 should thread back to 5 [right].
										subResult = assertCustom(root.left.data, 4);
										subResult = assertCustom(root.left.hasRightThread, true);
										subResult = assertCustom(root.left.right.data, 5);

							      // Node 9 should not have any threads.
										subResult = assertCustom(root.right.data, 9);
							      subResult = assertCustom(root.right.hasRightThread, false);
							      subResult = assertCustom(root.right.hasLeftThread, false);

							      // Node 6 should not thread back to 9 [right].
										subResult = assertCustom(root.right.left.data, 6);
							      subResult = assertCustom(root.right.left.hasRightThread, false);
							      // Node 6 should thread back to 5 [left].
							      subResult = assertCustom(root.right.left.hasLeftThread, true);
							      subResult = assertCustom(root.right.left.left.data, 5);

										// Node 7 should thread back to 6 [left].
										subResult = assertCustom(root.right.left.right.data, 7);
										subResult = assertCustom(root.right.left.right.hasLeftThread, true);
							      subResult = assertCustom(root.right.left.right.left.data, 6);
										// Node 7 should thread back to 9 [right].
										subResult = assertCustom(root.right.left.right.hasRightThread, true);
							      subResult = assertCustom(root.right.left.right.right.data, 9);

							      // Node 10 should thread back to 9 [left].
										printSubTest("Node 10 should thread back to 9 [left]");
										subResult = assertCustom(root.right.right.data, 10);
										subResult = assertCustom(root.right.right.hasLeftThread, true);
										subResult = assertCustom(root.right.right.left.data, 9);

										System.out.println(tree.inorderAscending());

	                  // TEST CASE FOOTER
	                  result = result & subResult;
	                  printSubTestFooter(subResult);

	                  // FOOTER
	                  printFooter(title, result);
	                  return result;
	                  // FOOTER END
	                }

									public boolean test_tree_clone(String title) {
		                  // HEADER
		                  printHeader(title);
		                  boolean result = true;
		                  boolean subResult = true;
		                  // HEADER END

		                  // TEST CASE HEADER
		                  printSubTest("clone a tree");
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

											DoubleThreadedBST<Integer> clone = tree.clone();

											DTNode<Integer> root = clone.getRoot();
											subResult = assertCustom(root.data, 1);
								      subResult = assertCustom(root.hasLeftThread, false);

											// Node 2 should thread back to 1 [left].
											subResult = assertCustom(root.right.data, 2);
								  		subResult = assertCustom(root.right.hasLeftThread, true);
											subResult = assertCustom(root.right.left.data, 1);

											// Node 3 should thread back to 2 [left].
											subResult = assertCustom(root.right.right.data, 3);
											subResult = assertCustom(root.right.right.hasLeftThread, true);
											subResult = assertCustom(root.right.right.left.data, 2);

											// Node 4 should thread back to 3 [left].
											subResult = assertCustom(root.right.right.right.data, 4);
											subResult = assertCustom(root.right.right.right.hasLeftThread, true);
											subResult = assertCustom(root.right.right.right.left.data, 3);

											printSubTest("make sure that the data is not soft copied [clone tree]");
											DTNode<Integer> originalRoot = tree.getRoot();
											originalRoot.right = null;
											subResult = assertCustom(root.right.data, 2);

		                  // TEST CASE FOOTER
		                  result = result & subResult;
		                  printSubTestFooter(subResult);

		                  // TEST CASE HEADER
		                  printSubTest("adding strange configuration of elements to binary tree [clone tree]");
		                  subResult = true;

		                  // TEST AND ASSERT
		                  tree = new DoubleThreadedBST<Integer>();
		                  tree.insert(5);
		                  tree.insert(8);
		                  tree.insert(6);
		                  tree.insert(2);
		                  tree.insert(4);
		                  tree.insert(1);

		                  /*
		                          5
		                        /   \
		                      2       8
		                    /   \    /
		                  1       4 6

		                  */
											clone = tree.clone();
											root = clone.getRoot();
											// Node 5 should not have any threads.
											subResult = assertCustom(root.hasRightThread, false);
											subResult = assertCustom(root.hasLeftThread, false);

											// Node 8 should not have any threads.
											subResult = assertCustom(root.right.hasRightThread, false);
											subResult = assertCustom(root.right.hasLeftThread, false);

											// Node 6 should thread back to 8 [right].
											subResult = assertCustom(root.right.left.hasRightThread, true);
											subResult = assertCustom(root.right.left.right.data, 8);
											// Node 6 should thread back to 5 [left].
											subResult = assertCustom(root.right.left.hasLeftThread, true);
											subResult = assertCustom(root.right.left.left.data, 5);

											// Node 2 should not have any threads.
											subResult = assertCustom(root.left.hasRightThread, false);
											subResult = assertCustom(root.left.hasLeftThread, false);

											// Node 1 should have thread back to 2 [right].
											subResult = assertCustom(root.left.left.hasRightThread, true);
											subResult = assertCustom(root.left.left.right.data, 2);
											// Node 1 should not have a left thread.
											subResult = assertCustom(root.left.left.hasLeftThread, false);

											// Node 4 should have thread back to 5 [right].
											subResult = assertCustom(root.left.right.hasRightThread, true);
											subResult = assertCustom(root.left.right.right.data, 5);
											// Node 4 should have thread back to 2 [left].
											subResult = assertCustom(root.left.right.hasLeftThread, true);
											subResult = assertCustom(root.left.right.left.data, 2);

		                  // TEST CASE FOOTER
		                  result = result & subResult;
		                  printSubTestFooter(subResult);

		                  // FOOTER
		                  printFooter(title, result);
		                  return result;
		                  // FOOTER END
		                }

										public boolean test_tree_copy(String title) {
												// HEADER
												printHeader(title);
												boolean result = true;
												boolean subResult = true;
												// HEADER END

												// TEST CASE HEADER
												printSubTest("copy a tree");
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

												DoubleThreadedBST<Integer> clone = new DoubleThreadedBST<Integer>(tree);

												DTNode<Integer> root = clone.getRoot();
												subResult = assertCustom(root.data, 1);
												subResult = assertCustom(root.hasLeftThread, false);

												// Node 2 should thread back to 1 [left].
												subResult = assertCustom(root.right.data, 2);
												subResult = assertCustom(root.right.hasLeftThread, true);
												subResult = assertCustom(root.right.left.data, 1);

												// Node 3 should thread back to 2 [left].
												subResult = assertCustom(root.right.right.data, 3);
												subResult = assertCustom(root.right.right.hasLeftThread, true);
												subResult = assertCustom(root.right.right.left.data, 2);

												// Node 4 should thread back to 3 [left].
												subResult = assertCustom(root.right.right.right.data, 4);
												subResult = assertCustom(root.right.right.right.hasLeftThread, true);
												subResult = assertCustom(root.right.right.right.left.data, 3);

												printSubTest("make sure that the data is not soft copied [clone tree]");
												DTNode<Integer> originalRoot = tree.getRoot();
												originalRoot.right = null;
												subResult = assertCustom(root.right.data, 2);

												// TEST CASE FOOTER
												result = result & subResult;
												printSubTestFooter(subResult);

												// TEST CASE HEADER
												printSubTest("adding strange configuration of elements to binary tree [clone tree]");
												subResult = true;

												// TEST AND ASSERT
												tree = new DoubleThreadedBST<Integer>();
												tree.insert(5);
												tree.insert(8);
												tree.insert(6);
												tree.insert(2);
												tree.insert(4);
												tree.insert(1);

												/*
																5
															/   \
														2       8
													/   \    /
												1       4 6

												*/
												clone = new DoubleThreadedBST<Integer>(tree);
												root = clone.getRoot();
												// Node 5 should not have any threads.
												subResult = assertCustom(root.hasRightThread, false);
												subResult = assertCustom(root.hasLeftThread, false);

												// Node 8 should not have any threads.
												subResult = assertCustom(root.right.hasRightThread, false);
												subResult = assertCustom(root.right.hasLeftThread, false);

												// Node 6 should thread back to 8 [right].
												subResult = assertCustom(root.right.left.hasRightThread, true);
												subResult = assertCustom(root.right.left.right.data, 8);
												// Node 6 should thread back to 5 [left].
												subResult = assertCustom(root.right.left.hasLeftThread, true);
												subResult = assertCustom(root.right.left.left.data, 5);

												// Node 2 should not have any threads.
												subResult = assertCustom(root.left.hasRightThread, false);
												subResult = assertCustom(root.left.hasLeftThread, false);

												// Node 1 should have thread back to 2 [right].
												subResult = assertCustom(root.left.left.hasRightThread, true);
												subResult = assertCustom(root.left.left.right.data, 2);
												// Node 1 should not have a left thread.
												subResult = assertCustom(root.left.left.hasLeftThread, false);

												// Node 4 should have thread back to 5 [right].
												subResult = assertCustom(root.left.right.hasRightThread, true);
												subResult = assertCustom(root.left.right.right.data, 5);
												// Node 4 should have thread back to 2 [left].
												subResult = assertCustom(root.left.right.hasLeftThread, true);
												subResult = assertCustom(root.left.right.left.data, 2);

												// TEST CASE FOOTER
												result = result & subResult;
												printSubTestFooter(subResult);

												// FOOTER
												printFooter(title, result);
												return result;
												// FOOTER END
											}
}
