package lexicalAnalysis;

public class ExprItem extends Item{

	private Expr expr;
	
	
	public ExprItem(Expr expr) {
		this.expr =expr;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "ExprItem");
		expr.display(indent + "  ");
		
	}


	public Expr getExpr() {
		return expr;
	}

}
