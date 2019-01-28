package com.neu.algorithms;

import java.util.Scanner;

public class Question5 {

	public static long sumDigits(long number){
		 if (number < 0) {
			throw new IllegalArgumentException("Number entered should be positive");
		}
		else {
			return (number == 0 ? 0 : number % 10 + sumDigits(number / 10)); 
		}
	}
	
	public static void main(String[] args){
		System.out.println("Please enter a positive integer");
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		System.out.println("Sum of the Digits is\n"+sumDigits(n));
	}

}
