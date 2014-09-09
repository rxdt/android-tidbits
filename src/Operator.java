import java.util.HashMap;

/*****************************************
  			SUPER OPERATOR CLASS 
******************************************/
public abstract class Operator {
	
	public String delimiters = "+-*/!#";
	
	public static HashMap<String, Operator> operators = 
			new HashMap<String, Operator>(); 
	
	public boolean check(String tok) {
		if(!delimiters.contains(tok)) {
			return false;
		}
		return true;
	}

	public abstract Operand execute(Operand op1, Operand op2);

	public abstract int priority();
}


/**********************************
 			ADDITION 
***********************************/
class AdditionOperator extends Operator {
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() + op2.getValue()));
	 }
	
	public int priority() {
		return 2;
	}
}


/***********************************
  			SUBTRACTION 
************************************/
class SubtractionOperator extends Operator {
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() - op2.getValue()));
	 }
	
	public int priority() {
		return 2;
	}
}


/********************************* 
  			DIVISION 
**********************************/
class DivisionOperator extends Operator {
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() / op2.getValue()));
	 }
	
	public int priority() {
		return 3;
	}
}


/*********************************
 			MULTIPLICATION 
**********************************/
class MultiplicationOperator extends Operator {
	
	public Operand execute(Operand op1, Operand op2){
		return (new Operand(op1.getValue() * op2.getValue()));
	}
	
	public int priority() {
		return 3;
	}
}

/*********************************
  			EXCLAMATION 
**********************************/
class ExclamationOperator extends Operator {
	
	@Override
	public Operand execute(Operand op1, Operand op2) {
		return null;
	}
	
	public int priority() {
		return 1;
	}
}


/*********************************
  			HASH 
**********************************/
class HashOperator extends Operator {

	@Override
	public Operand execute(Operand op1, Operand op2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int priority() {
		return 0;
	}

}

