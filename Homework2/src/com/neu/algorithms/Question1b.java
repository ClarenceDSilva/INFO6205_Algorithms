package com.neu.algorithms;

// Evaluate Infix expression using only 1 Arrays
class EvaluateSingleArray {
	
	String[] elems;
	int count;
	
	EvaluateSingleArray()
	{
		elems  = new String[100];
		count  = -1;
	}
	
	/**
	 * Evaluate the given expression
	 * @param expression
	 */
	void evaluateExp(String expression)
	{
		String[] exps = expression.split(" ");
		
		for(String exp : exps)
		{
			if (exp.equals("("))
				continue;
			else if (exp.equals("/") || exp.equals("*")
						|| exp.equals("+") || exp.equals("-"))
			{
				//Check the precedence of the current operator with respect to the 
				//previous operator in the operator array
				if(count > -1 && (elems[count] != "(" || elems[count] != ")")  
						&& prec(exp) <= prec(elems[count]))
					calculate();
				
				elems[++count] = exp;
				
			}
			else if (exp.equals(")"))
			{
				calculate();
				
			}
			else
				elems[++count] = exp;
		}
		
		//Complete backward iteration on the operator stack at the end of the input
		while(count > 0)
		{
			calculate();
		}
		
		System.out.println(elems[count]);
	}
	
	/**
	 * Perform calculation
	 */
	void calculate()
	{
		if(count > 0)
		{
			Double val2 = Double.parseDouble(elems[count]);
			String op = elems[--count];
			Double val1 = Double.parseDouble(elems[--count]);
			
			if(op.equals("/"))
				elems[count] = "" + (val1 / val2);
			else if(op.equals("*"))
				elems[count] = "" + (val1 * val2);
			else if(op.equals("+"))
				elems[count] = "" + (val1 + val2);
			else if(op.equals("-"))
				elems[count] = "" + (val1 - val2);
		}
	}
	
	/**
	 * Check Precedence
	 */
    int prec(String exp) 
	{ 
		switch (exp) 
		{ 
		case "+": 
		case "-": 
			return 1; 
	
		case "*": 
		case "/": 
			return 2; 
	
		case "^": 
			return 3; 
		} 
		return -1; 
	}
}

//Driver class
public class Question1b {
	
	public static void main(String[] args) {
		
		EvaluateSingleArray eval1 = new EvaluateSingleArray();
		String expression1 = "( 1 + 2 * ( 20 / 5 ) )";
		eval1.evaluateExp(expression1);
		EvaluateSingleArray eval2 = new EvaluateSingleArray();
		String expression2 = "( 1 + 3 + ( ( 4 / 2 ) * ( 8 * 4 ) ) )";
		eval2.evaluateExp(expression2);
		EvaluateSingleArray eval3 = new EvaluateSingleArray();
		String expression3 = "( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )";
		eval3.evaluateExp(expression3);
		
	}

}

