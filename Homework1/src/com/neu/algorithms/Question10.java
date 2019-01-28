package com.neu.algorithms;

import java.util.Stack;

public class Question10 {

	//Method to return the precedence of the given function
	// Greater the return value, greater the precedence
	static int Precedence(char ch) {
		switch(ch) {
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
	
	//Step b: Method to convert an expression from Infix to Postfix
	static String infixToPostfix(String exp) {
		String result = new String("");
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<exp.length(); ++i) {
			char c = exp.charAt(i);
			
			// Current token is a whitespace, skip it 
            if (c == ' ') 
                continue; 
			
			// If scanned character is an operand, add it to output
			if(Character.isLetterOrDigit(c))
				result += c;
			
			//If scanned character is an '(' add it to the stack
			else if(c == '(') {
				stack.push(c);
			}
			// When ')' is encountered, pop the stack, till you reach the '(' bracket
			else if (c == ')') {
				while(!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();
				 if (!stack.isEmpty() && stack.peek() != '(') 
	                    return "Invalid Expression"; // invalid expression
				 else
					 stack.pop();
			}
			else //Operator is encountered 
			{	
				while(!stack.isEmpty() && Precedence(c) <= Precedence(stack.peek()))
					result += stack.pop();
				stack.push(c);
			} 	
		}
		
		//Pop all the remaining operators from the stack
		while(!stack.isEmpty())
			result += stack.pop();
		
		return result;
	}
	
	//Step c: Evaluate Postfix expression
	static int evaluatePostfixExpn(String expn) {
		// create a stack
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < expn.length(); i++) {
			char c = expn.charAt(i);
			
			//When the scanned character is a number, push it to the stack
			if(Character.isDigit(c))
				stack.push(c - '0'); // Added to make character as a number
			else {
				// if the scanned character is an operator, pop 2 elements and apply the operator
				int a = stack.pop();
				int b = stack.pop();
				
				switch(c) {	
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
					stack.push(b/a);
					break;
				}
			}
			
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		// Step a: Reading the expression(Make sure that your expression does not have a space or a blank character)
		
		String exp1 = "(1+2*(20/5))";
		System.out.println("Expression is\n "+exp1);
		System.out.println("Infix to Postfix conversion of the expression is\n "+ infixToPostfix(exp1));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(infixToPostfix(exp1)));
		
		String exp2 = "(1+2-3)";
		System.out.println("\nExpression is\n "+exp2);
		System.out.println("Infix to Postfix conversion of the expression is\n "+ infixToPostfix(exp2));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(infixToPostfix(exp2)));
		
		String exp3 = "(1+3+((4/2)*(8*4)))";
		System.out.println("\nExpression is\n "+exp3);
		System.out.println("Infix to Postfix conversion of the expression is\n "+ infixToPostfix(exp3));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(infixToPostfix(exp3)));
		
		String exp4 = "( 300 + 23 ) * ( 43 - 21 )/( 84 + 7 )";
		System.out.println("\nExpression is\n "+exp4);
		System.out.println("Infix to Postfix conversion of the expression is\n "+ infixToPostfix(exp4));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(infixToPostfix(exp4)));
		
		String exp5 = "(4+8)*(6-5)/((3-2)*(2+2))";
		System.out.println("\nExpression is\n "+exp5);
		System.out.println("Infix to Postfix conversion of the expression is\n "+ infixToPostfix(exp5));
		System.out.println("Postfix evaluation is\n"+ evaluatePostfixExpn(infixToPostfix(exp5)));
	}

}
