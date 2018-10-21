package lexicalAnalysis;
import java.util.*;

public class Call extends Expr{


	private String id;
	private List<Expr> args;
	
	public Call(String id, List<Expr> args) {
		
		this.id = id;
		this.args=args;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "Call " + id);
		
		for(int i =0; i<args.size();i++) {
			args.get(i).display(indent+ "  ");
		}
		
	}
	
	
}
