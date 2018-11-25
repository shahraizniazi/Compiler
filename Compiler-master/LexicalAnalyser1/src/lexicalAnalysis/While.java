package lexicalAnalysis;

public class While extends stmt{

	private Expr expr;
	private stmt body;
	
	
	
	public While(Expr expr, stmt body) {
		
		this.expr = expr;
		this.body = body;
	}
	
	
	public void display(String indent) {
		System.out.println(indent+"While");
		expr.display(indent + "  ");
		body.display(indent + "  ");
		
	}
	
	
	
	public Value interpret(SymbolTable t) {
		Value test = expr.interpret(t);
		
		while (test.boolValue()){
			body.interpret(t);
			test = expr.interpret(t);
		}
		return new VoidValue();
	}
	
	
	
}
