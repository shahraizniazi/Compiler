package lexicalAnalysis;

public class True extends Expr {
	
	public void display(String indent) {
		System.out.println(indent + "True");
	}
	
	
	public Value interpret(SymbolTable t) {
		return new BoolValue(true);
	}
}
