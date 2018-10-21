package lexicalAnalysis;

public class Param {
	
	private String id;
	private VarTypes type;
	
	public Param(String id, VarTypes type) {
		this.id = id;
		this.type = type;
	}
	
	public String getId() {return id;}
	public VarTypes getType() {return type;}
	
	public void display(String indent) {
		System.out.println(indent + id + " : " + type.toString());
	}

}
