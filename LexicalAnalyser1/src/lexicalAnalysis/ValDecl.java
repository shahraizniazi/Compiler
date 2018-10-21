package lexicalAnalysis;

public class ValDecl {
	private String id;
	private int value;
	
	
	public ValDecl(String id, int value) {
		this.id = id;
		this.value = value;
	}
	
	
	
	public String getId() {return id;}
	public int getValue() {return value;}
	
	
	public void display(String indent) {
			System.out.println(indent + "Val " + id + " =" + " "+ value);
		}
	}


