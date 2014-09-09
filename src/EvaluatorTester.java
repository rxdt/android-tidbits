import java.util.Scanner;

public class EvaluatorTester {
	
	 public static void main(String[] args) {
		 Evaluator anEvaluator = new Evaluator();
		 
		 System.out.println("Enter a number");
		 Scanner in = new Scanner(System.in);
		 String expression = in.next();
		 System.out.println("you entered " + expression);
		 expression = expression + "!";
		 System.out.print("the answer is: ");
		 System.out.println(anEvaluator.eval(expression));
	 }
}
