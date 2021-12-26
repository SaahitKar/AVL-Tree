class AVLNode {
    //Node Variables
    int key, height;
    Book bookObj;
    AVLNode left, right;
    //AVLNode Instantiation
    AVLNode(int d) {
        key = d;
        height = 1;
    }
}

class Book {
    String title, lastName;
    Book(String t, String l) {
        title = t;
        lastName = l;
    }
}
 
class AVLTree {
    //Variables
    AVLNode root;
    boolean rotate = true;
    
    int heightN(AVLNode av) {
        /* base case tree is empty */
        if (av == null)
            return 0;
        /* If tree is not empty then height = 1 + max of left
         height and right heights */
        return 1 + Math.max(height(av.left), height(av.right));
    }
    
    boolean isBalanced(AVLNode AVLNode) {
        int lh;
        int rh;
        if (AVLNode == null) {
            return true;
        }
        lh = heightN(AVLNode.left);
        rh = heightN(AVLNode.right);
        if (Math.abs(lh - rh) <= 1
            && isBalanced(AVLNode.left)
            && isBalanced(AVLNode.right))
            return true;
        /* If we reach here then tree is not height-balanced */
        return false;
    }
 
    // A utility function to get the height of the tree
    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }
    
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
 
    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        // Perform rotation
        x.right = y;
        y.left = T2;
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        // Perform rotation
        y.left = x;
        x.right = T2;
        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        // Return new root
        return y;
    }
 
    // Get Balance factor of AVLNode N
    int getBalance(AVLNode N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }
 
    AVLNode insert(AVLNode AVLNode, int key, Book b) {
        
        /* 1.  Perform the normal BST insertion */
        if (AVLNode == null)
            return (new AVLNode(key));
        if (key < AVLNode.key)
            AVLNode.left = insert(AVLNode.left, key, b);
        else if (key > AVLNode.key)
            AVLNode.right = insert(AVLNode.right, key, b);
        else // Duplicate keys not allowed
            return AVLNode;
        /* 2. Update height of this ancestor AVLNode */
        AVLNode.height = 1 + max(height(AVLNode.left),
                              height(AVLNode.right));
        /* 3. Get the balance factor of this ancestor
              AVLNode to check whether this AVLNode became
              unbalanced */
        int balance = getBalance(AVLNode);
        // If this AVLNode becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < AVLNode.left.key) {
            System.out.println("Imbalance condition occurred at inserting ISBN " + key + "; fixed in LeftLeft Rotation");
            rotate = false;
            return rightRotate(AVLNode);
        }
        // Right Right Case
        if (balance < -1 && key > AVLNode.right.key) {
            System.out.println("Imbalance condition occurred at inserting ISBN " + key + "; fixed in RightRight Rotation");
            rotate = false;
            return leftRotate(AVLNode);
        }
        // Left Right Case
        if (balance > 1 && key > AVLNode.left.key) {
            System.out.println("Imbalance condition occurred at inserting ISBN " + key + "; fixed in LeftRight Rotation");
            rotate = false;
            AVLNode.left = leftRotate(AVLNode.left);
            return rightRotate(AVLNode);
        }
        // Right Left Case
        if (balance < -1 && key < AVLNode.right.key) {
            System.out.println("Imbalance condition occurred at inserting ISBN " + key + "; fixed in RightLeft Rotation");
            rotate = false;
            AVLNode.right = rightRotate(AVLNode.right);
            return leftRotate(AVLNode);
        }
        /* return the (unchanged) AVLNode pointer */
        return AVLNode;
    }
 
    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every AVLNode
    void preOrder(AVLNode AVLNode) {
        if (AVLNode != null) {
            System.out.print(AVLNode.key + " ");
            preOrder(AVLNode.left);
            preOrder(AVLNode.right);
        }
    }
}