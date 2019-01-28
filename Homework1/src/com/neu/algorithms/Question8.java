package com.neu.algorithms;
//Array implementation of stack
// Time complexity for push operation - O(1)

public class Question8 {
	
	// Creating a large array to avoid space issues
	static final int MAX = 1000; 
    int top; 
    Node[] a = new Node[MAX]; // Maximum size of Stack 
    
	private class Node {
		int age;
		String name;
		
		Node(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}
	
	Question8() {
		top = -1;
	}
	
	// Utility function to check if the stack is empty or not 
    public boolean isEmpty() 
    { 
        return(top < 0);
    }
    
    Node push (int age, String name) {
    	if(top >= (MAX - 1)) {
    		System.out.println("Stack overflow");
    		Node pushed =  null;
    		return pushed;  		
    	}else {
    		//System.out.println("Age is" +age+"Name is"+name);
    		top++;
    		a[top] = new Node(age,name);
    		a[top].age = age;
    		a[top].name = name;
    		System.out.println(a[top].age + " and " + a[top].name+ " pushed into the stack"); 
            return a[top]; 
    		
    	}
    }
    
    int pop() {
    	if(top < 0) {
    		System.out.println("Stack Underflow");
    		//Node popped =  null;
    		return 0;
    	}else {
    		//top --;
    		int popped = a[top].age;
    		return popped;
    	}
    }
	public static void main(String[] args) {
		Question8 q8 = new Question8();
		q8.push(31, "Name1");
		q8.push(24, "Name2");
		q8.push(10, "Name3");
		q8.push(44, "Name4");
		q8.push(81, "Name5");
		System.out.println(q8.pop() + " Popped from stack"); 

	}

}
