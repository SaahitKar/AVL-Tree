//Saahit Karumuri
//SE 3345.501

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) { 
        Scanner myReader = new Scanner(System.in);
        System.out.println("Enter the file name to input ISBN >>> ");
        String text = myReader.nextLine();
        System.out.println("");
        readIn(text);
    }
    
    public static void readIn(String fileName) {
        //Variables Declaration
        AVLTree tree = new AVLTree();
        BinaryTree btree = new BinaryTree();
        ArrayList<Integer> list = new ArrayList<Integer>();
        //Try Statement
        try {
          File myObj = new File(fileName);
          Scanner myReader = new Scanner(myObj);
          //Read all lines in file
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] temp = data.split(" ");
            int val = Integer.parseInt(temp[0]);
            Integer valInt = new Integer(val);
            Book boo = new Book(temp[1], temp[2]);
            //Insert into AVL Tree
            tree.root = tree.insert(tree.root, val, boo);
            Node n = new Node(val);
            list.add(val);
          }
          //Insert into BST Tree & Check if it's a BST Tree
          btree.root = btree.insertLevelOrder(list, btree.root, 0);
          boolean isBinST = btree.isBST();
          AVLTree check = new AVLTree();
          if(isBinST == false) {
            System.out.println("");
            System.out.println("The Random Binary Tree isn't a Binary Search Tree nor an AVL Tree.");
          }
          if(isBinST == true) {
            Book bo = new Book(" "," ");
            for(int i = 0; i < list.size(); i++) {
                //Check an AVL Tree
                check.root = check.insert(check.root, list.get(i), bo);
                if(check.rotate == false) {
                    System.out.println("");
                    System.out.println("The Random Binary Tree is a Binary Search Tree, but not an AVL Tree.");
                    break;
                }
            }
              if(check.rotate == true) {
                System.out.println("");
                System.out.println("The Random Binary Tree is a Binary Search Tree and an AVL Tree.");
              }
          }
          myReader.close();
          //Catch any errors
        } catch (Exception e) {
            System.out.println("An error occurred when trying to open the text file. Try again.");
        }
    }
}