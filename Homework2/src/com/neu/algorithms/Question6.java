package com.neu.algorithms;

//Non recursive method
public class Question6 {
	String bine;
	int count1 = 0;
	public void countStringBinary(int n) {
		int binCount = (int)Math.pow(2, n);
		for(int i = 0; i < binCount; i++) {
			bine = "";
			int temp = i;
			int count = 0;
			for(int j = 0; j < n; j++) {
				if(temp % 2 == 1) {
					bine = '1'+bine;
				}
				else {
					bine = '0'+bine;
				}
				temp = temp/2;
			}
			for(int k = (n-1); k >= 1; k--) {
				if(bine.charAt(k) == bine.charAt(k - 1) && (bine.charAt(k) != '1') && (bine.charAt(k-1) != '1')) {
					count++;
					break;
				}
			}
			if (count < 1) {
				
				System.out.println(bine);
				count1++;
			}	
		}
		System.out.println("Total number of Strings is "+ count1);
	}
	public static void main(String[] args) {
		Question6 q6 = new Question6();
		q6.countStringBinary(4);

	}

}
