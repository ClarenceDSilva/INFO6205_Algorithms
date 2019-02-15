package com.neu.algorithms;

public class Question1b {

	public static String s = "Hello Students";
    static int hash = 0;

    public static void main(String args[]) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int ascii = (int)c;
            int power = length-1-i;
            hash += ascii * (int)Math.pow(31, power);
        }
        System.out.println("Hashcode "+hash);
    }
}
