package com.neu.algorithms;
/*
 * Scenario when the array size is too small
 * 1. Create a new array (Twice the size of the smaller array)
 * 2. Copy the contents of the stack array into the newly created array
 * 3. Use that array for further push and pop operations
 * Cost of copying the array is O(n) where n is the number of elements in the stack
 * Hence: Time complexity of Push operation is O(1) - Best Case; O(n) - Worst Case
 * */
public class Question9 {
	// Creating a large array to avoid space issues
	static int MAX = 3;
	int top;
	Node[] a = new Node[MAX]; // Maximum size of Stack
	int max1 = MAX * 2;
	//System.out.println("MAX SIZE IS "+ max1);
	Node[] b = new Node[max1];

	private class Node {
		int age;
		String name;

		Node(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}

	Question9() {
			top = -1;
		}

	// Utility function to check if the stack is empty or not
	public boolean isEmpty() {
		return (top < 0);
	}

	Node push(int age, String name) {
		if (top >= (MAX - 1)) {
			System.out.println("Stack overflow as array size is exceded");
			System.out.println("Creating a new array and copying the elements into it");
			//Creating new array twice the size of the old array
			
			
			for(int i =0; i< MAX; i++) {
				//System.out.println("NODE A HAS "+ b[i].age);
				b[i] = new Node(age,name);
				 //System.arraycopy(b, 0, a, 0, max1);
				b[i] = a[i];
			}
			// Now push the contents into array b
			top++;
			b[top] = new Node(age,name);
			b[top].age = age;
			b[top].name = name;
			System.out.println(b[top].age + " and " + b[top].name + " pushed into the stack\n");
			return b[top];
		} else {
			// System.out.println("Age is" +age+"Name is"+name);
			top++;
			a[top] = new Node(age, name);
			a[top].age = age;
			a[top].name = name;
			System.out.println(a[top].age + " and " + a[top].name + " pushed into the stack\n");
			return a[top];

		}
	}

	int pop() {
		if (top < 0) {
			System.out.println("Stack Underflow");
			// Node popped = null;
			return 0;
		} else {
			if (top >= (MAX - 1)) {
				//top--;
				int popped = b[top].age;
				return popped;
			}else {
				top--;
				int popped = a[top].age;
				return popped;
			}
			
		}
	}

	public static void main(String[] args) {
		Question9 q9 = new Question9();
		q9.push(31, "Name1");
		q9.push(24, "Name2");
		q9.push(10, "Name3");
		q9.push(44, "Name4");
		q9.push(81, "Name5");
		System.out.println(q9.pop() + " Popped from stack");
	}

}
