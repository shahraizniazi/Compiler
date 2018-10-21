package lexicalAnalysis;

public class IfThenElse  extends stmt{

	private Expr test;
	private stmt trueClause;
	private stmt falseClause;


	
	public IfThenElse(Expr test, stmt trueClause, stmt falseClause) {
		this.test = test;
		this.trueClause= trueClause;
		this.falseClause= falseClause;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "IfThenElse");
		test.display(indent + "  ");
		trueClause.display(indent + "  ");
		falseClause.display(indent +"  ");
		
	}
	
	
	
}
