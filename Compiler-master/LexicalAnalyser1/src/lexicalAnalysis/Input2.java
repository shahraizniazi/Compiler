package lexicalAnalysis;
import java.util.Scanner;

public class Input2 extends stmt{

	private String message;
	private String id;
	
	
	public Input2(String message, String id) {
		this.message = message;
		this.id = id;
	}
	
	
	
	
	public void display(String indent) {
		System.out.println(indent + "Input2 \"" + message + "\"," + id);
		
	}
	
	
	public Value interpret(SymbolTable t) {
		IntCell lhs = (IntCell) t.lookup(id);
		System.out.print(message + " ");
		Scanner sc = SymbolTable.sc;
		String input = sc.nextLine();
		lhs.set(Integer.parseInt(input));
		return new VoidValue();
	}

}
