package com.neu.algorithms;

public class Question4 {

	int front, rear, size;
	int capacity;
	int array[];

	public Question4(int capacity) {
		this.capacity = capacity;
		front = this.size = 0;
		rear = capacity - 1;
		array = new int[this.capacity];
	}

	// Queue is full when size is equal to capacity
	boolean isFull(Question4 queue) {
		return (queue.size == queue.capacity);
	}

	// Queue is empty when the size is 0
	boolean isEmpty(Question4 queue) {
		return (queue.size == 0);
	}

	// Method to add an item to the queue
	void enqueue(int item) {
		if (isFull(this))
			return;
		this.rear = (this.rear + 1) % this.capacity;
		this.array[this.rear] = item;
		this.size = this.size + 1;
		System.out.println(item + " has been enqueued");
	}

	// Method to remove an item from the queue
	int dequeue() {
		if (isEmpty(this))
			return Integer.MIN_VALUE;
		int item = this.array[this.front];
		this.front = (this.front + 1) % this.capacity;
		this.size = this.size - 1;
		System.out.println(item + " has been dequeued");
		return item;
	}

	// Method to get the front of the queue
	int front() {
		if (isEmpty(this))
			return Integer.MIN_VALUE;
		return this.array[this.front];
	}

	// Method to get rear of the queue
	int rear() {
		if (isEmpty(this)) {
			return Integer.MIN_VALUE;
		}
		return this.array[this.rear];
	}

	public static void main(String[] args) {
		Question4 queue = new Question4(5);
		queue.enqueue(4);
		queue.dequeue();
		queue.enqueue(56);
		queue.enqueue(2);
		queue.enqueue(67);
		queue.dequeue();
		queue.dequeue();

		System.out.println("Front item is " + queue.front());
		System.out.println("Rear item is " + queue.rear());

	}

}
