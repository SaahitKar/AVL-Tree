/* Class containing left and right child of current
node and key value*/
import java.util.*;

class Node {
	int key;
	Node left, right;

	public Node(int item) {
		key = item;
		left = right = null;
	}
}

// A Java program to introduce Binary Tree
class BinaryTree
{
	Node root;
	BinaryTree(int key) {
		root = new Node(key);
	}
	BinaryTree() {
		root = null;
	}
	
	 boolean isBST()  {
        return isBinaryST(root, Integer.MIN_VALUE,
                               Integer.MAX_VALUE);
    }
 
    /* Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    boolean isBinaryST(Node node, int min, int max)
    {
        /* an empty tree is BST */
        if (node == null)
            return true;
        /* false if this node violates the min/max constraints */
        if (node.key < min || node.key > max)
            return false;
        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        // Allow only distinct values
        return (isBinaryST(node.left, min, node.key-1) &&
                isBinaryST(node.right, node.key+1, max));
    }
    //
     public Node insertLevelOrder(ArrayList<Integer> arr, Node root, int i) {
        // Base case for recursion
        if (i < arr.size()) {
            Node temp = new Node(arr.get(i));
            root = temp;
            root.left = insertLevelOrder(arr, root.left, 2 * i + 1);
            root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
        }
        return root;
    }
    //
    public void inOrder(Node root)
    {
        if (root != null) {
            System.out.print(root.key + " ");
            inOrder(root.left);
            inOrder(root.right);
        }
    }
}