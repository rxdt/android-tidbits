import java.util.*;

public class Evaluator {
	
	 private Stack<Operand> opdStack;
	 
	 private Stack<Operator> oprStack;
	 
	 public Evaluator() {
		 opdStack = new Stack<Operand>();
		 oprStack = new Stack<Operator>();
	 }

	 public int eval(String expr) {
		 
		 Operator.operators.put("+", new AdditionOperator());
		 Operator.operators.put("-", new SubtractionOperator());
		 Operator.operators.put("*", new MultiplicationOperator());
		 Operator.operators.put("/", new DivisionOperator());

		 String delimiters = "+-*/#! ";
		 StringTokenizer st = new StringTokenizer(expr, delimiters, true);
		 String tok;
		 while (st.countTokens() > 0) {
				 
			 if ( !(tok = st.nextToken()).equals(" ")) { // filter out spaces
				 if (Operand.check(tok)) { // check if tok is an operand
					 opdStack.push(new Operand(tok));
				 } else {
					 if (!(Operator.operators.containsValue(tok))) {
						 System.out.println("*****invalid token******");
						 System.exit(1);
				 }
				 
				 Operator newOpr = (Operator) Operator.operators.get(tok); 
				 
				 while ( ((Operator)oprStack.peek()).priority() >= 
						 newOpr.priority()) {
					 Operator oldOpr = ((Operator)oprStack.pop());
					 Operand op2 = (Operand)opdStack.pop();
					 Operand op1 = (Operand)opdStack.pop();
					 opdStack.push((Operand) oldOpr.execute(op1,op2));
				 }
				 oprStack.push(newOpr);
				 }
			 }
		 }
		 return opdStack.pop().getValue();
	 }
}
