package com.neu.algorithms;

class TripleSum{
	
	static int N = 3;
	static int a[] = new int[] {1,-1,0};

	/**
	 * Find the triple sum
	 * @return
	 */
	static int findTripleSum()
	{
		int count = 0;
		for (int i = 0; i < N; i++)
		   for (int j = i+1; j < N; j++)
		      for (int k = j+1; k < N; k++)
		         if (a[i] + a[j] + a[k] == 0)
		            count++;
		return count;

	}
	
}

public class Question3 {

	public static void main(String[] args) {
		 
		int tripleSumCount = TripleSum.findTripleSum();
		System.out.println("Triple Sum Count is : "+ tripleSumCount);
	}
	
}

