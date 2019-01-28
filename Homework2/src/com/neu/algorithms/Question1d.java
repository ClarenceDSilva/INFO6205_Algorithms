package com.neu.algorithms;

import java.util.Stack;
// Refer Step a and Step b from Question1c
public class Question1d {
	// Step c: Evaluate Postfix expression
	static int evaluatePostfixExpn(String expn) {
		// create a stack
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < expn.length(); i++) {
			char c = expn.charAt(i);

			// When the scanned character is a number, push it to the stack
			if (Character.isDigit(c))
				stack.push(c - '0'); // Added to make character as a number
			else {
				// if the scanned character is an operator, pop 2 elements and apply the
				// operator
				int a = stack.pop();
				int b = stack.pop();

				switch (c) {
				case '+':
					stack.push(b + a);
					break;

				case '-':
					stack.push(b - a);
					break;

				case '*':
					stack.push(b * a);
					break;

				case '/':
					stack.push(b / a);
					break;
				}
			}

		}
		return stack.pop();
	}

	public static void main(String[] args) {
		// Step a: Reading the expression(Make sure that your expression does not have a
		// space or a blank character)
		Question1c q1c = new Question1c();
		String expression1 = "( 1 + 2 * ( 20 / 5 ) )";
		System.out.println(q1c.infixToPostfix(expression1));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(q1c.infixToPostfix(expression1)));
		
		EvaluateSingleArray eval2 = new EvaluateSingleArray();
		String expression2 = "( 1 + 3 + ( ( 4 / 2 ) * ( 8 * 4 ) ) )";
		System.out.println(q1c.infixToPostfix(expression2));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(q1c.infixToPostfix(expression2)));
		
		EvaluateSingleArray eval3 = new EvaluateSingleArray();
		String expression3 = "( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )";
		System.out.println(q1c.infixToPostfix(expression3));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(q1c.infixToPostfix(expression3)));
	}
}
