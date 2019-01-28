package com.neu.algorithms;

//Evaluating infix expression using 2 stacks
class Evaluate {
	
	String[] ops;
	Double[] vals;
	int opsCount, valsCount;
	
	Evaluate()
	{
		ops  = new String[100];
		vals = new Double[100];
		opsCount  = -1;
		valsCount = -1;
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
				ops[++opsCount] = exp;
			else if (exp.equals("/") || exp.equals("*")
						|| exp.equals("+") || exp.equals("-"))
			{
				//Check the precedence of the current operator with respect to the 
				//previous operator in the operator array
				if(opsCount > -1 && (ops[opsCount] != "(" || ops[opsCount] != ")")  
						&& prec(exp) <= prec(ops[opsCount]))
					calculate();
				
				ops[++opsCount] = exp;
				
			}
			else if (exp.equals(")"))
			{
				//TODO : Loop till you find the opening braces in the operator
				calculate();
				opsCount--;
				
			}
			else
				vals[++valsCount] = Double.parseDouble(exp);
		}
		
		//Complete backward iteration on the operator stack at the end of the input
		while(opsCount > -1)
		{
			calculate();
		}
		
		System.out.println(vals[valsCount]);
	}
	
	/**
	 * Perform calculation
	 */
	void calculate()
	{
		if(opsCount > -1)
		{
			String op = ops[opsCount--];
			
			Double val2 = vals[valsCount];
			Double val1 = vals[--valsCount];
			
			if(op.equals("/"))
				vals[valsCount] = val1 / val2;
			else if(op.equals("*"))
				vals[valsCount] = val1 * val2;
			else if(op.equals("+"))
				vals[valsCount] = val1 + val2;
			else if(op.equals("-"))
				vals[valsCount] = val1 - val2;
		}
	}
	
	/**
	 * Check Precedence
	 */
	static int prec(String exp) 
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

public class Question1 {
    // Driver method to test above methods 
    public static void main(String[] args) 
    { 
    	Evaluate eval = new Evaluate();
		String expression1 = "( 2 * 2 + ( 20 / 5 ) )";
		eval.evaluateExp(expression1);
		String expression2 = "( 1 + 3 + ( ( 4 / 2 ) * ( 8 * 4 ) ) )";
		eval.evaluateExp(expression2);
		String expression3 = "( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )";
		eval.evaluateExp(expression3);
    } 
}
