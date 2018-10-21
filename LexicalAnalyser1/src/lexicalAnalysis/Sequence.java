package lexicalAnalysis;
import java.util.*;


public class Sequence extends stmt{

	private List<stmt> body;
	
	public Sequence(List<stmt> body) {
		this.body = body;
	}
	
	
	public void display(String indent) {
		System.out.println(indent +"Sequence");
		for(int i=0; i<body.size();i++) {
			body.get(i).display(indent + "  ");
		}
	}

	
	
}
