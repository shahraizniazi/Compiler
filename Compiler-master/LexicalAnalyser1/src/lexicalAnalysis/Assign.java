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
	
	
	public Value interpret(SymbolTable t) {
		
		Value lhs = t.lookup(id);
		Value rhs = expr.interpret(t);
				
		if (lhs instanceof IntCell) {
			((IntCell) lhs).set(rhs.intValue());
		
		}
		else {
			((BoolCell)(lhs)).set(rhs.boolValue());}
		
		return rhs;
	}

}
