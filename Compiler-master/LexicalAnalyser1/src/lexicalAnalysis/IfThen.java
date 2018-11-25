package lexicalAnalysis;

public class IfThen extends stmt {

	private Expr expr;
	private stmt trueClause;
	
	
	public IfThen(Expr expr, stmt trueClause) {
		this.expr = expr;
		this.trueClause=trueClause;
		
	}
	
	
	
	public void display(String indent) {
		System.out.println(indent + "IfThen");
		expr.display(indent + "  ");
		trueClause.display(indent + "  ");
	}
	
	
	public Value interpret(SymbolTable t) {
		Value test = (BoolValue) expr.interpret(t);
		if (test.boolValue())
			return trueClause.interpret(t);
		else
			return new VoidValue();
	}
	
}
