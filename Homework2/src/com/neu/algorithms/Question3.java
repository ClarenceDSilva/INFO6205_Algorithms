package com.neu.algorithms;

public class Question3 {
	private Node first, last;

	private class Node {
		String item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(String item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty())
			first = last;
		else
			oldlast.next = last;
		System.out.println(item + " has been dequeued");
	}
	
	public String dequeue() {
		String item = first.item;
		first = first.next;
		if(isEmpty())
			last = null;
		System.out.println(item + " has been dequeued");
		return item;
		
	}

	public static void main(String[] args) {
		Question3 queue = new Question3();
		queue.enqueue("Ball");
		queue.dequeue();
		queue.enqueue("Scissors");
		queue.enqueue("paper");
		queue.enqueue("Snow");
		queue.dequeue();
		queue.dequeue();

	}

}
