package lexicalAnalysis;

public class IfThenElse  extends stmt{

	private Expr expr;
	private stmt trueClause;
	private stmt falseClause;


	
	public IfThenElse(Expr expr, stmt trueClause, stmt falseClause) {
		this.expr = expr;
		this.trueClause= trueClause;
		this.falseClause= falseClause;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "IfThenElse");
		expr.display(indent + "  ");
		trueClause.display(indent + "  ");
		falseClause.display(indent +"  ");
		
	}


	@Override
	public Value interpret(SymbolTable t) {
		Value test =  expr.interpret(t);
		if (test.boolValue())
			return trueClause.interpret(t);
		else
			return falseClause.interpret(t);
	}
	
	
	
}
