package com.neu.algorithms;

public class Question6 {
	int size;
	int top;
	char[] a;
	char[] b;
	static String s = "It was the best of time";
	
	// isEmpty function to check if stack is empty
	public boolean isEmpty() {
		if (top < 0)
			return true;
		else
			return false;
	}
	
	//Pushing the string into the stack
	public char[] push(String s) {
		top = -1;
		size = s.length();
		a = s.toCharArray();
		b = new char[size];
		for(int i = 0; i < size; i++) {
			b[++top] = a[i];
		}
		String stack = String.valueOf(b);
		System.out.println("\nString in the stack is: \n"+ stack);
		return b;
	}
	
	//Popping the stack in reverse order
	public String pop(char [] stack) {
		//char[] stack  = this.push(s);
		char [] pop;
		top = stack.length - 1;
		if(isEmpty()) {
			System.out.println("\nCannot pop elements as stack is empty");
			return null;
		}else {
			pop = new char[top + 1];
			int j = 0;
			for(int i=top; i > 0; i--) {
				pop[j] = stack[i];
				j++;					
			}
		}
		String popped = String.valueOf(pop);
		System.out.println("\nPopped stack is: \n"+ popped);
		return popped;
	}
	
	public static void main(String[] args) {
		System.out.println("The given string is:\n" +s);
		Question6 q6 = new Question6();
		q6.push(s);
		q6.pop(q6.push(s));
	}
}
