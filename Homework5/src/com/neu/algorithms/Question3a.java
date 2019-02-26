package com.neu.algorithms;

//This program answers the Questions 2j and 3 for Binary Tree
public class Question3a {

	Node root; 
	  
    // Tree Node 
    static class Node { 
        int data; 
        Node left, right; 
        Node(int data) 
        { 
            this.data = data; 
            this.left = null; 
            this.right = null; 
        } 
    } 
  
    // Function to insert nodes in level order 
    public Node insertLevelOrder(int[] arr, Node root, 
                                                int i) 
    { 
        // Base case for recursion 
        if (i < arr.length) { 
            Node temp = new Node(arr[i]); 
            root = temp; 
  
            // insert left child 
            root.left = insertLevelOrder(arr, root.left, 
                                             2 * i + 1); 
  
            // insert right child 
            root.right = insertLevelOrder(arr, root.right, 
                                               2 * i + 2); 
        } 
        return root; 
    } 
  
    // Function to print tree nodes in InOrder fashion 
    public void inOrder(Node root) 
    { 
        if (root != null) { 
            inOrder(root.left); 
            System.out.print(root.data + " "); 
            inOrder(root.right); 
        } 
    } 
    
    
	private boolean containsNodeRecursive(Node current, int value) {
		if (current == null) {
			return false;
		}
		if (value == current.data) {
			return true;
		}
		return value < current.data ? containsNodeRecursive(current.left, value)
				: containsNodeRecursive(current.right, value);
	}
	
	
	public boolean containsNode(int value) {
	    return containsNodeRecursive(root, value);
	}

  
    // Driver program to test above function 
    public static void main(String args[]) 
    { 
    	Question3a t2 = new Question3a(); 
        int arr[] = { 3,7,9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56 }; 
        t2.root = t2.insertLevelOrder(arr, t2.root, 0); 
        t2.inOrder(t2.root); 
        System.out.println(t2.containsNode(55));
    } 
}
