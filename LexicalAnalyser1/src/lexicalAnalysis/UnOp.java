package lexicalAnalysis;

public class UnOp extends Expr{

	private Op1 op;
	private Expr expr;
	
	public UnOp(Op1 op, Expr expr) {
		this.op = op;
		this.expr = expr;
	}
	

	public Op1 getOp() {
		return op;
	}

	public Expr getExpr() {
		return expr;
	}

	public void display(String indent) {
		System.out.println(indent + "UnOp " + op);
		expr.display(indent + "  ");
	}
	
	
	
	

}
