package com.neu.algorithms;

class Complexity
{
	static void complexities()
	{
		int n = 2;
		
		//O(1) 
		System.out.println("O(1) Input is : " + n);
		
		//O(log n)
		for (int i = 1; i < n; i = i * 2){
		    System.out.println("O(log n) Input is: " + i);
		}
		
		//O(n)
		for (int i = 0; i < n; i++) {
		    System.out.println("O(n) Input is " + i);
		}
		
		//O(n log n)
		for (int i = 1; i <= n; i++){
		    for(int j = 1; j < 8; j = j * 2) {
		        System.out.println("O(n log n) Input is : " + i + " and " + j);
		    }
		}
		
		//O(n2)
		for (int i = 1; i <= n; i++) {
		    for(int j = 1; j <= n; j++) {
		        System.out.println("O(n2) Input is : " + i + " and " + j);
		    }
		}
		
		//O(n3)
		for (int i = 1; i <= n; i++) {
		    for(int j = 1; j <= n; j++) {
		    	for(int k = 1; k <= n; k++) {
		    		System.out.println("O(n3) Input is : " + i + ", " + j + " and " + k);
		    	}
		    }
		}
		
		//O(2^n)
		for (int i = 1; i <= Math.pow(2, n); i++){
		    System.out.println("O(2^n) Input is : " + i);
		}

	}
}

public class Question2 {

	public static void main(String[] args) {
		Complexity.complexities();
	}
	
}