package com.neu.algorithms;

import java.util.Random;

public class Question1 {

	// Using String
	public void randomTextUsingString() {
		System.out.println("Using String");
		long startTime = System.currentTimeMillis();
		/*// Byte array for generating plane text
		byte[] array = new byte[500];
		for (int i = 0; i < 200000; i++) {
			new Random().nextBytes(array);
			String randomString = new String(array);
			System.out.println(randomString);
		}*/
		int leftLimit = 97;
		int rightLimit = 122;
		int targetStringLength = 500;
		Random random = new Random();
		String str = new String();
		for(int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			str += ((char) randomLimitedInt);
			reverseString(str);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken using STRING "+(endTime - startTime));
		
	}

	// Using StringBuider
	public void randomTextUsingStringBuffer() {
		long startTime = System.currentTimeMillis();
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		System.out.println("Using String Builder");
		 byte b = (byte)500;
		int targetStringLength = b &500;
		for (int i = 0; i < 200000; i++) {
			Random random = new Random();
			StringBuilder builder = new StringBuilder(targetStringLength);
			for (int j = 0; j < targetStringLength; j++) {
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				builder.append((char) randomLimitedInt);
			}
			reverseStringBuilder(builder);
			//String generatedString = builder.toString();
			//System.out.println(generatedString);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken using STRING BUILDER "+(endTime - startTime));
		
	}
	
	//Method to reverse StringBuilder
	static StringBuilder reverseStringBuilder(StringBuilder input) {
		input = input.reverse();
		return input;
	}
	
	//Method to reverse String
	static String reverseString(String input) {
		char[] inputs = input.toCharArray();
		String result = "";
		for(int i = inputs.length-1; i>=0; i--) {
			result += inputs[i];
		}
		return result;
	}

	public static void main(String[] args) {
		Question1 q1 = new Question1();
		q1.randomTextUsingString();
		q1.randomTextUsingStringBuffer();
	}

}
