package com.neu.algorithms;

// Time Complexity of this is O(n)
//Space Complexity is n
public class Question4a {

	static int factorial(int n) {
		System.out.println("Pushed "+n+" into the stack");
		System.out.println("\n");
		if (n == 0) {
			return 1;
		}else {
			int factorial = n * factorial(n-1);
			System.out.println("Popped "+factorial+" out of the stack");
			return factorial;
		}
	}
	
	public static void main(String[] args) {
		int fact1 = factorial(7);
		int fact2 = factorial(14);
		System.out.println("The factorial of 7 is "+ fact1+" and 14 is "+fact2);
	}

}
