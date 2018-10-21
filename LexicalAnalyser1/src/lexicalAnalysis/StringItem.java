package lexicalAnalysis;

public class StringItem extends Item {

	private String message;
	
	
	public StringItem(String message) {
		
		this.message = message;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "StringItem \"" + message + "\"");
		
	}

}
