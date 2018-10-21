package lexicalAnalysis;

public class Input2 extends stmt{

	private String message;
	private String id;
	
	
	public Input2(String message, String id) {
		this.message = message;
		this.id = id;
	}
	
	
	
	
	public void display(String indent) {
		System.out.println(indent + "Input2 \"" + message + "\"," + id);
		
	}

}
