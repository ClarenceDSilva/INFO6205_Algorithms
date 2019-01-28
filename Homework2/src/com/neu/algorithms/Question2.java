package com.neu.algorithms;

import java.util.Stack;

public class Question2 {

	// Method to return the precedence of the given function
	// Greater the return value, greater the precedence
	static int Precedence(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

	// Step b: Method to convert an expression from Infix to Postfix
	static String infixToPostfix(String exp) {
		String result = new String("");
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);
			
			// Current token is a whitespace, skip it 
            if (c == ' ') 
                continue; 

			// If scanned character is an operand, add it to output
			if (Character.isLetterOrDigit(c))
				result += c;

			// If scanned character is an '(' add it to the stack
			else if (c == '(') {
				stack.push(c);
			}
			// When ')' is encountered, pop the stack, till you reach the '(' bracket
			else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();
				if (!stack.isEmpty() && stack.peek() != '(')
					return "Invalid Expression"; // invalid expression
				else
					stack.pop();
			} else // Operator is encountered
			{
				while (!stack.isEmpty() && Precedence(c) <= Precedence(stack.peek()))
					result += stack.pop();
				stack.push(c);
			}
		}

		// Pop all the remaining operators from the stack
		while (!stack.isEmpty())
			result += stack.pop();

		return result;
	}

	public static void main(String[] args) {
		// Step a: Reading the expression(Make sure that your expression does not have a
		// space or a blank character)

		String exp1 = "((A/(B+C))-D)";
		System.out.println("Expression is\n " + exp1);
		System.out.println("Infix to Postfix conversion of the expression is\n " + infixToPostfix(exp1));
	}

}
