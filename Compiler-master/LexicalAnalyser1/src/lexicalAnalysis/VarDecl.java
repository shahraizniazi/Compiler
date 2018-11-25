package lexicalAnalysis;

public class VarDecl {

	private String id;
	private Type type;
	
	
	public VarDecl(String id, Type type) {
		
		this.id= id;
		this.type = type;
	}
	
	public String getId() {return id;}
	public Type getType() {return type;}
	
	public void display(String indent) {
		System.out.println(indent+ "Var " + id + " : "+ type.toString());
	}

	public void interpret(SymbolTable t) {
		if (type == Type.Int)
			t.bind(id, new IntCell(0));
		else if (type == Type.Bool)
			t.bind(id,  new BoolCell(false));
	}
	
	
	
}
