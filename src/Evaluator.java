import java.util.*;

public class Evaluator {
	
	 static Stack<Operand> opdStack;
	 
	 private Stack<Operator> oprStack;
	 
	 private Operator newOpr;
	 
	 public Evaluator() {
		 opdStack = new Stack<Operand>();
		 oprStack = new Stack<Operator>();
	 }

	 public int eval(String expr) {
		 
		 Operator.operators.put("+", new AdditionOperator());
		 Operator.operators.put("-", new SubtractionOperator());
		 Operator.operators.put("*", new MultiplicationOperator());
		 Operator.operators.put("/", new DivisionOperator());
		 Operator.operators.put("!", new ExclamationOperator());
		 Operator.operators.put("#", new HashOperator());
		 
		 String delimiters = "+-*/#! ";
		 StringTokenizer st = new StringTokenizer(expr, delimiters, true);
		 String tok;
		 oprStack.push(Operator.operators.get("#"));
		 while (st.hasMoreElements()) {
				 
			 if ( !(tok = st.nextToken()).equals(" ")) { // filter out spaces 
				 if (Operand.check(tok)) { // check if tok is an operand
					 opdStack.push(new Operand(tok));  
				 } else {
					 if (!(Operator.operators.containsKey(tok))) {
						 System.out.println("*****invalid token******");
						 System.exit(1);
					 }
					 else {
						 newOpr = (Operator) Operator.operators.get(tok); 
						 System.out.println("new operator priority: " + newOpr.priority());
					 }
				 
				
				 
				 
				 while (((Operator) oprStack.peek()).priority() >= 
						 newOpr.priority()) {
					 Operator oldOpr = ((Operator)oprStack.pop());
					 Operand op2 = (Operand)opdStack.pop(); 
					 Operand op1 = (Operand)opdStack.pop(); 
					 opdStack.push((Operand) oldOpr.execute(op1,op2));
				 }
				 
				 }
			 }
		 }
		 return opdStack.pop().getValue();
	 }
}
