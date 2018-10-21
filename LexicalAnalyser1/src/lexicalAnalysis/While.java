package lexicalAnalysis;

public class While extends stmt{

	private Expr test;
	private stmt body;
	
	
	
	public While(Expr test, stmt body) {
		
		this.test = test;
		this.body = body;
	}
	
	
	public void display(String indent) {
		System.out.println(indent+"While");
		test.display(indent + "  ");
		body.display(indent + "  ");
		
	}
	
	
	
	
	
}
