package com.neu.algorithms;

import java.util.Arrays;

public class Question6b {
	public static void main(String[] args) {
		CircularQueue<Integer> q = new CircularQueue<Integer>(8);
		int [] inputs = new int[] {7, 38, 3 ,9 ,82, 10, 31, 24};
		
		//Enqueue all values
		for(int input : inputs)
			q.enqueue(input);
		
		//Display queue elements
		System.out.println(q.displayQueue());
		
		//Dequeue 3 elements form the queue
		for (int count = 0; count < 3; count++)
			System.out.println(q.dequeue());
		
		//Enqueue 2 elements
		q.enqueue(1);
		q.enqueue(2);
		
		// Dequeue all elements
		while (!q.isEmpty())
			q.dequeue();

		// Display the empty queue
		System.out.println(q.displayQueue());
			
	}
}
	class CircularQueue<E> {
		private int currentSize;
		private E[] circularQueueElements;
		private int maxSize;
		private int rear;
		private int front;
		
		public CircularQueue(int maxSize) {
			this.maxSize = maxSize;
			circularQueueElements = (E[]) new Object[this.maxSize];
			currentSize = 0;
			front = -1;
			rear = -1;
		}
		
		// Enqueue elements to the rear
		public void enqueue(E item) {
			if(isFull()) {
				System.out.println("Queue is full");
			}else {
				rear = (rear+1) % circularQueueElements.length;
				circularQueueElements[rear] = item;
				currentSize++;
				
				if(front == -1) {
					front = rear;
				}
			}
		}
		
		// Dequeue elements from front
		public E dequeue()  {
	        E deQueuedElement = null;
	        if (isEmpty()) {
	           System.out.println("Queue us empty");
	        }
	        else {
	            deQueuedElement = circularQueueElements[front];
	            circularQueueElements[front] = null;
	            front = (front + 1) % circularQueueElements.length;
	            currentSize--;
	        }
	        return deQueuedElement;
	    }
		
		// To display queue elements
		public String displayQueue() {
	    	
	    	String result = "";
	    	
	    	if(!this.isEmpty())
	    		result = "CircularQueue [" + Arrays.toString(circularQueueElements) + "]";
	    	else
	    		result = "Circular Queue is empty";
	    	
	        return result;
	    }
		
		boolean isFull() {
			return (currentSize == circularQueueElements.length);
		}
		
		boolean isEmpty() {
			return (currentSize == 0);
		}
	}

