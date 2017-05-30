import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BTreePrinter<T extends Comparable<? super T>>{

	/* DESTROY TREE STRUCTURE! */
	public void convertToBST(DTNode<T> root) {

		String result = "";

		DTNode<T> prev = null, p = root, lastJump = null;;

		if (root != null) {

			// Go far left
			while (p != null) {
				prev = p;
				p = p.left;
			}

			p = prev;

			// p is now most left root of tree

			while (p != null) {
				result += p.data; //visit
				System.out.println("p = " + "["+ (p != null ? p.data : "null") +
				 		"] [L: " + (p.left != null ? p.left.data : "null") + "][" + (p != null ? p.hasLeftThread : "null") +
				 		"] [R: " + (p.right != null ? p.right.data : "null") + "][" + (p != null ? p.hasRightThread : "null") + "]");

				System.out.println("prev AFTER = " + "["+ (prev != null ? prev.data : "null") +
				"] [L: " + (prev.left != null ? prev.left.data : "null") + "][" + (p != null ? prev.hasLeftThread : "null") +
				"] [R: " + (prev.right != null ? prev.right.data : "null") + "][" + (p != null ? prev.hasRightThread : "null") + "]");
				prev = p;

				System.out.println("prev new = " + "["+ (prev != null ? prev.data : "null") +
				"] [L: " + (prev.left != null ? prev.left.data : "null") + "][" + (p != null ? prev.hasLeftThread : "null") +
				"] [R: " + (prev.right != null ? prev.right.data : "null") + "][" + (p != null ? prev.hasRightThread : "null") + "]");


				p = p.right; // Jump right
				// before we forget about prev, rek it

				System.out.println("p NEW = " + "["+ (p != null ? p.data : "null") +
				 		"] [L: " + (p.left != null ? p.left.data : "null") + "][" + (p != null ? p.hasLeftThread : "null") +
				 		"] [R: " + (p.right != null ? p.right.data : "null") + "][" + (p != null ? p.hasRightThread : "null") + "]");



				if (prev.hasRightThread) {
					prev.right = null;
					prev.hasRightThread = false;
				}


				if (prev.hasLeftThread) {
					prev.left = null;
					prev.hasRightThread = false;
				}

				if (p!= null && !prev.hasRightThread) {
					while (!p.hasLeftThread) { // Go far left
						p = p.left;
					}
				}
			}
		}

		// String result = "";
		//
		// DTNode<T> prev = null, p = root;
		//
		// if (root != null) {
		//
		// 	// Go far left
		// 	while (p.left != null) {
		// 		p = p.left;
		// 	}
		//
		// 	prev = p;
		//
		// 	// p is now most left root of tree
		//
		// 	while (p != null) {
		// 		System.out.println("p = " + "["+ (p != null ? p.data : "null") +
		// 		"] [L: " + (p.left != null ? p.left.data : "null") + "][" + (p != null ? p.hasLeftThread : "null") +
		// 		"] [R: " + (p.right != null ? p.right.data : "null") + "][" + (p != null ? p.hasRightThread : "null") + "]");
		//
		// 		System.out.println("prev = " + "["+ (prev != null ? prev.data : "null") +
		// 		"] [L: " + (prev.left != null ? prev.left.data : "null") + "][" + (p != null ? prev.hasLeftThread : "null") +
		// 		"] [R: " + (prev.right != null ? prev.right.data : "null") + "][" + (p != null ? prev.hasRightThread : "null") + "]");
		//
		// 		System.out.println("");
		// 		prev = p;
		//
		//
		// 		result += p.data; //visit
		//
		//
		// 		p = p.right;
		//
		// 		if (p!= null && !prev.hasRightThread) {
		//
		// 			if (prev.hasRightThread) {
		// 				prev.right = null;
		// 				prev.hasRightThread = false;
		// 			}
		//
		//
		// 			if (prev.hasLeftThread) {
		// 				prev.left = null;
		// 				prev.hasRightThread = false;
		// 			}
		//
		// 			while (!p.hasLeftThread) {
		// 				p = p.left;
		// 			}
		// 		} else {
		// 			// STRIP TREE
		//
		//
		//
		// 		}
		// 	}
		// }
	}

    public void printNode(DTNode<T> root) {

		// Convert Double Thread to BST

		convertToBST(root);
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private void printNodeInternal(List<DTNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<DTNode<T>> newNodes = new ArrayList<DTNode<T>>();
        for (DTNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int maxLevel(DTNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private boolean isAllElementsNull(List<DTNode<T>> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
