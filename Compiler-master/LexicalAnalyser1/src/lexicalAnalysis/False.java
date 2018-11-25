package lexicalAnalysis;

public class False extends Expr{

	public void display(String indent) {
		System.out.println(indent +"False");
	}
	
	
	public Value interpret(SymbolTable t) {
		return new BoolValue(false);
	}
}
