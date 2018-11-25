package lexicalAnalysis;


import java.util.Scanner;

public class Input extends stmt{

	private String message;

	public Input(String message) {
		this.message=message;
	}
	
	
	
	
	public void display(String indent) {
		System.out.println(indent + "Input \""  + message + "\"");
		
	}
	
	public Value interpret(SymbolTable t) {
		Scanner sc = SymbolTable.sc;
		System.out.print(message);
		sc.nextLine();
		return new VoidValue();
	}
	
	
	
	
}
