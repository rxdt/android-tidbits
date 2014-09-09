import java.util.HashMap;

/*****************************************
  			SUPER OPERATOR CLASS 
******************************************/
public abstract class Operator {
	
	private int priority = 0; 
	
	public String delimiters = "+-*/!#";
	
	static HashMap<String, Operator> operators = 
			new HashMap<String, Operator>();
	
	public boolean check(String tok) {
		if(!delimiters.contains(tok)) {
			return false;
		}
		return true;
	}

	public abstract Object execute(Operand op1, Operand op2);

	public int priority() {
		return this.priority;
	}

}


/**********************************
 			ADDITION 
***********************************/
class AdditionOperator extends Operator {
	
	public int priority = 2;
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() + op2.getValue()));
	 }
}


/***********************************
  			SUBTRACTION 
************************************/
class SubtractionOperator extends Operator {
	
	public int priority = 2;
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() - op2.getValue()));
	 }
}


/********************************* 
  			DIVISION 
**********************************/
class DivisionOperator extends Operator {
	
	public int priority = 3;
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() / op2.getValue()));
	 }
}


/*********************************
 			MULTIPLICATION 
**********************************/
class MultiplicationOperator extends Operator {
	
	public int priority = 3;
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() * op2.getValue()));
	}
}

/*********************************
  			EXCLAMATION 
**********************************/
class ExclamationOperator extends Operator {
	
	public int priority = 1;

	@Override
	public Object execute(Operand op1, Operand op2) {
		return null;
	}
}


/*********************************
  			HASH 
**********************************/
class HashOperator extends Operator {
	public int priority = 0;

	@Override
	public Object execute(Operand op1, Operand op2) {
		return null;
	}
}

