package lexicalAnalysis;

public class Id extends Expr {

	private String id;
	
	public Id(String id) {
		this.id = id;
	}
	
	
	
	public void display(String indent) {
		System.out.println(indent + "Id " + id);
		
	}

}
