package lexicalAnalysis;

public class IfThen extends stmt {

	private Expr test;
	private stmt trueClause;
	
	
	public IfThen(Expr test, stmt trueClause) {
		this.test = test;
		this.trueClause=trueClause;
		
	}
	
	public void display(String indent) {
		System.out.println(indent + "IfThen");
		test.display(indent + "  ");
		trueClause.display(indent + "  ");
	}
	
}
