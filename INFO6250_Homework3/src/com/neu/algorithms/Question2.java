package com.neu.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

// node class is the basic structure 
// of each node present in the Huffman - tree. 
class HuffmanNode {

	int data;
	char c;

	HuffmanNode left;
	HuffmanNode right;
}

// comparator class helps to compare the node
// on the basis of one of its attribute.
// Here we will be compared
// on the basis of data values of the nodes.
class MyComparator implements Comparator<HuffmanNode> {
	public int compare(HuffmanNode x, HuffmanNode y) {

		return x.data - y.data;
	}
}

class Question2 {
	
	private static char[] charArray;
	
	private static int[] charfreq;

	// recursive function to print the
	// huffman-code through the tree traversal.
	// Here s is the huffman - code generated.
	public static void printCode(HuffmanNode root, String s) {

		// base case; if the left and right are null
		// then its a leaf node and we print
		// the code s generated by traversing the tree.
		if (root.left == null && root.right == null) {

			// c is the character in the node
			System.out.println(root.c + ":" + s);

			return;
		}

		// if we go to left then add "0" to the code.
		// if we go to the right add"1" to the code.

		// recursive calls for left and
		// right sub-tree of the generated tree.
		printCode(root.left, s + "0");
		printCode(root.right, s + "1");
	}
	
	// function to print the character and its 
	// frequency in order of its occurrence 
	public static void getCharWithFreq(String str) 
	{ 
		final int SIZE = 1000; 
		
		charArray = new char[10];
		
		charfreq = new int[10];
		
		int count = 0;
		
		// size of the string 'str' 
		int n = str.length(); 

		// 'freq[]' implemented as hash table 
		int[] freq = new int[SIZE]; 

		for (int i = 0; i < n; i++) 
		{
			freq[str.charAt(i)]++;
		}

		// traverse 'str' from left to right 
		for (int i = 0; i < n; i++) { 

			// if frequency of character str.charAt(i) 
			// is not equal to 0 
			if (freq[str.charAt(i)] != 0) { 

				// print the character along with its 
				// frequency 
				charArray[count]  = str.charAt(i);
				charfreq[count] = freq[str.charAt(i)] ;
				count++;
				System.out.print(str.charAt(i) + " : "); 
				System.out.println(freq[str.charAt(i)] + " "); 

				// update frequency of str.charAt(i) to 
				// 0 so that the same character is not 
				// printed again 
				freq[str.charAt(i)] = 0; 
			} 
		} 
	} 

	// main function
	public static void main(String[] args) {

		String input = "Test is a hard test";
		getCharWithFreq(input);
		
		System.out.println();
		System.out.println("Before Compression");
		
		//Original binary conversion
		for(char c : charArray)
        {
        	int charInt = (int) c;
        	System.out.println(c + ":" + Integer.toBinaryString(charInt));
        }
		
		System.out.println();
		System.out.println("After compression by Huffman Tree");

		// creating a priority queue q.
		// makes a min-priority queue(min-heap).
		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(input.length(), new MyComparator());

		for (int i = 0; i < charfreq.length; i++) {

			// creating a huffman node object
			// and adding it to the priority-queue.
			HuffmanNode hn = new HuffmanNode();

			hn.c = charArray[i];
			hn.data = charfreq[i];

			hn.left = null;
			hn.right = null;

			// add functions adds
			// the huffman node to the queue.
			q.add(hn);
		}

		// create a root node
		HuffmanNode root = null;

		// Here we will extract the two minimum value
		// from the heap each time until
		// its size reduces to 1, extract until
		// all the nodes are extracted.
		while (q.size() > 1) {

			// first min extract.
			HuffmanNode x = q.peek();
			q.poll();

			// second min extarct.
			HuffmanNode y = q.peek();
			q.poll();

			// new node f which is equal
			HuffmanNode f = new HuffmanNode();

			// to the sum of the frequency of the two nodes
			// assigning values to the f node.
			f.data = x.data + y.data;
			f.c = '-';

			// first extracted node as left child.
			f.left = x;

			// second extracted node as the right child.
			f.right = y;

			// marking the f node as the root node.
			root = f;

			// add this node to the priority-queue.
			q.add(f);
		}

		// print the codes by traversing the tree
		printCode(root, "");
		
	}
}
