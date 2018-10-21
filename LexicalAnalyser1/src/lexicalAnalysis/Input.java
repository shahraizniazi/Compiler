package lexicalAnalysis;

public class Input extends stmt{

	private String message;

	public Input(String message) {
		this.message=message;
	}
	
	
	
	
	public void display(String indent) {
		System.out.println(indent + "Input \""  + message + "\"");
		
	}
	
	
	
	
}
