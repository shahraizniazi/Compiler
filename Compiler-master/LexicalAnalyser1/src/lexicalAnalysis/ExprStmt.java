package lexicalAnalysis;

public class ExprStmt extends stmt{

	private Expr expr;
	
	public ExprStmt(Expr expr) {
		this.expr = expr;
	}
	
	
	
	public void display(String indent) {
		System.out.println(indent + "ExprStmt");
		expr.display(indent + "  ");
		
		
	}
	
	
	public Value interpret(SymbolTable t) {
		return expr.interpret(t);
	}
	

}
