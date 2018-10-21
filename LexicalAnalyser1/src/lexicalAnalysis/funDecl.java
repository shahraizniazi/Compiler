package lexicalAnalysis;
import java.util.*;

public class funDecl {

	private String id;
	private VarTypes type;
	private List<Param> params;
	private Block block;
	
	
	public funDecl(String id, VarTypes type, List<Param> params, Block block) {
		this.id = id;
		this.type =type;
		this.params = params;
		this.block = block;
		
	}
	
	
	public void display(String indent) {
		System.out.println(indent+ "FUN " + id + " " + ": " + type.toString());
		if(params!=null) {
		for(int i=0; i<params.size();i++) {
			params.get(i).display(indent+ "  ");
		}}
		
		block.display(indent+ "  ");
		
		
		
	}
}
