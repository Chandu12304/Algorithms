package daaManual;

import java.util.*;

public class binaryTreeInserion {

    // Definition of the TreeNode class
    class TreeNode {
        int val; // Value of the node
        TreeNode left; // Pointer to the left child
        TreeNode right; // Pointer to the right child

        // Default constructor
        TreeNode() {
            this.val = 0;
            this.left = null;
            this.right = null;
        }

        // Constructor that initializes the node with a given value
        TreeNode(int x) {
            this.val = x;
            this.left = null;
            this.right = null;
        }

        // Constructor that initializes the node with a given value and its left and right children
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }

    // Method to insert a new value into the binary search tree (BST)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val); // Create a new node
        if (root == null) return newNode; // If the tree is empty, return the new node as the root
        TreeNode ptr = root; // Pointer to traverse the tree
        TreeNode par = ptr; // Pointer to keep track of the parent node

        // Traverse the tree to find the correct position for the new node
        while (ptr != null) {
            par = ptr; // Update parent pointer
            if (newNode.val < ptr.val) ptr = ptr.left; // Move to the left child
            else ptr = ptr.right; // Move to the right child
        }

        // Insert the new node as a child of the parent
        if (newNode.val < par.val) par.left = newNode; // Insert on the left
        else par.right = newNode; // Insert on the right
        return root; // Return the unchanged root
    }

    // Method to delete a node with a given key from the BST
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null; // If the tree is empty, return null
        TreeNode ptr1 = null; // Pointer to keep track of the parent node
        TreeNode ptr2 = root; // Pointer to find the node to be deleted
        TreeNode ptr3 = null; // Pointer for left subtree
        TreeNode ptr4 = null; // Pointer for right subtree

        // Traverse the tree to find the node with the key
        while (ptr2 != null && ptr2.val != key) {
            ptr1 = ptr2; // Update parent pointer
            if (key < ptr2.val) ptr2 = ptr2.left; // Move to the left child
            else ptr2 = ptr2.right; // Move to the right child
        }
        
        // If the key was not found, return the original root
        if (ptr2 == null) return root;

        // Case 1: Node with no children (leaf node)
        if (ptr2.left == null && ptr2.right == null) {
            if (ptr1 != null) {
                if (ptr1.left == ptr2) ptr1.left = null; // Remove leaf from parent's left
                else ptr1.right = null; // Remove leaf from parent's right
            } else return null; // If the root is to be deleted
        }
        // Case 2: Node with one child (left or right)
        else if (ptr2.left != null && ptr2.right == null) {
            if (ptr1 != null) {
                if (ptr1.left == ptr2) ptr1.left = ptr2.left; // Bypass node by linking parent to left child
                else ptr1.right = ptr2.left; // Bypass node by linking parent to left child
            } else return ptr2.left; // If the root is to be deleted
        } else if (ptr2.left == null && ptr2.right != null) {
            if (ptr1 != null) {
                if (ptr1.left == ptr2) ptr1.left = ptr2.right; // Bypass node by linking parent to right child
                else ptr1.right = ptr2.right; // Bypass node by linking parent to right child
            } else return ptr2.right; // If the root is to be deleted
        }
        // Case 3: Node with two children
        else {
            ptr3 = ptr2.left; // Pointer to the left subtree
            ptr4 = ptr2.right; // Pointer to the right subtree
            while (ptr4.left != null) ptr4 = ptr4.left; // Find the minimum node in the right subtree
            ptr4.left = ptr3; // Link the left subtree to the minimum node
            if (ptr1 != null) {
                if (ptr1.left == ptr2) ptr1.left = ptr2.right; // Bypass node by linking parent to right child
                else ptr1.right = ptr2.right; // Bypass node by linking parent to right child
            } else return ptr2.right; // If the root is to be deleted
        }
        return root; // Return the unchanged root
    }

    // Method to perform inorder traversal of the tree
    public void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left); // Visit the left subtree
            System.out.print(root.val + " "); // Print the value of the node
            inorderTraversal(root.right); // Visit the right subtree
        }
    }

    // Main method to demonstrate insertion and deletion
    public static void main(String[] args) {
        binaryTreeInserion bst = new binaryTreeInserion(); // Create an instance of the class
        TreeNode root = null; // Initialize the root of the tree

        // Insert values into the BST
        root = bst.insertIntoBST(root, 3);
        root = bst.insertIntoBST(root, 4);
        root = bst.insertIntoBST(root, 2);
        root = bst.insertIntoBST(root, 5);
        root = bst.insertIntoBST(root, 1);

        // Print inorder traversal before deletion
        System.out.print("Inorder traversal before deletion: ");
        bst.inorderTraversal(root);
        System.out.println();

        // Delete a node with the value 4
        root = bst.deleteNode(root, 2);

        // Print inorder traversal after deletion
        System.out.print("Inorder traversal after deleting 2 : ");
        bst.inorderTraversal(root);
        System.out.println();
    }
}
