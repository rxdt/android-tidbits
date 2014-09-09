
public class Operand {

	private static int value;
	
	public Operand(String tok) {
		value = Integer.parseInt(tok);
	}
	
	public Operand(int value) {}

	public static void setValue(int tok) {
		value = tok;
	}
	
	public static boolean check(String tok) {
		 try {
			 	setValue(Integer.parseInt(tok)); 
		        return true;
		    }
		    catch(Exception e) {
		        return false;
		    }
	}

	public int getValue() {
		return this.value;
	}
	
}

