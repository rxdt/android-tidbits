import java.util.Scanner;

public class EvaluatorTester {
	
	 public static void main(String[] args) {
		 Evaluator anEvaluator = new Evaluator();
		 
		 System.out.println("Enter a number");
		 Scanner in = new Scanner(System.in);
		 String expression;
		 expression = in.next() + "!";
		 System.out.println("you entered " + expression);
		 System.out.println("the answer is : ");
		 
		 System.out.println(anEvaluator.eval(expression));
		 
		 for (String arg : args) {
			 System.out.println(arg + " = " 
					 + anEvaluator.eval(arg));
		 }
		 
	 }
}
