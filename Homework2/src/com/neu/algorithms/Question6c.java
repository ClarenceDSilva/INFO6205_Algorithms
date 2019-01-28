package com.neu.algorithms;

public class Question6c {
	static void countStringBinary(int n, String out, int lastDigit)
    {
    	// if number becomes N-digit, print it
		if (n == 0)
		{
			System.out.print(out + ",");
			return;
		}

		// append 1 to the result and recurse with one less digit
		countStringBinary(n - 1, out + "1", 1);

		// append 0 to the result and recurse with one less digit
		// only if last digit is 1
		if (lastDigit == 1) {
			countStringBinary(n - 1, out + "0", 0);
		}
    }
	
	
	public static void main(String[] args) {

		countStringBinary(4,"",1);
		
	}
}
