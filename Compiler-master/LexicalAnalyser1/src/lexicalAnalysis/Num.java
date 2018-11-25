package lexicalAnalysis;

public class Num extends Expr {

	private int value;
	
	public Num(int value) {
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public void display(String indent) {
		System.out.println(indent + "Num " + value);
	}
	
	
	public Value interpret(SymbolTable t) {
		return new IntValue(value);
	}
	
	
	

}
