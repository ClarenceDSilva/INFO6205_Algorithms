package com.neu.algorithms;

public class Question7 {
	
	private class Node {
		int age;
		String name;
		Node next;
	}
	Node top;
	Question7(){
		this.top = null;
	}
	
	public void push(int x, String y) {
		// Create a new node temp and allocate memory
		Node temp = new Node();
		
		if(temp == null) {
			System.out.println("\nNode overflow");
			return;
		}
		// initialize data into temp data field 
		temp.age = x;
		temp.name = y;
		
		// put top reference into temp link 
		temp.next = top;
		
		// update top reference 
        top = temp; 
	}
	
	// Utility function to check if the stack is empty or not 
    public boolean isEmpty() 
    { 
        return top == null; 
    } 
    //Utility function to check the top element of the stack
    public String peek() {
    	//check for empty stack
    	if(!isEmpty()) {
    		return top.name;
    	}else {
    		System.out.println("Stack is empty");
    		return null;
    	}
    }
    
    //Utility function to pop the top element of the stack
    public void pop() {
    	if(top == null) {
    		System.out.println("Stack underflow");
    		return;
    	}
    	// update the top pointer to point to the next node 
        top = (top).next; 
    }
    
    public void display() {
    	// Check if there are elements in the stack
    	if(top == null) {
    		System.out.println("\n Stack underdlow");    		
    	}else {
    		Node temp = top;
    		while (temp !=null) {
    			System.out.println("Node data:\n"+ "AGE:"+ temp.age+ " "+ "NAME:" + temp.name);
    			temp = temp.next;
    		}
    	}
    }

	public static void main(String[] args) {
		Question7 q7 = new Question7();
		q7.push(31, "Name1");
		q7.push(24, "Name2");
		q7.push(10, "Name3");
		q7.push(44, "Name4");
		q7.push(81, "Name5");
		
		//print stack elements
		q7.display();
		
		//print top element of the stack
		System.out.println("\nTop element is: " +q7.peek());
		
		//Deleting the top element of the stack
		System.out.println("\nAfter Popping two stack elements from the top");
		q7.pop();
		q7.pop();
		
		//print stack elements
		q7.display();
		
		//print top element of the stack
		System.out.println("\nTop element is: " +q7.peek());
	}

}
