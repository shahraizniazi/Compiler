package lexicalAnalysis;

public  class Assign extends stmt {

	
	private String id;
	private Expr expr;
	
	
	public Assign(String id, Expr expr) {
		this.id = id;
		this.expr = expr;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "Assign " + id);
		expr.display(indent + "  ");
		
	}

}
